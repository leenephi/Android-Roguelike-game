package android.roguelike;

public class TileData {
	
	private spawnData[][] data;
	private int width;
	private int height;
	
	public enum spawnData {
		EASY_MONSTER, NORMAL_MONSTER, HARD_MONSTER
	}
	
	public TileData(int width, int height) {
		
		this.width = width;
		this.height = height;
		this.data = new spawnData[width][height];
		
	}
	
	public void Box(int _x, int _y, int w, int h, spawnData c){
		for (int x=_x; x < _x+w; x++) {
			if (x >= 0 && x < this.width){
				for (int y=_y; y < _y+h; y++) {
					if (y >= 0 && y < this.height) data[x][y] = c;
				}
			}
		}
	}
	
	public void putValue(spawnData value, int x, int y){
		if (x>=0 && x < width && y>=0 && y<height) {
			this.data[x][y] = value;
		}
	}
	
	public spawnData getValue(int x, int y){
		if (x>=0 && x < width && y>=0 && y<height) {
			return this.data[x][y];
		}
		return null;
	}
	
	
}