package android.roguelike;

public class TileData {
	
	private char[][] data;
	private int width;
	private int height;
	
	public final char MONSTER_EASY = 1;
	public final char MONSTER_NORMAL = 2;
	public final char MONSTER_HARD = 3;
	
	public TileData(int width, int height) {
		
		this.width = width;
		this.height = height;
		this.data = new char[width][height];
		
	}
	
	public void Box(int _x, int _y, int w, int h, char c){
		for (int x=_x; x < _x+w; x++) {
			if (x >= 0 && x < this.width){
				for (int y=_y; y < _y+h; y++) {
					if (y >= 0 && y < this.height) data[x][y] = c;
				}
			}
		}
	}
	
	public void putValue(char value, int x, int y){
		if (x>=0 && x < width && y>=0 && y<height) {
			this.data[x][y] = value;
		}
	}
	
	public char getValue(int x, int y){
		if (x>=0 && x < width && y>=0 && y<height) {
			return this.data[x][y];
		}
		return 0;
	}
	
	
}