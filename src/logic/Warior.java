package logic;

import java.util.ArrayList;

public class Warior extends Character {
	private int shield;
	public Warior(int shield) {
		super(300,1200);
		this.shield = shield;
	}
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	
	public void Warcry(ArrayList<Character> enemies,int damage) //deal every enemy dmg=???
	{
		for(int i=0;i<enemies.size();i++)
		{
			Character temp = enemies.get(i);
			if(!temp.isDead())
			{
				temp.attacked_by_enemy(damage);
			}
			
		}
	}
	
	public void Directstrike(Character enemy,int damage) //direct hit enemy with stunning dmg=???
	{
		enemy.attacked_by_enemy(damage);
		enemy.setStun(true);
	}
	
	public void Fearless(int powerup_shield) //plus shield
	{
		this.setShield(this.getShield()+powerup_shield);
	}
	
	@Override
	public void attacked_by_enemy(int damage)
	{
		if((this.getHp()+this.getShield())-damage <= 0)
		{
			this.setHp(0);
			this.setDead(true);
		}
		else
		{
			this.setHp((this.getHp()+this.getShield())-damage);
		}
	}
}
