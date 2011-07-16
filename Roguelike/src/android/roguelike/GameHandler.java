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
		
		Dot touch = gameGen.getTouchHandler().getTouchable(x, y);

		if (touch != null) {
			if (touch.x == 0 && touch.y == 0){
				//wait
				monsterHandler.update();
			}else if (Math.abs(touch.x)<=1 && Math.abs(touch.y)<=1) {
				player.moveBy(touch);
				monsterHandler.update();
			}
		}

		

		
        
	}
	
	public void handleNewMonster(Monster monster, Dot dot){
		monsterHandler.handleNewMonster(monster, dot);
	}
	
}
