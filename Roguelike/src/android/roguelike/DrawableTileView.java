package android.roguelike;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;

import android.view.View;

public class DrawableTileView extends View {

	private DrawableCharset charset;
	private TileScreen tilescreen;
	private TileMap tilemap;
	
	private int charwidth = 9;
	private int charheight = 11;
	
	private int width = ((400 / charwidth) - 1);
	private int height = ((700 / charheight) - 1);

	public DrawableTileView(Context context) {
		super(context);
		
		Resources res = getResources();
		
		charset = new DrawableCharset(res);
		tilescreen = new TileScreen(width, height, charset);
		tilemap = new TileMap(width, height,charset);

		tilescreen.LoadMap(tilemap);
	}
	
    protected void onDraw(Canvas canvas) {
    	
    	int startX = 0;
        int startY = 0;
    
        tilescreen.Draw(startX, startY, charwidth,charheight, canvas);
    }

}
