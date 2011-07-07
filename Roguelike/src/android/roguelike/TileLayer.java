package android.roguelike;

import android.graphics.Canvas;

public class TileLayer {
	
	private int width;
	private int height;
	private TileChar[][] data;
	
	public TileLayer(int LayerWidth, int LayerHeight) {
		
		width = LayerWidth;
		height = LayerHeight;
		data = new TileChar[width][height];
	}
	
	public boolean isSameSize(TileLayer other){ 
		return (other.width == this.width && other.height == this.height);
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
		if (this.isSameSize(layer)) {
			
			for (int x=0; x < width; x++) {
				for (int y=0; y < height; y++) {
					if (this.data[x][y] != null) layer.data[x][y] = this.data[x][y];
				}
			}
		}

	}
	
	public void DifferenceWith(TileLayer layer) {
		if (this.isSameSize(layer)) {
			for (int x=0; x < width; x++) {
				for (int y=0; y < height; y++) {
					if (this.data[x][y] == layer.data[x][y]) this.data[x][y] = null;
				}
			}
		}

	}
	
	public void Box(int _x, int _y, int w, int h, TileChar c){
		for (int x=_x; x < _x+w; x++) {
			if (x >= 0 && x < width){
				for (int y=_y; y < _y+h; y++) {
					if (y >= 0 && y < height) data[x][y] = c;
				}
			}
		}
	}
	
	public void xLine(int x, int y, int x2, TileChar c){
		if (y >= 0 && y < height) {
			if (x < x2) {
				for (int dx=x; dx <= x2; dx++) {
					if (dx >= 0 && dx < width){
						data[dx][y] = c;
					}
				}
			} else {
				for (int dx=x2; dx <= x; dx++) {
					if (dx >= 0 && dx < width){
						data[dx][y] = c;
					}
				}
			}
		}
	}
	
	public void yLine(int x, int y, int y2, TileChar c){
		if (x >= 0 && x < width) {
			if (y < y2) {
				for (int dy=y; dy <= y2; dy++) {
					if (dy >= 0 && dy < height){
						data[x][dy] = c;
					}
				}
			} else {
				for (int dy=y2; dy <= y; dy++) {
					if (dy >= 0 && dy < height){
						data[x][dy] = c;
					}
				}
			}
		}
	}
	
	public void Fill(TileChar c) {
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				data[x][y] = c;
			}
		}
	}
	
	public Boolean PutChar(int x, int y, TileChar c) {
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
	public TileChar GetChar(int x, int y) {
		
		if (x >= 0 && x < width && y >= 0 && y < height){
			return data[x][y];
		} 
		
		return null;
		
	}

}
