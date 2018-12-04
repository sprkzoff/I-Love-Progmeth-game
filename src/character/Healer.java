package character;

import java.util.ArrayList;

public class Healer extends Character {
	public static int MAX_HP=800;
	private static final int HEALING_POWER = 600;
	private static final int ALL_HEAL_HEALING_POWER = 300;
	public Healer() {
		super(100,1000);
		setImage("resources/druid.jpg");
		setSkillNames("Heal", "Cleansing", "Hands Of God");
	}
	public boolean heal(Character friend)
	{
		if(friend.isDead()) return false; // heal failed
		else
		{
			if(friend.getHp() + HEALING_POWER > friend.getMaxHp()) friend.setHp(friend.getMaxHp());
			else friend.setHp(friend.getHp() + HEALING_POWER);
		}
		return true;
	}
	public boolean cleansing(Character friend)
	{
		if(friend.isAffectedByStatus()) {
			friend.setBleed(0);
			friend.setBurn(0);
			friend.setFreeze(0);
			friend.setStun(0);
			return true;
		}
		return false;
	}
	
	public boolean handsOfGod(ArrayList<Character> friends)
	{
		boolean success = false;
		for(int i=0;i<friends.size();i++)
		{
			Character temp = friends.get(i);
			if(!temp.isDead()) {
				if(temp.getHp() + ALL_HEAL_HEALING_POWER > temp.getMaxHp()) temp.setHp(temp.getMaxHp());
				else temp.setHp(temp.getHp() + ALL_HEAL_HEALING_POWER);
				success = true;
			}	
		}
		return success;
	}
	@Override
	public String getInstance() {
		return "Healer";
	}
	
}
