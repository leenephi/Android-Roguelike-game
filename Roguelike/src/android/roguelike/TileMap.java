package android.roguelike;

public class TileMap {
	
	private TileLayer data;
	private int width;
	private int height;

	public TileMap(int MapWidth, int MapHeight) {
		
		this.width = MapWidth;
		this.height = MapHeight;
		this.data = new TileLayer(width,height);

	}
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	
	public TileLayer getData() {return this.data;}
	
	public boolean setData(TileLayer data) {
		if (data.isSameSize(data)) {
			this.data = data;
			return true;
		}
		
		return false;
	}


}
