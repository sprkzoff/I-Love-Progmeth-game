package logic;

public abstract class FightingCharacter extends Character{
	int hp;
	int atk;
	int def;
	public FightingCharacter() {
		super();
		this.hp = 1;
		this.atk = 1;
		this.def = 0;
	}
	public FightingCharacter(int x, int y,int hp, int atk, int def) {
		super(x,y);
		if(hp < 1) hp = 1;
		if(atk < 1) atk = 1;
		if(def < 0) def = 0;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
	}
}
