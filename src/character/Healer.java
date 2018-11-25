package character;

import java.util.ArrayList;

public class Healer extends Character {
	public static int MAX_HP=800;
	public Healer() {
		super(60,1000);
		setImage("resources/druid.jpg");
		setSkillNames("Heal", "Cleansing", "Hands Of God");
	}
	public void heal(Character friend,int plus_hp)
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
	public void cleansing(Character friend)
	{
		friend.setBleed(false);
		friend.setBurn(false);
		friend.setFreeze(false);
		friend.setStun(false);
	}
	
	public void handsOfGod(ArrayList<Character> friends,int plus_hp)
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
	@Override
	public String getInstance() {
		return "Healer";
	}
	
}
