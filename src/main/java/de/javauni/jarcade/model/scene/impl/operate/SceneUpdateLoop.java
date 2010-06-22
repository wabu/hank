package de.javauni.jarcade.model.scene.impl.operate;

import java.lang.reflect.ParameterizedType;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;

import com.google.inject.name.Named;

import java.util.concurrent.ThreadFactory;


import de.javauni.jarcade.model.phys.ControlableBody;
import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.Operator;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.event.SceneChangeListener;
import de.javauni.jarcade.utils.UpdateLoop;

/**
 * @author wabu
 */
public class SceneUpdateLoop extends UpdateLoop implements SceneChangeListener {
    private final Scene.Edit scene;
    private final Logger log = LoggerFactory.getLogger(SceneUpdateLoop.class);

    private final List<OperatorBinding<?>> ops;
    private final List<OperatorFactory<Layer, ?>> layOpFac;
    private final List<OperatorFactory<Entity, ?>> entOpFac;

    /**
     * @param intervall aspired time intervall in ms between the calls
     * @param tf        factory used to create a thread
     * @param scene
     */
    @Inject
    public SceneUpdateLoop(
            @Named("level-update-intervall") int intervall, 
            @Named("level-update-thread") ThreadFactory tf, 
            Scene.Edit scene) {
        super(intervall, tf);
        this.scene = scene;
        this.scene.getSceneChannel().addListener(this);
        this.ops = new ArrayList<OperatorBinding<?>>();
        this.layOpFac = new ArrayList<OperatorFactory<Layer,?>>();
        this.entOpFac = new ArrayList<OperatorFactory<Entity,?>>();
    }

    @SuppressWarnings("unchecked")
    @Inject
    void initialize(Injector inj) {
        for(Key<?> k : inj.getBindings().keySet()) {
            if(k.getTypeLiteral().getRawType().isAssignableFrom(Operator.class)) {
                Key<? extends Operator<?>> key = (Key<? extends Operator<?>>)k;
                log.debug("registering operator "+key);
                //registerOperator(inj.getInstance(ko));
            }
            if(k.getTypeLiteral().getRawType().isAssignableFrom(OperatorFactory.class)) {
                Key<? extends OperatorFactory> key = (Key<OperatorFactory>)k;
                ParameterizedType type = (ParameterizedType)key.getTypeLiteral().getType();
                log.debug("got operator with {} on {}.",key, type);
                if(Layer.class.isAssignableFrom((Class<?>)type.getActualTypeArguments()[0])) {
                    log.debug("registering layer operator {}.", key);
                    layOpFac.add(inj.getInstance(key));
                }
                if(Entity.class.isAssignableFrom((Class<?>)type.getActualTypeArguments()[0])) {
                    log.debug("registering entity operator {}.", key);
                    entOpFac.add(inj.getInstance(key));
                }
            }
        }
    }

    @Override
    public void onLayerAdded(Layer layer) {
        // TODO access key for layer type and annotation
        for(OperatorFactory<Layer, ?> fac : layOpFac) {
            log.debug("binding layer operator");
            ops.add(new OperatorBinding<Layer>(layer, fac.create(layer)));
        }
    }

    @Override
    protected void update(long delta) {
        for(OperatorBinding<?> op : ops) {
            if(log.isTraceEnabled()) {
                log.trace("calling "+op);
            }
            op.apply(delta);
        }
    }

    @Override
    public void onEntitySpawned(Entity e, Layer layer) {
        if(e instanceof ControlableBody) {
            log.debug("adding entity operator");
            OperatorFactory<Entity, ?> fac = entOpFac.get(0);
            ops.add(new OperatorBinding<Entity>(e, fac.create(e)));
        }
    }

    @Override
    public void onEntityRemoved(Entity e, Layer layer) {
    }

    @Override
    public void onEntityLayerChange(Entity e, Layer before, Layer layer) {
    }
}

