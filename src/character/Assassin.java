package character;

import java.util.ArrayList;

//Have two active skills & 1 passive skill (evade)

public class Assassin extends Character {
	private boolean Stealth;
	
	public Assassin() {
		super(100,900);
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
	
	public String stealthAttack(Character enemy,int damage)
	{
		if(((enemy.getHp())/enemy.MAX_HP)*100 < 35.0) // if HP < 35% -> set assasin dmg*3
		{
			if(enemy instanceof Warrior) //break the warrior shield
			{
				Warrior temp = (Warrior)enemy;
				enemy.attacked_by_enemy((damage*3)+temp.getShield());
			}
			else
			{
				enemy.attacked_by_enemy(damage*3);
			}
		}
		else
		{
			enemy.attacked_by_enemy(damage);
		}
		return "Stealth Attackk";
	}
	
	public boolean evade() //passive evade
	{
		int random = (int )(Math.random() * 10 + 1);
		return random <= 5? true : false ;
	}
	
	public String bleedingBlade(Character enemy) //when attack bleeding enemy plus 100dmg && set enemy bleed to false
	{
		if(!enemy.isBleed())
		{
			enemy.setBleed(true);
		}
		else
		{
			System.out.println("Enemy is already bleed!!!");
		}
		return "Bleeding Blade";
	}
	
	@Override
	public String getInstance() {
		return "Assassin";
	}
	
	
}
