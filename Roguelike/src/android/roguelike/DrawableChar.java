package android.roguelike;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class DrawableChar {
	
	private Drawable drawable;
	private char c;
	private String name; 
	
	public DrawableChar(int resourceID, char ch, String name, Resources res) {
		drawable = res.getDrawable(resourceID);
		c = ch;
	}
	
	public void Draw(int x, int y,int w,int h, Canvas canvas){
		this.drawable.setBounds(x, y, x+w, y+h);
		this.drawable.draw(canvas);
	}

	public Drawable GetDrawable() {return drawable;}
	
	public char GetChar() {return c;}
	
	public String GetName() {return name;}

}
