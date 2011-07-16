package android.roguelike;

import android.roguelike.TileCharset.CharColor;

public class MonsterGenerator {
	
	private GameGenerator gameGen;
	private TileMap tilemap;
	private TileData data;
	private TileCharset charset;
	private MonsterHandler handler;
	
	public MonsterGenerator(GameGenerator gameGen) {
		
		this.gameGen=gameGen;
		this.tilemap = gameGen.getTileMap();
		this.data = this.tilemap.getData();
		this.charset = gameGen.getCharset();
		this.handler = gameGen.getMonsterHandler();
		
	}
	
	
	public boolean generateToMap(){
	
		TileChar c = charset.requestChar("r", "rat", false, charset.new CharColor(192,128,64));
		 
		
		for (int x=0; x < tilemap.getWidth(); x++) {
			for (int y=0; y < tilemap.getHeight(); y++) {
				
				if (data.getValue(x, y)== data.MONSTER_EASY) {
					
						Monster m = new Monster(c,"rat", gameGen, x,y);
						
				}
			}
		}
		
		
		
		return false;
		//TODO make monsters spawn here too
	}

}
