package android.roguelike;

import android.roguelike.TileData.spawnData;

public class MonsterGenerator {
	
	private GameGenerator gameGen;
	private TileMap tilemap;
	private TileData data;
	private TileCharset charset;
	private MonsterHandler monsterHandler;
	
	public MonsterGenerator(GameGenerator gameGen) {
		
		this.gameGen=gameGen;
		this.tilemap = gameGen.getTileMap();
		this.data = this.tilemap.getData();
		this.charset = gameGen.getCharset();
		this.monsterHandler = gameGen.getMonsterHandler();
		
	}
	
	
	public void generateToMap(){
	
		TileChar c = charset.requestChar("r", "rat", false, charset.new CharColor(192,128,64));
		 
		
		for (int x=0; x < tilemap.getWidth(); x++) {
			for (int y=0; y < tilemap.getHeight(); y++) {
				
				if (data.getValue(x, y)== spawnData.EASY_MONSTER) {
					
						Monster m = new Monster(c,"rat", gameGen, new Dot(x,y));
						m.setStats(5,1);
						
				}
			}
		}

	}

}
