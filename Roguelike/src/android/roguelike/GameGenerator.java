package android.roguelike;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;

public class GameGenerator {
	
	private TouchHandler _handler;
    
    private TileCharset charset;
	private TileScreen tilescreen;
	private TileMap tilemap;
	
	private MapGenerator mapGen;
	
	private MonsterGenerator monsterGen;
	
	private int screenWidth;
	private int screenHeight;
	
	private int charWidth = 13;
	private int charHeight = 17;
	
	private int worldWidth;
	private int worldHeight;
	
	private Monster player;

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
        
        _handler = new TouchHandler(touchables);

        player = new Monster(charset.GetChar("player"), "player", this);
        
	}
	
	public void handleMonster(Monster monster){
		this.monsterGen.spawnToMap(monster);
	}
	
	public void MovePlayer(String touch) {
		
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
	
	public Monster getPlayer(){return player;}	
	
	public TileCharset getCharset(){return charset;}	
	
	public TileScreen getTileScreen(){return tilescreen;}	
	
	public TileMap getTileMap(){return tilemap;}	
	
	public TouchHandler getTouchHandler(){return _handler;}	

	public void Draw(Canvas canvas){
		
		canvas.drawColor(Color.BLACK);
		
		tilescreen.Draw(0, 0, charWidth, charHeight, canvas);
		
		_handler.DrawTouchables(canvas);
		
	}
	
}
