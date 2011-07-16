package android.roguelike;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new TileView(this));
    }
 
	class TileView extends SurfaceView implements SurfaceHolder.Callback {
		
	    private DrawThread _thread;
	    private GameGenerator gameGen;
	    private GameHandler gameHandler;
	    
		private int screenWidth;
		private int screenHeight;
		
	    public TileView(Context context) {
	    	
	        super(context);
	        getHolder().addCallback(this);
	        _thread = new DrawThread(getHolder(), this);
	        
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
	        setFocusable(true);
	        
	        Resources res = getResources();
	        
	        Display display = getWindowManager().getDefaultDisplay(); 
	        
	        screenWidth = display.getWidth();
			screenHeight = display.getHeight();
	        
	        gameGen = new GameGenerator(res,screenWidth,screenHeight);
	        
	        gameHandler = new GameHandler(gameGen);
	        
	    }
	 
	    @Override
	    public boolean onTouchEvent(MotionEvent event) {

	    	if(event.getAction() == MotionEvent.ACTION_DOWN){
	    		
	    		int _x = (int) event.getX();
		        int _y = (int) event.getY();
		        
		        gameHandler.MovePlayer(_x,_y);
	    	}     
	        
	        return true;
	    }
	    
	    
	    @Override
	    public void onDraw(Canvas canvas) {
	    	synchronized (_thread.getSurfaceHolder()) {
	    		gameGen.Draw(canvas);
	    	}
	    }
	 
	    //@Override
	    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	        // TODO Auto-generated method stub
	    }
	 
	    //@Override
	    public void surfaceCreated(SurfaceHolder holder) {
	        _thread.setRunning(true);
	        _thread.start();
	    }
	 
	    //@Override
	    public void surfaceDestroyed(SurfaceHolder holder) {
	        // simply copied from sample application LunarLander:
	        // we have to tell thread to shut down & wait for it to finish, or else
	        // it might touch the Surface after we return and explode
	        boolean retry = true;
	        _thread.setRunning(false);
	        while (retry) {
	            try {
	                _thread.join();
	                retry = false;
	            } catch (InterruptedException e) {
	                // we will try it again and again...
	            }
	        }
	    }
	}
 
    class DrawThread extends Thread {
        private SurfaceHolder _surfaceHolder;
        private TileView _tilescreen;
        private boolean _run = false;
 
        public DrawThread(SurfaceHolder surfaceHolder, TileView tilescreen) {
            _surfaceHolder = surfaceHolder;
            _tilescreen = tilescreen;
        }
 
        public void setRunning(boolean run) {
            _run = run;
        }
        
        public TileView getView() {
        	return _tilescreen;
        }
        
        public SurfaceHolder getSurfaceHolder() {
            return _surfaceHolder;
        }
 
        @Override
        public void run() {
            Canvas c;
            while (_run) {
                c = null;
                try {
                    c = _surfaceHolder.lockCanvas(null);
                    synchronized (_surfaceHolder) {
                        _tilescreen.onDraw(c);
                    }
                } finally {
                    // do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state
                    if (c != null) {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
}