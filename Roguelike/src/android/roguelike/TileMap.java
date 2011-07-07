package android.roguelike;

public class TileMap {
	
	private TileLayer data;
	private int width;
	private int height;
	private TileCharset charset;

	public TileMap(int MapWidth, int MapHeight, TileCharset cset) {
		
		this.width = MapWidth;
		this.height = MapHeight;
		this.charset = cset;
		
		UglyGenerator();

	}
	
	public TileLayer GetData() {return this.data;}
	
	public void UglyGenerator() {
		
		this.data = new TileLayer(this.width,this.height,this.charset.GetChar("floor"));
		this.data.Fill(this.charset.GetChar("floor"));
		
	}

}
