package character;

import java.util.ArrayList;

public class Warrior extends Character {
	private int shield;
	public static int MAX_HP=1200;
	
	public Warrior() {
		super(300,1200);
		setImage("resources/warrior.jpg");
		setSkillNames("War Cry", "Direct Strike", "Fearless");
		this.shield = 20;
	}
	
	public Warrior(int shield) {
		super(300,1200);
		setImage("resources/warrior.jpg");
		setSkillNames("War Cry", "Direct Strike", "Fearless");
		this.shield = shield;
	}
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	
	public String warcry(ArrayList<Character> enemies,int damage) //deal every enemy dmg=???
	{
		for(int i=0;i<enemies.size();i++)
		{
			Character temp = enemies.get(i);
			if(!temp.isDead())
			{
				temp.attacked_by_enemy(damage);
			}
			
		}
		return "War Cry";
	}
	
	public String directStrike(Character enemy,int damage) //direct hit enemy with stunning dmg=???
	{
		enemy.attacked_by_enemy(damage);
		enemy.setStun(true);
		return "Direct Strike";
	}
	
	public String fearless(int powerup_shield) //plus shield
	{
		this.setShield(this.getShield()+powerup_shield);
		return "Fearless";
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
	@Override
	public String getInstance() {
		return "Warrior";
	}
	
}
