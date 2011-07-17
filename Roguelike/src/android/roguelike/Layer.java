package android.roguelike;


public class Layer<E> {
	
	public final int width;
	public final int height;
    private Object[][] data;
    
    public Layer(int LayerWidth, int LayerHeight) {
		
		width = LayerWidth;
		height = LayerHeight;
		data = new Object[width][height];
	}
    
    public boolean onLayer(Dot dot){
		return (dot != null && dot.x>=0 && dot.x < width && dot.y>=0 && dot.y < height); 
	}
    
    public boolean setData(Layer<E> data) {
		if (data.isSameSize(data)) {
			this.data = data.data;
			return true;
		}
		
		return false;
	}
    
	public boolean isSameSize(Layer<E> other){ 
		return (other.width == this.width && other.height == this.height);
	}
		
	public void MergeTo(Layer<E> layer) {
		if (this.isSameSize(layer)) {
			
			for (int x=0; x < width; x++) {
				for (int y=0; y < height; y++) {
					if (this.data[x][y] != null) layer.data[x][y] = this.data[x][y];
				}
			}
		}

	}
	
	public void DifferenceWith(Layer<E> layer) {
		if (this.isSameSize(layer)) {
			for (int x=0; x < width; x++) {
				for (int y=0; y < height; y++) {
					if (this.data[x][y] == layer.data[x][y]) this.data[x][y] = null;
				}
			}
		}

	}
	
	public void Box(int _x, int _y, int w, int h, Object c){
		for (int x=_x; x < _x+w; x++) {
			if (x >= 0 && x < width){
				for (int y=_y; y < _y+h; y++) {
					if (y >= 0 && y < height) data[x][y] = c;
				}
			}
		}
	}
	
	public void xLine(int x, int y, int x2, Object c){
		if (y >= 0 && y < height) {
			if (x < x2) {
				for (int dx=x; dx <= x2; dx++) {
					if (dx >= 0 && dx < width){
						data[dx][y] = c;
					}
				}
			} else {
				for (int dx=x2; dx <= x; dx++) {
					if (dx >= 0 && dx < width){
						data[dx][y] = c;
					}
				}
			}
		}
	}
	
	public void yLine(int x, int y, int y2, Object c){
		if (x >= 0 && x < width) {
			if (y < y2) {
				for (int dy=y; dy <= y2; dy++) {
					if (dy >= 0 && dy < height){
						data[x][dy] = c;
					}
				}
			} else {
				for (int dy=y2; dy <= y; dy++) {
					if (dy >= 0 && dy < height){
						data[x][dy] = c;
					}
				}
			}
		}
	}
	
	public void Fill(Object c) {
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				data[x][y] = c;
			}
		}
	}
	
	public boolean put(Dot dot, Object c) {
		if (onLayer(dot)){
			data[dot.x][dot.y] = c;
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public E get(Dot dot) {
		
		if (onLayer(dot)){
			return (E) data[dot.x][dot.y];
		} 
		
		return null;
		
	}
	
	public boolean put(int x, int y, Object c) {
		if (x >= 0 && x < width && y >= 0 && y < height){
			data[x][y] = c;
			return true;
		} else {
			return false;
		}
	}
	

	@SuppressWarnings("unchecked")
	public E get(int x, int y) {
		
		if (x >= 0 && x < width && y >= 0 && y < height){
			return (E) data[x][y];
		} 
		
		return null;
		
	}


}
