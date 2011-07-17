package android.roguelike;

import java.util.ArrayList;

import android.roguelike.TileMap.MonsterSpawnLayer;
import android.roguelike.TileMap.PassableLayer;

public class MapGenerator {
	
	private GameGenerator gameGen;
	private TileMap tilemap;
	private TileCharset charset;
	
	private MonsterSpawnLayer spawnData;
	private PassableLayer passableData;
	
	private int width;
	private int height;
	
	private ArrayList<Dot> dots;
	
	public MapGenerator(GameGenerator gameGen) {
		this.gameGen = gameGen;
		this.tilemap = gameGen.getTileMap();
		this.spawnData = this.tilemap.getSpawnData();
		this.passableData = this.tilemap.getPassableData();
		
		charset = gameGen.getCharset();
		
		width = tilemap.getWidth();
		height = tilemap.getHeight();
	}

	public TileLayer makeCorridor(TileLayer data, TileChar floor) {
		
	    int length;
		int nx;
		int ny;

		Dot d = null;
		
		if (dots.size()>0) {
		
			d = dots.get((int)(Math.random()*Math.max((dots.size()-1),0)));
		
		}
		
		if (d != null) {
	
			if (Math.random()>0.5){
				length = (int)(-3-Math.random()*7);
			} else {
				length = (int)(3+Math.random()*7);
			}
			
			if (Math.random()>0.5) {
				nx = d.x+length;
				ny = d.y;

				if (nx >0 && nx < width-1){
					
					for(int x=-1; x<2; x++){
						for(int y=-1; y<2; y++){
							if (data.get(nx+x, ny+y) == floor) return makeCorridor(data, floor);
						}
					}
					
					
					data.xLine(d.x, d.y, nx, floor);
					passableData.xLine(d.x, d.y, nx, true);
					dots.add(new Dot(nx,ny));
					
	
				}
				
			} else {
				nx = d.x;
				ny = d.y+length;
				
				if (ny >0 && ny < height-1){
					
					for(int x=-1; x<2; x++){
						for(int y=-1; y<2; y++){
							if (data.get(nx+x, ny+y) == floor) return makeCorridor(data, floor);
						}
					}
					
					data.yLine(d.x, d.y, ny, floor);
					passableData.yLine(d.x, d.y, ny, true);
					dots.add(new Dot(nx,ny));
					
				}
				
				
			}
		
		}
		
		return data;
		
	}

	public TileLayer makeRoom(TileLayer data, TileChar floor, TileMap.MonsterSpawnData spawn){
		
		
		Dot d = null;
		
		if (dots.size()>0) {
			
			d = dots.get((int)(Math.random()*Math.max((dots.size()-1),0)));
		
		}
		
		if (d != null) {
			
			int w = (int)(2+Math.random()*2);
			int h = (int)(2+Math.random()*2);
			
			int nx = Math.max(d.x-w,1);
			int ny = Math.max(d.y-h,1);
			int nw = Math.min(nx+w*2,width-1)-nx;
			int nh = Math.min(ny+h*2,height-1)-ny;
			
			data.Box( nx, ny, nw,nh, floor);
			spawnData.Box( nx, ny, nw,nh, spawn);
			passableData.Box( nx, ny, nw,nh, true);

		}
		
		return data;
		
	}
	
	public void generateDungeon(){
		
		TileLayer data = tilemap.getCharData();
		
		TileChar wall = charset.getChar("wall");
		TileChar floor = charset.getChar("floor");
		
		dots = new ArrayList<Dot>();

		data.Fill(wall);
		passableData.Fill(false);

	    dots.add(new Dot(width/2,height/2));
	    
	    for (int i=0; i<60; i++) {
	    
	    	makeCorridor(data, floor);

	    }
	    
	    for (int i=0; i<8; i++) {
		    
	    	makeRoom(data, floor,null);

	    }
	    
	    for (int i=0; i<2; i++) {
		    
	    	makeRoom(data, floor,TileMap.MonsterSpawnData.EASY_MONSTER);

	    }
	    
		gameGen.getTileScreen().LoadMap(tilemap);
		
	}
	
	

}
