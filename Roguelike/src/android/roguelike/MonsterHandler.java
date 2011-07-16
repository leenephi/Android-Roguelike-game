package android.roguelike;

import java.util.HashMap;
import java.util.Map;

public class MonsterHandler {
	
	private Monster[][] monsterList;
	private TileMap tilemap;
	private GameGenerator gameGen;
	private Monster player;
	
	public MonsterHandler(GameGenerator gameGen){
		
		this.gameGen = gameGen;
		this.tilemap = gameGen.getTileMap();
		
		monsterList = new Monster[gameGen.getTileMap().getWidth()][gameGen.getTileMap().getHeight()];
		
	}
	
	public boolean isEmpty(Dot dot){
		return (getMonster(dot)==null);
	}
	
	public Monster getMonster(Dot dot){
		if (dot != null && dot.onMap(tilemap)){
			return monsterList[dot.x][dot.y];
		}
		return null;
	}
	
	public void putMonster(Monster monster){
		if (monster != null){
			Dot coor = monster.getCoordinates();
			
			if (coor.onMap(tilemap)){
				monsterList[coor.x][coor.y] = monster;
			}
			
			
		}
	}
	
	public void removeMonster(Dot dot){
		if (dot != null && dot.onMap(tilemap)){
			monsterList[dot.x][dot.y] = null;
		}
	}
	
	public void setPlayer(Monster player){
		this.player = player;
	}
	
	public void update(){
		
		for (int x=0; x < tilemap.getWidth(); x++){
			for (int y=0; y < tilemap.getHeight(); y++){
				Monster m = monsterList[x][y];
				if (m != null && m != player) {
					
					if (m.isAlive()) {
						wanderAround(m);
					} else {
						monsterList[x][y]=null;
					}
					
					
				}
			}
		}
	}
	
	public Monster handleNewMonster(Monster monster, Dot dot){
		if (monster != null) {
			if (monster.moveTo(dot)==false) {
				if (isEmpty(dot)) removeMonster(dot);
				spawnToMap(monster);
			} 
			
			putMonster(monster);

		}
		return monster;
	}
	
	public void wanderAround(Monster monster){
		if (monster != null) {
			
			switch ((int)(Math.random()*4+0.5)) {
				case 1: monster.moveBy(gameGen.LEFT); break;
				case 2: monster.moveBy(gameGen.RIGHT); break;
				case 3: monster.moveBy(gameGen.UP); break;
				case 4: monster.moveBy(gameGen.DOWN); break;
			}

		}
		
	}
	
	public boolean spawnToMap(Monster monster){
		if (monster != null) {

			for (int x = 0; x < this.tilemap.getWidth(); x++){
				for (int y = 0; y < this.tilemap.getHeight(); y++){
					
					if (monster.moveTo(new Dot(x, y))) return true;
				}
			}
			
		}
		
		return false;
	}

}
