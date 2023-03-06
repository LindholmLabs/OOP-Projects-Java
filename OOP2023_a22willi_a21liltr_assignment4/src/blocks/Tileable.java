package blocks;

public interface Tileable {
	public Object generate();
	
	public void rotateLeft();
	
	public void rotateRight();
	
	public void setPosition(int x, int y);
}
