package android.roguelike;

public class TileMap {
	
	private TileLayer tileCharData;
	private TileData otherData;
	private int width;
	private int height;

	public TileMap(int MapWidth, int MapHeight) {
		
		this.width = MapWidth;
		this.height = MapHeight;
		this.tileCharData = new TileLayer(width,height);
		this.otherData = new TileData(width,height);

	}
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	
	public TileLayer getCharData() {return this.tileCharData;}
	
	public TileData getData() {return this.otherData;}
	
	
	
	public boolean setData(TileLayer data) {
		if (data.isSameSize(data)) {
			this.tileCharData = data;
			return true;
		}
		
		return false;
	}


}
