package logic;

public class Equipment extends Item{
	int wearableLevel;
	String type;
	int val;
	public Equipment(String type, String name, int val, int sell, int buy) {
		super(name,buy,sell);
		if(val < 0) val = 0;
		this.val = val;
		this.type = type;
	}
	public void setWearableLevel(int w) {
		if(w < 0) w = 0;
		this.wearableLevel = w;
	}
	public int getWearableLevel() {
		return this.wearableLevel;
	}
	public String getType() {
		return this.type;
	}
	public void setVal(int v) {
		if(v < 0) v = 0;
		this.val = v;
	}
	public int getVal() {
		return this.val;
	}
}
