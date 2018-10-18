package logic;

public class Potion extends Item{
	int healingValue;
	public Potion() {
		this.setName("Noob's Potion");
		this.healingValue = 0;
	}
	public Potion(String name, int healing) {
		if(healing < 1) healing = 1;
		this.setName(name);
		this.healingValue = healing;
	}
}
