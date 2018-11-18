package logic;

import java.util.ArrayList;

public class Player{
	private int totaldmg_hit;
	private int totaldmg_get;
	private ArrayList<Character> characters;
	public Player() {
		super();
		this.totaldmg_get = 0;
		this.totaldmg_hit = 0;
		this.characters = new ArrayList<Character>();
	}
}
