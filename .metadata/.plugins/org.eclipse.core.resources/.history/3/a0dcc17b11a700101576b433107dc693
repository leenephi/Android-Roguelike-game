package android.roguelike;

import android.graphics.Canvas;

public class TileScreen {
	
	private static final int BUFFERLAYER = 0;
	private static final int MAPLAYER = 1;
	private static final int ITEMLAYER = 2;
	private static final int MONSTERLAYER = 3;
	
	private TileLayer[] layerData;
	
	private int width;
	private int height;
	
	private DrawableCharset charset;
	private TileMap map;

	public TileScreen(int ScreenWidth, int ScreenHeight, DrawableCharset cset) {
		
		width = ScreenHeight;
		height = ScreenHeight;
		layerData = new TileLayer[4];
		
		layerData[BUFFERLAYER] = new TileLayer(width,height,null);
		charset = cset;
		
	}
	
	public void LoadMap(TileMap tilemap) {this.map = tilemap;}
	
	public void Draw(int StartX, int StartY, int w, int h, Canvas canvas) {
		this.layerData[MAPLAYER] = this.map.GetData();
		
		this.layerData[MAPLAYER].MergeTo(this.layerData[BUFFERLAYER]);
		
		this.layerData[BUFFERLAYER].Draw(StartX, StartY, w, h, canvas);
		
	}

}
