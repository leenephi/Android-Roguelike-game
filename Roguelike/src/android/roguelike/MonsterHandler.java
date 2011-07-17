package android.roguelike;

public class MonsterHandler {
	
	private MonsterLayer monsterLayer;
	private TileMap tilemap;
	private GameGenerator gameGen;
	private Monster player;
	
	public MonsterHandler(GameGenerator gameGen){
		
		this.gameGen = gameGen;
		this.tilemap = gameGen.getTileMap();
		
		monsterLayer = new MonsterLayer(tilemap.getWidth(),tilemap.getHeight());
		
	}
	
	public class MonsterLayer extends Layer<Monster>{
		public MonsterLayer(int LayerWidth, int LayerHeight) {
			super(LayerWidth,LayerHeight);
		}
	}
	
	public boolean isEmpty(Dot dot){
		return (getMonster(dot)==null);
	}
	
	public Monster getMonster(Dot dot){
		return monsterLayer.get(dot);
	}
	
	public void putMonster(Monster monster){
		if (monster != null){
			Dot coor = monster.getCoordinates();
			monsterLayer.put(coor,monster);
		}
	}
	
	public void removeMonster(Dot dot){
		monsterLayer.put(dot,null);
	}
	
	public void setPlayer(Monster player){
		this.player = player;
	}
	
	public void update(){
		
		for (int x=0; x < tilemap.getWidth(); x++){
			for (int y=0; y < tilemap.getHeight(); y++){
				
				Monster m = monsterLayer.get(x, y);
				if (m != null && m != player) {
					
					if (m.isAlive()) {
						wanderAround(m);
					} else {
						monsterLayer.put(x, y, null);
					}
					
					
				}
			}
		}
	}
	
	public Monster handleNewMonster(Monster monster, Dot dot){
		if (monster != null) {
			if (dot == null || monster.moveTo(dot)==false) {
				
				Dot coor = monster.getCoordinates();
				if (isEmpty(dot)) removeMonster(coor);
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
