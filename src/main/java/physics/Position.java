package physics;

public class Position
{
	private int x;
	private int y;
	private float velocity;
	private int width;
	private int height;
	
	public Position(int x, int y, int width, int height, float velocity)
	{
		this.x			= x;
		this.y			= y;
		this.width		= width;
		this.height		= height;
		this.velocity	= velocity;
	}
	
	public Position(Position p)
	{
		this.x			= p.x;
		this.y			= p.y;
		this.width		= p.width;
		this.height		= p.height;
		this.velocity	= p.velocity;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public float getVelocity() {
		return velocity;
	}
	
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
}
