package de.javauni.jarcade.model.phys;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jbox2d.collision.shapes.Shape;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import org.jbox2d.dynamics.contacts.Contact;

import com.google.inject.Inject;

import com.google.inject.assistedinject.Assisted;

import de.javauni.jarcade.model.scene.operate.Operator;

public class ControlableBodyOperator implements Operator<ControlableBody> {
    private final ControlableBody entity;

    @Inject
    public ControlableBodyOperator(@Assisted ControlableBody entity) {
        this.entity = entity;
        this.world = entity.getBody().getWorld();
        init(entity);
    }

    @Override
    public void step(ControlableBody e, long delta) {
        this.step(delta/1000f);
    }

      //
     // @see org.jbox2d.testbed.tests.character;
    //

	/** 
	 * Minimum dot product between normal and vertical to qualify the character as "standing."
	 * 0.0 means you can "stand" on anything up to a wall, 1.0 means you have to be on something
	 * perfectly flat.  Pick something in the middle!!!  Behavior will be odd if the number is
	 * too close to either extreme.
	 */
	static private final float standingDotProductCutoff = 0.6f;
	
	static {
		assert(standingDotProductCutoff >= 0.0f && standingDotProductCutoff <= 1.0f);
	}
	
	private World world;
	private Vec2 vertical;
	private float scale;
	
	private float jumpSpeed;
	private float jumpContinueForceMax;
	private float jumpContinueDecayFactor = 0.85f;
	
	private float airMoveReductionFactor = 0.6f;
	
	//if the jump key is hit but the player is not on the ground yet, 
        // it will be retried for this many frames
	private int delayedJumpFudgeFrames = 5;
	
	//true if the controller is trying to make the character jump
	private boolean tryingToJump = false;
	//true if the model has accepted the jump command
	private boolean jumpActive = false; 
	/*
	 * true if the character has hit the ground AND stopped trying to jump -
	 * this is used to avoid bunny hopping if you hold the jump key
	 */
	private boolean jumpIsReset = true;   
    private Body body;
    private Shape feet;
	
	/**
	 * Create a character.
	 * @param w the World the character will live in
	 * @param p0 the center of the feet part of the capsule
	 * @param p1 the center of the head part of the capsule
	 * @param radius the radius of the capsule
	 */
	public void init(ControlableBody body) {
        this.body = body.getBody();
        this.feet = body.getFeet();

		vertical = body.getVertical();
		scale = vertical.normalize();
		jumpSpeed = .1f*(float)Math.sqrt(scale);
		jumpContinueForceMax = 7*(float)Math.sqrt(scale) * 20.0f;
		moveForce = 2*scale*20.0f;
		maxSpeed = scale*30.0f;
	}
	
	float leftMove, rightMove;
	       
        private boolean wasOnGroundLastStep = false;
        public void step(float dt) {
            leftMove = -10f*entity.neuronalDirection().get();
            if(leftMove < 0) {
                rightMove = -leftMove;
                leftMove = 0;
            }
            tryToJump(entity.neuronalJump().get() > 0);

                boolean onGroundThisStep = isOnGround();
                jumpLogic(onGroundThisStep);
                frictionLogic(onGroundThisStep);
                moveLogic(onGroundThisStep);
                dampingLogic(onGroundThisStep);
                wasOnGroundLastStep = onGroundThisStep;
        }
        
        private void dampingLogic(boolean onGroundThisStep) {
                if (!onGroundThisStep) 
            body.setLinearVelocity(
                    new Vec2(body.getLinearVelocity().x*.99f, body.getLinearVelocity().y));
        }
        
        private float footFriction = 80.0f;
        private void frictionLogic(boolean onGroundThisStep) {
                if (rightMove == leftMove && onGroundThisStep) {
                        feet.m_friction = footFriction;
                } else {
                        feet.m_friction = 0.0f;
                }
                world.refilter(feet);
        }
        
        private float moveForce;
        private float maxSpeed;
        
        public void setMaxSpeed(float newSpeed) {
                maxSpeed = newSpeed;
        }
        
        public float getMaxSpeed() { return maxSpeed; }
        
        private void moveLogic(boolean onGroundThisStep) {
                float move = rightMove - leftMove;
                float force = move * moveForce;
                
                Vec2 gVec = new Vec2(1.0f,0.0f);
                if (onGroundThisStep) {
                        gVec = Vec2.cross(-1.0f, standingNormal);
                } else {
                        force *= airMoveReductionFactor;
                }
                Vec2 velocityVec = body.getLinearVelocity();
                float dot = Vec2.dot(velocityVec, gVec);
                if (move < 0.0f) dot = -dot;
                float multiplier = MathUtils.map(dot,0,maxSpeed,1.0f,0.0f);
                if (dot < 0.0f) multiplier = 1.0f;
                if (multiplier < 0.0f) multiplier = 0.0f;
                Vec2 forceVec = gVec.mul(force*multiplier);
                body.applyForce(forceVec, body.getWorldCenter());
                
                if (dot < -0.1f && move != 0.0f && onGroundThisStep) {
                        body.setLinearVelocity(body.getLinearVelocity().mul(0.5f));
                }
                
                leftMove = 0.0f;
                rightMove = 0.0f;
        }
        
