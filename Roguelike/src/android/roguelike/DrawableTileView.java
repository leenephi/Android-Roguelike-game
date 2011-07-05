package android.roguelike;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import android.view.View;

public class DrawableTileView extends View {

	private Drawable wall;	
	private Drawable[][] map;
	
	private int mapWidth = 24;
	private int mapHeight = 36;

	public DrawableTileView(Context context) {
		super(context);
		
		Resources res = getResources();
		
		wall = res.getDrawable(R.drawable.wall);
		
		map = new Drawable[mapWidth][mapHeight];
		
		for (int x=0; x<mapWidth; x++) {
    		for (int y=0; y<mapHeight; y++) {
    			
    			map[x][y] = wall;
    		}
		}
		
	}
	
    protected void onDraw(Canvas canvas) {
    	
    	int startX = 0;
        int startY = 0;
        
        int width = 20;
        int height = 20;
    	
    	for (int x=0; x<mapWidth; x++) {
    		for (int y=0; y<mapHeight; y++) {
    			
    			map[x][y].setBounds(startX + width*x, startY + height*y, 
    					startX + width*(x+1), startY + height*(y+1));
    		
    			map[x][y].draw(canvas);
    		}

		}
    	
    }

}