package android.roguelike;

import android.graphics.Canvas;

public class TileScreen {
	
	private static final int BUFFERLAYER = 0;
	private static final int MAPLAYER = 1;
	private static final int ITEMLAYER = 2;
	private static final int MONSTERLAYER = 3;
	private static final int IMAGELAYER = 4;
	
	public TileLayer[] layerData;
	
	private int width;
	private int height;
	
	private TileMap map;

	public TileScreen(int ScreenWidth, int ScreenHeight) {
		
		width = ScreenWidth;
		height = ScreenHeight;
		layerData = new TileLayer[5];
		
		layerData[BUFFERLAYER] = new TileLayer(width,height);
		layerData[MONSTERLAYER] = new TileLayer(width,height);
		layerData[ITEMLAYER] = new TileLayer(width,height);
		layerData[IMAGELAYER] = new TileLayer(width,height);
		
	}
	
	public boolean putMonster(int x, int y, Monster monster){
		return this.layerData[MONSTERLAYER].PutChar(x, y, monster.getChar());
	}

	public boolean removeMonster(int x, int y){
		return this.layerData[MONSTERLAYER].PutChar(x, y, null);
	}
	
	public void LoadMap(TileMap tilemap) {this.map = tilemap;}
	
	public void Draw(int StartX, int StartY, int w, int h, Canvas canvas) {
		this.layerData[MAPLAYER] = this.map.getCharData();
		
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
