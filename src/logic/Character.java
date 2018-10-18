package logic;

public class Character {
	int x;
	int y;
	public Character() {
		this.x = 0;
		this.y = 0;
	}
	public Character(int x, int y) {
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		this.x = x;
		this.y = y;
	}
}
