package android.roguelike;

import java.util.ArrayList;

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
	
	private int worldWidth;
	private int worldHeight;
	
	private Monster player;
	
	private MapGenerator mapGen;
	private MonsterGenerator monsterGen;
	
	private TouchHandler touchHandler;
	private MonsterHandler monsterHandler;

	public GameGenerator(Resources res, int width, int height){

        charset = new TileCharset(res);
        
		screenWidth = width;
		screenHeight = height;
		
		worldWidth = (screenWidth / charWidth) ;
		worldHeight = (screenHeight / charHeight);
        
		tilescreen = new TileScreen(worldWidth, worldHeight);
		tilemap = new TileMap(worldWidth, worldHeight);

		mapGen = new MapGenerator(this);
		
		mapGen.generateDungeon();

		monsterGen = new MonsterGenerator(this);

		int buttonW = screenWidth/3;
		int buttonBigH = buttonW;
		int buttonSmallH = (int)(buttonW*.75);

		ArrayList<TouchBox> touchables = new ArrayList<TouchBox>();
        
        touchables.add(new TouchBox("left",0,screenHeight-buttonBigH,buttonW,buttonBigH));
        
        touchables.add(new TouchBox("right",screenWidth-buttonBigH,screenHeight-buttonW,buttonW,buttonBigH));
        
        touchables.add(new TouchBox("up",buttonW,screenHeight-buttonSmallH*2,buttonW,buttonSmallH));
        
        touchables.add(new TouchBox("down",buttonW,screenHeight-buttonSmallH,buttonW,buttonSmallH));
        
        touchHandler = new TouchHandler(touchables);
        
        monsterHandler = new MonsterHandler(this);

        player = new Monster(charset.getChar("player"), "player", this);
        
        monsterHandler.spawnToMap(player);

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