        int jumpFudgeCounter = -1;
        private void jumpLogic(boolean onGroundThisStep) {
                if (jumpIsReset && onGroundThisStep) jumpActive = tryingToJump;
                else if (!tryingToJump) jumpActive = false;
                
                if (!tryingToJump) jumpFudgeCounter = -1;
                
                //handle jump fudge factor, which triggers jump events for a few frames after a failed attempt
                if (!onGroundThisStep && !jumpActive && tryingToJump && jumpFudgeCounter == -1) {
                        jumpFudgeCounter = delayedJumpFudgeFrames;
                }
                
                if (!jumpActive && jumpFudgeCounter > 0) {
                        jumpFudgeCounter -= 1;
                        tryingToJump = true;
                }
                
                if (onGroundThisStep && !tryingToJump) {
                        jumpIsReset = true;
                }
                if (onGroundThisStep && !wasOnGroundLastStep) {
//                      System.out.println("new on ground");
                        jumpActive = false;
                        jumpIsReset = false;
                }
                if (jumpActive) {
//                      System.out.println("Active");
                        if (isOnGround()) {
//                              System.out.println("jump");
                                jump();
                        }
                        continueJump();
                        jumpIsReset = false;
                } else {
                        if (!onGroundThisStep){// && body.getLinearVelocity().y < 0) {
                                body.applyForce(new Vec2(0.0f, -body.getMass()*9.8f), body.getWorldCenter());
                        }
                }
        }
        
        private Vec2 jumpVector = null;
        private void jump() {
                if (jumpIsReset == false) return;
                // Average standing normal and vertical to get jump vector.
                // Modify this to suit game mechanics - you might not want to
                // alter jump direction when jumping from a slope.
                jumpVector = standingNormal.add(vertical).mul(0.5f);
                jumpVector.normalize();

//              jumpVector = vertical;

                float mass = body.getMass();
                Vec2 impulse = jumpVector.mul(jumpSpeed * mass);
//              System.out.println(impulse.length());
                body.applyImpulse(impulse, body.getWorldCenter());
                jumpContinueForce = jumpContinueForceMax;
        }
        
        public void setJumpSpeed(float newSpeed) { jumpSpeed = newSpeed; }
        public float getJumpSpeed() { return jumpSpeed; }
        
        private float jumpContinueForce;
        private void continueJump() {
                if (jumpVector == null) jumpVector = new Vec2(0.0f,1.0f);
                body.applyForce(jumpVector.mul(jumpContinueForce*body.getMass()), body.getWorldCenter());
                jumpContinueForce *= jumpContinueDecayFactor;
        }
        
        public void setJumpContinueForce(float newForce) { jumpContinueForceMax = newForce; }
        public float getJumpContinueForce() { return jumpContinueForceMax; }
        
        /** 
         * Called to set the jump state.
         * May be ignored by model if the model is not receiving new jump instructions yet.
         * @param tf
         */
        void tryToJump(boolean tf) {
//              System.out.println("ttj " + tf);
                tryingToJump = tf;
        }
        
        public boolean isOnGround() {
                Vec2 standingNormal1 = getStandingNormal();
                if (standingNormal1 != null) return true;
                else return false;
        }
        
        /**
         * Calculates the best estimate of the normal vector between the feet and the ground,
         * pointing "up."
         * 
         * If multiple normal vectors qualify as "standing" vectors, the normalized average of
         * the qualifying standing vectors is returned.  This is done to make sure that when
         * multiple contacts are present, one is not arbitrarily chosen as the normal, which
         * could lead to strange jumping behavior if jump direction depends on normal. 
         * 
         * If no qualifying standing vectors exist, the routine will check the average of all
         * contact normals to see if it might qualify as a standing vector.  This ensures that
         * if the feet are "pinned" between two steep walls the player can still jump.
         * 
         * @return the normal vector, or null if the character either isn't standing on 
         * anything or the effective normal does not qualify as a standing vector.
         */
        public Vec2 getStandingNormal() {
                List<Vec2> standingNormals = new ArrayList<Vec2>();
                List<Vec2> allNormals = new ArrayList<Vec2>();
                standingNormal = null;
                
                Set<Contact> contacts = feet.getContacts();
                for (Contact c:contacts) {
                        Vec2 normal = null;
                        if (c.m_shape1 == feet) {
                                normal = c.getManifolds().get(0).normal.mul(-1);
                        } else {
                                normal = c.getManifolds().get(0).normal.mul(1);
                        }
                        allNormals.add(normal);
                        float dot = Vec2.dot(normal, vertical);
                        if (dot >= standingDotProductCutoff) {
                                standingNormals.add(normal);
                        }
                }
                
                if (standingNormals.size() > 0) {
                        float sumx = 0.0f;
                        float sumy = 0.0f;
                        for (Vec2 v:standingNormals) {
                                sumx += v.x;
                                sumy += v.y;
                        }
                        sumx /= standingNormals.size();
                        sumy /= standingNormals.size();
                        standingNormal = new Vec2(sumx, sumy);
                        standingNormal.normalize();
                } else if (allNormals.size() > 0) {
                        float sumx = 0.0f;
                        float sumy = 0.0f;
                        for (Vec2 v:allNormals) {
                                sumx += v.x;
                                sumy += v.y;
                        }
                        sumx /= allNormals.size();
                        sumy /= allNormals.size();
                        standingNormal = new Vec2(sumx, sumy);
                        if (Vec2.dot(standingNormal, vertical) < standingDotProductCutoff) {
//                              System.out.println("Null");
                                standingNormal = null; //didn't satisfy our cutoff criteria
                        }
                }
                
                return standingNormal;
        }
        
        private Vec2 standingNormal;
        
}
