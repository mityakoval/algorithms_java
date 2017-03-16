/**
 * 
 */


/**
 * @author mityakoval
 *
 */
public class Site {
	private int index;
	private boolean open;
	
	public Site(int index){
		this.index = index;
		this.open = false;
	}
	
	public boolean isOpen() {
		return this.open;
	}
	
	public void open() {
		this.open = true;
	}
	
	public int getIndex() {
		return index;
	}
}
