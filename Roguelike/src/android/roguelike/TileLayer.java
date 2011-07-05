package android.roguelike;

import android.graphics.Canvas;

public class TileLayer {
	
	private int width;
	private int height;
	private DrawableChar[][] data;
	private DrawableChar base;
	
	public TileLayer(int LayerWidth, int LayerHeight, DrawableChar basechar) {
		
		width = LayerWidth;
		height = LayerHeight;
		base = basechar;
		data = new DrawableChar[width][height];
	}
	
	public void Draw(int StartX, int StartY, int w, int h, Canvas canvas){
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				if (this.data[x][y] != null){
					this.data[x][y].Draw(StartX+x*w, StartY+y*h, w, h, canvas);
				}
			}
		}
	}
	
	public void MergeTo(TileLayer layer) {
		if (layer.width == this.width && layer.height == this.height) {
			
			for (int x=0; x < width; x++) {
				for (int y=0; y < height; y++) {
					if (this.data[x][y] != null) layer.data[x][y] = this.data[x][y];
				}
			}
		}

	}
	
	public void Fill(DrawableChar c) {
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				data[x][y] = c;
			}
		}
	}
	
	public Boolean PutChar(int x, int y, DrawableChar c) {
		if (x >= 0 && x < width && y >= 0 && y < height){
			data[x][y] = c;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns DrawableChar in given coordinates (x,y)
	 * If there isn't any returns base DrawableChar
	 * @param x-coordinate integer
	 * @param y-coordinate integer
	 * @return DrawableChar or null
	 */
	public DrawableChar GetChar(int x, int y) {
		
		if (x >= 0 && x < width && y >= 0 && y < height){
			if (data[x][y] != null) {
				return data[x][y];
			} 
		} 
		
		return base;
		
	}

}
