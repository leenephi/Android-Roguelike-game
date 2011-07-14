package android.roguelike;

public class Monster {
	
	private int x;
	private int y;
	private TileChar c;
	private TileScreen tilescreen;
	private GameGenerator gameGen;
	private String name;
	
	public Monster(TileChar _c, String name, GameGenerator gameGen) {
		c = _c;
		x = 0;
		y = 0;
		this.gameGen = gameGen;
		this.name = name;
		tilescreen = gameGen.getTileScreen();
		this.gameGen.getMonsterHandler().handleNewMonster(this);
	}
	
	public TileChar getChar() {return c;}
	
	public boolean moveTo(int _x, int _y) {
		
		TileChar ch  = this.gameGen.getTileMap().getData().GetChar(_x, _y);
		
		if (ch != null && ch.isPassable()) {
			if (tilescreen.PutMonster(_x, _y, this)) {
				tilescreen.RemoveMonster(x, y);
				x = _x;
	        	y = _y;
	        	return true;
			}
			
		}

        return false;
		
	}
	
	public boolean moveBy(int dx, int dy) {return moveTo(x+dx, y+dy);}
	


}
