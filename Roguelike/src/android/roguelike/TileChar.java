package android.roguelike;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class TileChar {
	
	private Bitmap bitmap;
	private char c;
	private String name;
	private boolean passable;
	private boolean seeThrough;
	
	public TileChar(int ResourceId, char ch, String n, boolean passable, Resources res) {
		bitmap = BitmapFactory.decodeResource(res, ResourceId);
		this.passable = passable;
		this.seeThrough = passable;
		c = ch;
		name = n;
	}
	
	public void Draw(int x, int y,int w,int h, Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
	}

	public boolean isPassable() {return passable;}
	
	public Bitmap GetBitmap() {return bitmap;}
	
	public char GetChar() {return c;}
	
	public String GetName() {return name;}

}
