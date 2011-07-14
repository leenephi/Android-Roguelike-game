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
	private boolean passable;
	private boolean seeThrough;
	
	public TileChar(String ch, String name, CharColor color, boolean passable, Resources res) {
		
		bitmap = BitmapFactory.decodeResource(res, R.drawable.empty);
		can = new Canvas(bitmap);
		
		paint = new Paint();
		paint.setTextSize(11);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setSubpixelText (true);
		
		paint.setARGB(255, color.getR(), color.getG(), color.getB());
		can.drawText(ch, 0, 11, paint);
		
		this.passable = passable;
		this.seeThrough = passable;
		c = ch;
		this.name = name;
	}
	
	public void Draw(int x, int y,int w,int h, Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
	}

	public boolean isPassable() {return passable;}
	
	public CharColor getColor() {return color;}
	
	public Bitmap getBitmap() {return bitmap;}
	
	public String getChar() {return c;}
	
	public String getName() {return name;}

}
