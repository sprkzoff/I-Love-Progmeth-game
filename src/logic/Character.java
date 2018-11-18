package logic;

import java.util.ArrayList;

public class Character {
	private int atk;
	private int hp;
	private ArrayList<String> buffs;
	private ArrayList<String> debuffs;
	private boolean dead;
	private boolean stun;
	private boolean freeze;
	private boolean burn;
	public Character() {
		super();
		this.dead = false;
		this.stun = false;
		this.freeze = false;
		this.burn = false;
		this.atk = 0;
		this.hp = 1000;
		this.buffs = new ArrayList<String>(); //change type of array later
		this.debuffs = new ArrayList<String>();
	}
	public boolean isStun() {
		return stun;
	}
	public void setStun(boolean stun) {
		this.stun = stun;
	}
	public boolean isFreeze() {
		return freeze;
	}
	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}
	public boolean isBurn() {
		return burn;
	}
	public void setBurn(boolean burn) {
		this.burn = burn;
	}
	public Character(int atk,int hp) {
		super();
		this.dead = false;
		this.stun = false;
		this.freeze = false;
		this.burn = false;
		this.atk = atk;
		this.hp = hp;
		this.buffs = new ArrayList<String>(); //change type of array later
		this.debuffs = new ArrayList<String>();
	}
	
	public void attacked_by_enemy(int damage)
	{
		if(this.getHp()-damage <= 0)
		{
			this.setHp(0);
			this.setDead(true);
		}
		else
		{
			this.setHp(this.getHp()-damage);
		}
	}
	
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public ArrayList<String> getBuffs() {
		return buffs;
	}
	public void setBuffs(ArrayList<String> buffs) {
		this.buffs = buffs;
	}
	public ArrayList<String> getDebuffs() {
		return debuffs;
	}
	public void setDebuffs(ArrayList<String> debuffs) {
		this.debuffs = debuffs;
	}
	
}
