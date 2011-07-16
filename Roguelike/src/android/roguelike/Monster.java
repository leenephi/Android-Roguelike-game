package android.roguelike;

public class Monster {
	
	private Dot coordinates;
	private int hitpoints;
	private int attack;
	private TileChar c;
	private TileScreen tilescreen;
	private GameGenerator gameGen;
	private MonsterHandler monsterHandler;
	private String name;
	
	public Monster(TileChar _c, String name, GameGenerator gameGen, Dot coordinates) {
		c = _c;
		this.coordinates = null;
		this.gameGen = gameGen;
		this.name = name;
		tilescreen = gameGen.getTileScreen();
		monsterHandler = this.gameGen.getMonsterHandler();
		monsterHandler.handleNewMonster(this, coordinates);
	}
	
	public boolean isAlive() {return this.hitpoints>0;}
	
	public void setStats(int HP, int ATT) {
		this.attack = ATT;
		this.hitpoints = HP;
	}
	
	public TileChar getChar() {return c;}
	
	public Dot getCoordinates() {return coordinates;}
	
	public boolean attackMonster(Monster monster){
		if (this.coordinates.distanceTo(monster.coordinates)==1) {
			monster.hitpoints -= this.attack;
			return true;
		}
		return false;
	}
	
	
	
	public boolean moveTo(Dot dot) {
		
		if (dot != null) {
			
			int _x = dot.x;
			int _y = dot.y;
			
			TileChar ch  = this.gameGen.getTileMap().getCharData().GetChar(_x, _y);
			
			if (ch != null && ch.isPassable()) {
				
				if ( monsterHandler.isEmpty(dot) ) {
			
					if (coordinates != null) {
						tilescreen.removeMonster(coordinates.x, coordinates.y);
						monsterHandler.removeMonster(coordinates);
					}
					
					this.coordinates = dot;	
					
					tilescreen.putMonster(_x, _y, this);
					
					monsterHandler.putMonster(this);
					
			        return true;
				} else {
					this.attackMonster(monsterHandler.getMonster(dot));
					return false;
				}
				
				
			}
		
		}

        return false;
		
	}
	
	public boolean moveBy(Dot dot) {return moveTo(coordinates.add(dot));}
	


}
