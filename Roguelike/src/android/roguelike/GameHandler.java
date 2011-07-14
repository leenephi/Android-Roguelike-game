package android.roguelike;

public class GameHandler {

	private GameGenerator gameGen;
	
	private TouchHandler touchHandler;
	
	private MonsterHandler monsterHandler;
	
	private Monster player;
	
	public GameHandler(GameGenerator gameGen){

		this.gameGen = gameGen;
        
		touchHandler = gameGen.getTouchHandler();
		
		monsterHandler = gameGen.getMonsterHandler();

        player = gameGen.getPlayer();
        
	}
	
	public void MovePlayer(int x, int y) {
		
		String touch = gameGen.getTouchHandler().getTouchable(x, y);
		
		if (touch=="left"){
			player.moveBy(-1, 0);
        } else if (touch=="right"){
        	player.moveBy(1, 0);
        } else if (touch=="up"){
        	player.moveBy(0, -1);
        } else if (touch=="down"){
        	player.moveBy(0, 1);
	    }
        
	}
	
	public void handleNewMonster(Monster monster){
		monsterHandler.handleNewMonster(monster);
	}
	
}
