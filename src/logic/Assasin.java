package logic;

import java.util.ArrayList;

//Have two active skills & 1 passive skill (evade)

public class Assasin extends Character {
	private boolean Stealth;
	public Assasin() {
		super(100,900);
		Stealth = false;
	}
	public boolean isStealth() {
		return Stealth;
	}
	public void setStealth(boolean stealth) {
		Stealth = stealth;
	}
	
	public void Stealth_Attack(Character enemy,int damage)
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
	}
	
	public boolean evade() //passive evade
	{
		int random = (int )(Math.random() * 10 + 1);
		return random <= 5? true : false ;
	}
	
	public void Bleeding_blade(Character enemy) //when attack bleeding enemy plus 100dmg && set enemy bleed to false
	{
		if(!enemy.isBleed())
		{
			enemy.setBleed(true);
		}
		else
		{
			System.out.println("Enemy is already bleed!!!");
		}
	}
	
	
	
	
}
