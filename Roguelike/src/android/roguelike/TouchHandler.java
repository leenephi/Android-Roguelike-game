package android.roguelike;

import android.graphics.Canvas;
import android.graphics.Paint;

public class TouchHandler {
	
	private TouchBox[] touchables;
	
	private Paint paint;
	
	public TouchHandler(TouchBox[] _touchables) {
		this.touchables = _touchables;
		
		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setARGB(64, 255, 255, 255);
	}
	
	public void DrawTouchables(Canvas canvas){
		for (TouchBox t: this.touchables) {
			t.Draw(canvas,paint);
		}
	}
	
	public String getTouchable(int x, int y) {
		for (TouchBox t: this.touchables) {
			if (t.isInside(x, y)) return t.getName();
		}
		
		return null;
	}
	
}
