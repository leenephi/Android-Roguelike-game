package android.roguelike;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;

public class GameGenerator {
	
    private TileCharset charset;
	private TileScreen tilescreen;
	private TileMap tilemap;
	
	private int screenWidth;
	private int screenHeight;
	
	private int charWidth = 13;
	private int charHeight = 17;
	
	public final Dot WAIT = new Dot(0,0);
	public final Dot LEFT = new Dot(-1,0);
	public final Dot RIGHT = new Dot(1,0);
	public final Dot UP = new Dot(0,-1);
	public final Dot DOWN = new Dot(0,1);
	
	public final Dot UPLEFT = UP.add(LEFT);
	public final Dot UPRIGHT = UP.add(RIGHT);
	public final Dot DOWNLEFT = DOWN.add(LEFT);
	public final Dot DOWNRIGHT = DOWN.add(RIGHT);
	
	
	private int worldWidth;
	private int worldHeight;
	
	private Monster player;
	
	private MapGenerator mapGen;
	private MonsterGenerator monsterGen;
	
	private TouchHandler touchHandler;
	private MonsterHandler monsterHandler;

	public GameGenerator(Resources res, int width, int height){

        charset = new TileCharset(res,charHeight);
        
		screenWidth = width;
		screenHeight = height;
		
		worldWidth = (screenWidth / charWidth) ;
		worldHeight = (screenHeight / charHeight);
        
		
		int buttonWmargin = (screenWidth/6);
		int buttonW = ((screenWidth/3)*2)/3;
		int buttonH = (int)((double)buttonW/1.5);
		
		int buttonOrginX = buttonWmargin;
		int buttonOrginY = screenHeight-buttonH*3;

		touchHandler = new TouchHandler();
		
		touchHandler.add(new TouchBox(LEFT,buttonOrginX,buttonOrginY+buttonH,buttonW,buttonH));
        
		touchHandler.add(new TouchBox(RIGHT,buttonOrginX+buttonW*2,buttonOrginY+buttonH,buttonW,buttonH));
        
		touchHandler.add(new TouchBox(UP,buttonOrginX+buttonW,buttonOrginY,buttonW,buttonH));
        
		touchHandler.add(new TouchBox(DOWN,buttonOrginX+buttonW,buttonOrginY+buttonH*2,buttonW,buttonH));
		
		touchHandler.add(new TouchBox(WAIT,buttonOrginX+buttonW,buttonOrginY+buttonH,buttonW,buttonH));
		
		touchHandler.add(new TouchBox(UPLEFT,buttonOrginX,buttonOrginY,buttonW,buttonH));
        
		touchHandler.add(new TouchBox(UPRIGHT,buttonOrginX+buttonW*2,buttonOrginY,buttonW,buttonH));
        
		touchHandler.add(new TouchBox(DOWNLEFT,buttonOrginX,buttonOrginY+buttonH*2,buttonW,buttonH));
        
		touchHandler.add(new TouchBox(DOWNRIGHT,buttonOrginX+buttonW*2,buttonOrginY+buttonH*2,buttonW,buttonH));
		
        
		tilescreen = new TileScreen(worldWidth, worldHeight);
		tilemap = new TileMap(worldWidth, worldHeight);

		mapGen = new MapGenerator(this);
		
		mapGen.generateDungeon();
		
		monsterHandler = new MonsterHandler(this);

		monsterGen = new MonsterGenerator(this);
		
		monsterGen.generateToMap();

        player = new Monster(charset.getChar("player"), "player", this, null);
        player.setStats(20, 3);
        
        monsterHandler.setPlayer(player);
        
	}
	
	public MonsterHandler getMonsterHandler(){return monsterHandler;}	
	
	public Monster getPlayer(){return player;}	
	
	public TileCharset getCharset(){return charset;}	
	
	public TileScreen getTileScreen(){return tilescreen;}	
	
	public TileMap getTileMap(){return tilemap;}	
	
	public TouchHandler getTouchHandler(){return touchHandler;}	

	public void Draw(Canvas canvas){
		
		canvas.drawColor(Color.BLACK);
		
		tilescreen.Draw(0, 0, charWidth, charHeight, canvas);
		
		touchHandler.DrawTouchables(canvas);
		
	}
	
}
