package android.roguelike;

import android.content.res.Resources;

import java.util.HashMap;
import java.util.Map;

public class DrawableCharset {
	
	private Map<String, DrawableChar> charset;
	private Resources resources;
	
	public DrawableCharset(Resources res) {
		
		charset = new HashMap<String, DrawableChar>();
		resources = res;
		UglyLoadCharset();
	}
	
	public DrawableChar GetChar(String name) {return charset.get(name);}
	
	/**TODO Make this nicer
	 *Ugly way of loading charset, need to fix this later 
	 */
	public void UglyLoadCharset(){

		charset.put("wall", new DrawableChar(R.drawable.wall, '#', "wall", resources));
		charset.put("floor", new DrawableChar(R.drawable.floor, '.', "floor", resources));
		charset.put("player", new DrawableChar(R.drawable.player, '.', "player", resources));
		
	}
	
	

}
