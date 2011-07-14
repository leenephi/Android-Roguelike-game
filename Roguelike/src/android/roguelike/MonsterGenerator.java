package android.roguelike;

public class MonsterGenerator {
	
	private GameGenerator gameGen;
	private TileMap tilemap;
	
	public MonsterGenerator(GameGenerator gameGen) {
		
		this.gameGen=gameGen;
		this.tilemap = gameGen.getTileMap();
		
	}
	
	public boolean generateToMap(){
		
		return false;
		//TODO make monsters spawn here too
	}

}
