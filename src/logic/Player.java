package logic;

import java.util.ArrayList;

import character.Character;

public class Player{
	private int totaldmg_hit;
	private int totaldmg_get;
	private ArrayList<Character> characters;
	public Player() {
		this.totaldmg_get = 0;
		this.totaldmg_hit = 0;
		this.characters = new ArrayList<Character>();
	}
	
	public int getTotaldmg_hit() {
		return totaldmg_hit;
	}

	public void setTotaldmg_hit(int totaldmg_hit) {
		this.totaldmg_hit = totaldmg_hit;
	}

	public int getTotaldmg_get() {
		return totaldmg_get;
	}

	public void setTotaldmg_get(int totaldmg_get) {
		this.totaldmg_get = totaldmg_get;
	}

	public ArrayList<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(ArrayList<Character> characters) {
		this.characters = characters;
	}

	public boolean All_dead()
	{
		for(int i=0;i<this.characters.size();i++)
		{
			if(!this.characters.get(i).isDead())
			{
				return true;
			}
		}
		return false;
	}
}
