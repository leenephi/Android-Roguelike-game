package android.roguelike;


public class TileMap {
	
	private TileLayer tileLayer;
	private MonsterSpawnLayer spawnLayer;
	private PassableLayer passableLayer;
	private int width;
	private int height;

	public TileMap(int MapWidth, int MapHeight) {
		
		this.width = MapWidth;
		this.height = MapHeight;
		this.tileLayer = new TileLayer(width,height);
		this.spawnLayer = new MonsterSpawnLayer(width,height);
		this.passableLayer = new PassableLayer(width,height);

	}
	
	public enum MonsterSpawnData {
		EASY_MONSTER, NORMAL_MONSTER, HARD_MONSTER
	}
	
	public class MonsterSpawnLayer extends Layer<MonsterSpawnData> {
		
		public MonsterSpawnLayer(int LayerWidth, int LayerHeight) {
			super(LayerWidth,LayerHeight);
		}
		
	}
	
	public class PassableLayer extends Layer<Boolean> {
		
		public PassableLayer(int LayerWidth, int LayerHeight) {
			super(LayerWidth,LayerHeight);
		}
		
	}
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	
	public TileLayer getCharData() {return this.tileLayer;}
	
	public MonsterSpawnLayer getSpawnData() {return this.spawnLayer;}
	public PassableLayer getPassableData() {return this.passableLayer;}

}
