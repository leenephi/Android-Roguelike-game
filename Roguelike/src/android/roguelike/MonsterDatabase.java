package android.roguelike;

import java.util.HashMap;
import java.util.Map;

public class MonsterDatabase {
	
	private Map<String,MonsterData> data;
	
	private GameGenerator gameGen;
	
	public MonsterDatabase (GameGenerator gameGen){
		
		this.gameGen = gameGen;
		
		data = new HashMap<String,MonsterData>(); 
		
		uglyLoadDataBase();
		
	}
	
	//TODO
	public void uglyLoadDataBase(){
		
		this.add(new MonsterData("rat").setTileChar("r",192,128,64)
			     .putData(MonsterStat.HITPOINTS, 4).putData(MonsterStat.ATTACK, 1));
		
		this.add(new MonsterData("goblin").setTileChar("g",64,192,64)
			     .putData(MonsterStat.HITPOINTS, 9).putData(MonsterStat.ATTACK, 3));
		
		this.add(new MonsterData("fire wyrm").setTileChar("W",128,0,0)
			     .putData(MonsterStat.HITPOINTS, 70).putData(MonsterStat.ATTACK, 9));
		
		
	}
	
	public void add(MonsterData data){
		if (data != null) {
			this.data.put(data.name, data);
		}
	}
	
	
	public Monster cloneMonster(String monsterName, Dot dot){
		
		if (data != null) {
		
			MonsterData d = data.get(monsterName);
			
			if (d != null && gameGen != null ) {
				
				TileChar t = d.getTileChar();
				
				if (t != null){
					Monster m = new Monster(t, monsterName, gameGen, dot);
					m.setStats(d);
					return m;
				}

			}
			
		}
		
		return null;

	}
	
	public enum MonsterStat {
		HITPOINTS, ATTACK, DEFENCE, DODGE
	}
	
	public class MonsterData {
		
		private Map<MonsterStat, Integer> data;
		private String name;
		private TileChar tileChar;
		
		public MonsterData(String monsterName) {
			 data = new HashMap<MonsterStat, Integer>();
			 name = monsterName;
			 this.tileChar = null;
		}
		
		public MonsterData setTileChar(String ch, int r, int g, int b){
			TileCharset charset = gameGen.getCharset();
			this.tileChar = charset.requestChar(ch, name, charset.new CharColor(r,g,b));
			return this;
		}
		
		public TileChar getTileChar(){ return tileChar;}
		
		public String getName(){ return name;}
		
		public Integer getData(MonsterStat s){
			return data.get(s);
		}
		
		public MonsterData putData(MonsterStat s, int value){
			data.put(s, value);
			return this;
		}
		
		
	}

}
