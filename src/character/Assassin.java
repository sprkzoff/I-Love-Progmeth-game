package character;

import java.util.ArrayList;

//Have two active skills & 1 passive skill (evade)

public class Assassin extends Character {
	private boolean Stealth;
	public static final int EVADE_CHANCE = 30;
	public Assassin() {
		super(100,900);
		setImage("resources/assassin.jpg");
		setDeadImage("resources/assassin_dead.jpg");
		setSkillNames("Stealth Attack", "Evade", "Bleeding Blade");
		Stealth = false;
	}
	public boolean isStealth() {
		return Stealth;
	}
	public void setStealth(boolean stealth) {
		Stealth = stealth;
	}
	
	public boolean stealthAttack(Character enemy)
	{
		int damage = (int) (this.getAtk() * 1.5);
		if((double) enemy.getHp() / (double) enemy.getMaxHp() <= 0.35) // if HP < 35% -> set assassin dmg*2
		{
			enemy.attackByEnemy(damage*2);
			
		}
		else
		{
			enemy.attackByEnemy(damage);
		}
		return true;
	}
	
	public boolean evade() //passive evade
	{
		int random = (int )(Math.random() * 10 + 1);
		return random <= 5? true : false ;
	}
	
	public boolean bleedingBlade(Character enemy) //when attack bleeding enemy plus 100dmg && set enemy bleed to false
	{
		int damage = this.getAtk();
		enemy.attackByEnemy(damage);
		if(!enemy.isBleed())
		{
			enemy.setBleed(2);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public String getInstance() {
		return "Assassin";
	}
	
	
}
