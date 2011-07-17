package android.roguelike;

import android.graphics.Canvas;

public class TileLayer extends Layer<TileChar> {
	
	public TileLayer(int LayerWidth, int LayerHeight) {
		super(LayerWidth,LayerHeight);
	}

	public void Draw(int StartX, int StartY, int w, int h, Canvas canvas){
		for (int x=0; x < super.width; x++) {
			for (int y=0; y < super.height; y++) {
				
				TileChar t = super.get(x, y);

				if (t != null)t.Draw(StartX+x*w, StartY+y*h, w, h, canvas);

			}
		}
	}

}
