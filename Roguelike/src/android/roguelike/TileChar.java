package android.roguelike;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.roguelike.TileCharset.CharColor;

public class TileChar {
	
	private Bitmap bitmap;
	private Paint paint;
	private Canvas can;
	private String c;
	private String name;
	private CharColor color;
	
	public TileChar(String ch, String name, CharColor color, Resources res, int charHeight) {
		
		bitmap = BitmapFactory.decodeResource(res, R.drawable.empty);
		can = new Canvas(bitmap);
		
		paint = new Paint();
		paint.setTextSize(charHeight-4);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setSubpixelText (true);
		
		paint.setARGB(255, color.getR(), color.getG(), color.getB());
		can.drawText(ch, 0, 1+charHeight/2, paint);
		
		c = ch;
		this.name = name;
	}
	
	public void Draw(int x, int y,int w,int h, Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
	}
	
	public CharColor getColor() {return color;}
	
	public Bitmap getBitmap() {return bitmap;}
	
	public String getChar() {return c;}
	
	public String getName() {return name;}

}
