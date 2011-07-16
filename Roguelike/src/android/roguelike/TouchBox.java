package android.roguelike;

import android.graphics.Canvas;
import android.graphics.Paint;

public class TouchBox {
	
	private int x;
	private int y;
	private int w;
	private int h;
	private Dot id;

	public TouchBox(Dot id, int _x, int _y, int _w, int _h) {
		this.x = _x;
		this.y = _y;
		this.w = _w;
		this.h = _h;
		this.id = id;
	}
	
	public Dot getId() {return id;}
	
	public boolean isInside(int _x, int _y) {
		return ( _x >= x && _x <= x + w && _y >= y && _y <= y + h);
	}
	
	public void Draw(Canvas canvas, Paint paint){
		
		canvas.drawRect ((float) x, (float) y, (float) (x+w-1), (float) (y+h-1), paint);
	}
	
}
