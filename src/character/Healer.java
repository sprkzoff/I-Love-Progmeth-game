package character;

import java.util.ArrayList;

public class Healer extends Character {
	public static int MAX_HP=800;
	public Healer() {
		super(60,1000);
		setImage("resources/druid.jpg");
		setSkillNames("Heal", "Cleansing", "Hands Of God");
	}
	public String heal(Character friend,int plus_hp)
	{
		if(friend.isDead())
		{
			System.out.println("Your friend is already dead!");
		}
		else
		{
			friend.setHp(friend.getHp()+plus_hp);
		}
		return "Heal";
	}
	public String cleansing(Character friend)
	{
		friend.setBleed(0);
		friend.setBurn(0);
		friend.setFreeze(0);
		friend.setStun(0);
		return "Cleansing";
	}
	
	public String handsOfGod(ArrayList<Character> friends,int plus_hp)
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
		return "Hands of God";
	}
	@Override
	public String getInstance() {
		return "Healer";
	}
	
}
