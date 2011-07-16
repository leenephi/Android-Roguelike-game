package android.roguelike;

import java.util.ArrayList;

public class MonsterHandler {
	
	private ArrayList<Monster> monsterList;
	private TileMap tilemap;
	private GameGenerator gameGen;
	
	public MonsterHandler(GameGenerator gameGen){
		
		this.gameGen = gameGen;
		this.tilemap = gameGen.getTileMap();
		
		monsterList = new ArrayList<Monster>();
		
	}
	
	public void handleNewMonster(Monster monster, int x, int y){
		if (monsterList.contains(monster)==false) {
			monsterList.add(monster);
			if (monster.moveTo(x, y)==false) {
				spawnToMap(monster);
			}
			
		}
	}
	
	public void wanderAround(Monster monster){
		
	}
	
	public boolean spawnToMap(Monster monster){
		if (monster != null) {

			for (int x = 0; x < this.tilemap.getWidth(); x++){
				for (int y = 0; y < this.tilemap.getHeight(); y++){
					
					if (monster.moveTo(x, y)) return true;
				}
			}
			
		}
		
		return false;
	}

}
