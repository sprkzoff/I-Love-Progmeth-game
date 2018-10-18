package logic;

public class Item {
	private String name;
	private int buyingCost;
	private int sellingCost;
	public Item() {
		this.name = "undefined";
		this.buyingCost = 1;
		this.sellingCost = 1;
	}
	public Item(String name, int buy, int sell) {
		if(buy < 1) buy = 1;
		if(sell < 1) sell = 1;
		this.name = name;
		this.buyingCost = buy;
		this.sellingCost = sell;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}
