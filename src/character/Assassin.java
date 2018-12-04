package character;

import java.util.ArrayList;

//Have two active skills & 1 passive skill (evade)

public class Assassin extends Character {
	private boolean Stealth;
	public static final int EVADE_CHANCE = 30;
	public Assassin() {
		super(150,900);
		setImage("resources/assassin.jpg");
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
		int damage = 300;
		if(((enemy.getHp())/enemy.MAX_HP)*100 < 35.0) // if HP < 35% -> set assassin dmg*3
		{
			enemy.attackByEnemy(damage*3);
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
		if(!enemy.isBleed())
		{
			enemy.setBleed(1);
			return true;
		}
		else
		{
			System.out.println("Enemy is already bleed!!!");
			return false;
		}
	}
	
	@Override
	public String getInstance() {
		return "Assassin";
	}
	
	
}
