package android.roguelike;

public class MonsterGenerator {
	
	private GameGenerator gameGen;
	private TileMap tilemap;
	
	public MonsterGenerator(GameGenerator gameGen) {
		
		this.gameGen=gameGen;
		this.tilemap = gameGen.getTileMap();
	}
	
	public boolean spawnToMap(Monster monster){
		if (monster != null) {

			for (int x = 0; x < this.tilemap.getWidth(); x++){
				for (int y = 0; y < this.tilemap.getHeight(); y++){
					
					if (monster.moveTo(x, y))return true;
		
				}
			}
			
		}
		
		return false;
	}
	
	public boolean generateToMap(){
		
		return false;
		//TODO make monsters spawn here too
	}

}
