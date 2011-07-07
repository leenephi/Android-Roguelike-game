package android.roguelike;

import android.content.res.Resources;

import java.util.HashMap;
import java.util.Map;

public class TileCharset {
	
	private Map<String, TileChar> charset;
	private Resources resources;
	
	public TileCharset(Resources res) {
		
		charset = new HashMap<String, TileChar>();
		resources = res;
		UglyLoadCharset();
	}
	
	public TileChar GetChar(String name) {return charset.get(name);}
	
	/**TODO Make this nicer
	 *Ugly way of loading charset, need to fix this later 
	 */
	public void UglyLoadCharset(){

		charset.put("wall", new TileChar(R.drawable.wall, '#', "wall", resources));
		charset.put("floor", new TileChar(R.drawable.floor, '.', "floor", resources));
		charset.put("player", new TileChar(R.drawable.player, '.', "player", resources));
		
	}
	
	

}
