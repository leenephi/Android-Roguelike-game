package android.roguelike;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class TileChar {
	
	private Bitmap bitmap;
	private char c;
	private String name; 
	
	public TileChar(int ResourceId, char ch, String n, Resources res) {
		bitmap = BitmapFactory.decodeResource(res, ResourceId);
		c = ch;
		name = n;
	}
	
	public void Draw(int x, int y,int w,int h, Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
	}

	public Bitmap GetBitmap() {return bitmap;}
	
	public char GetChar() {return c;}
	
	public String GetName() {return name;}

}
