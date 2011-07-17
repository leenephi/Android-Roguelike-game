package android.roguelike;

import android.roguelike.TileCharset.CharColor;
import android.roguelike.TileMap.MonsterSpawnLayer;

public class MonsterGenerator {
	
	private GameGenerator gameGen;
	private TileMap tilemap;
	private MonsterSpawnLayer spawnData;

	private MonsterDatabase monsterDB;
	
	public MonsterGenerator(GameGenerator gameGen) {
		
		this.gameGen=gameGen;
		this.tilemap = gameGen.getTileMap();
		this.spawnData = this.tilemap.getSpawnData();
		this.monsterDB = gameGen.getMonsterDatabase();
		
	}
	
	public void generateToMap(){
		
		if (tilemap != null && spawnData != null && monsterDB != null) {

			for (int x=0; x < tilemap.getWidth(); x++) {
				for (int y=0; y < tilemap.getHeight(); y++) {
					
					if (spawnData.get(x, y) == TileMap.MonsterSpawnData.EASY_MONSTER) {

						switch ((int)(Math.random()*3.0)) {
							case 0: monsterDB.cloneMonster("rat", new Dot(x,y));break;
							case 1: monsterDB.cloneMonster("goblin", new Dot(x,y));break;
							case 2: monsterDB.cloneMonster("fire wyrm", new Dot(x,y));break;
						}
	
					}
				}
			}
		
		}

	}

}
