package android.roguelike;

import android.graphics.Canvas;

public class TileScreen {
	
	private static final int BUFFERLAYER = 0;
	private static final int MAPLAYER = 1;
	private static final int ITEMLAYER = 2;
	private static final int MONSTERLAYER = 3;
	private static final int IMAGELAYER = 4;
	
	private TileLayer[] layerData;
	
	private int width;
	private int height;
	
	private TileCharset charset;
	private TileMap map;

	public TileScreen(int ScreenWidth, int ScreenHeight, TileCharset cset) {
		
		width = ScreenWidth;
		height = ScreenHeight;
		layerData = new TileLayer[5];
		
		layerData[BUFFERLAYER] = new TileLayer(width,height,null);
		layerData[MONSTERLAYER] = new TileLayer(width,height,null);
		layerData[ITEMLAYER] = new TileLayer(width,height,null);
		layerData[IMAGELAYER] = new TileLayer(width,height,null);
		
		charset = cset;
		
	}
	

	
	public boolean MovePlayer(int oldx, int oldy, int x, int y){
		
		if (PutPlayer(x, y) &&  (oldx != x || oldy != y)){
			return RemovePlayer(oldx, oldy);
		}
		
		return false;
	}
	
	public boolean PutPlayer(int x, int y){
		return this.layerData[MONSTERLAYER].PutChar(x, y, this.charset.GetChar("player"));
	}
	
	public boolean RemovePlayer(int x, int y){
		return this.layerData[MONSTERLAYER].PutChar(x, y, null);
	}
	
	public void LoadMap(TileMap tilemap) {this.map = tilemap;}
	
	public void Draw(int StartX, int StartY, int w, int h, Canvas canvas) {
		this.layerData[MAPLAYER] = this.map.GetData();
		
		this.layerData[IMAGELAYER].Fill(null);
		
		this.layerData[MAPLAYER].MergeTo(this.layerData[IMAGELAYER]);
		this.layerData[ITEMLAYER].MergeTo(this.layerData[IMAGELAYER]);
		this.layerData[MONSTERLAYER].MergeTo(this.layerData[IMAGELAYER]);
		
		//removing already drawn tiles
		//this.layerData[IMAGELAYER].DifferenceWith(this.layerData[BUFFERLAYER]);
		
		this.layerData[IMAGELAYER].Draw(StartX, StartY, w, h, canvas);
		
		//update buffer
		//this.layerData[IMAGELAYER].MergeTo(this.layerData[BUFFERLAYER]);
		
	}

}
