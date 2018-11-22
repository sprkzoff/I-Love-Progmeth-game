package logic;

import java.util.ArrayList;

public class Healer extends Character {
	public static int MAX_HP=800;
	public Healer() {
		super(60,1000);
	}
	public void Heal(Character friend,int plus_hp)
	{
		if(friend.isDead())
		{
			System.out.println("Your friend is already dead!");
		}
		else
		{
			friend.setHp(friend.getHp()+plus_hp);
		}
	}
	public void Clear_debuff(Character friend)
	{
		friend.setBleed(false);
		friend.setBurn(false);
		friend.setFreeze(false);
		friend.setStun(false);
	}
	
	public void AllHeal(ArrayList<Character> friends,int plus_hp)
	{
		for(int i=0;i<friends.size();i++)
		{
			Character temp = friends.get(i);
			if(temp.isDead())
			{
				System.out.println("Your friend is already dead!");
			}
			else
			{
				temp.setHp(temp.getHp()+plus_hp);
			}
		}
	}
}
