package character;

import java.util.ArrayList;

public class Warrior extends Character {
	public static int MAX_HP=1200;
	
	public Warrior() {
		super(200,1200);
		setImage("resources/warrior.jpg");
		setSkillNames("War Cry", "Direct Strike", "Berserk");
	}
	
	public void warcry(ArrayList<Character> enemies) //deal every enemy dmg=???
	{
		int damage = this.getAtk();
		for(int i=0;i<enemies.size();i++)
		{
			Character temp = enemies.get(i);
			if(!temp.isDead())
			{
				temp.attackByEnemy(damage);
			}
			
		}
	}
	
	public void directStrike(Character enemy) //direct hit enemy with stunning dmg=???
	{
		int damage = this.getAtk();
		enemy.attackByEnemy(damage);
		enemy.setStun(1);
	}
	
	public void berserk(Character enemy) //plus shield
	{
		int damage = this.getMaxHp() - this.getHp();
		enemy.attackByEnemy(damage);
	}
	
	
	@Override
	public String getInstance() {
		return "Warrior";
	}
	
}
