package character;

import java.util.ArrayList;

public class Guardian extends Character {
	private static int MAX_HP=1200;
	private int virtues;
	public static int MERCIFUL_HEALING = 100;
	public Guardian() {
		super(200,1100);
		this.virtues = 150;
		setImage("resources/guardian.jpg");
		setDeadImage("resources/guardian_dead.jpg");
		setSkillNames("Merciful Intervention", "Shield of Courage", "Echo of Liberation");
	}
	
	public boolean mercifulIntervention(ArrayList<Character> friends) // removes 1 debuff and heal each unit = MERCIFUL_HEALING
	{
		boolean check = false;
		for(int i = 0; i < friends.size(); i++) {
			Character friend = friends.get(i);
			if(!friend.isDead() && (friend.isAffectedByStatus() || friend.getHp() < friend.getMaxHp())) {
				check = true;
				if(friend.getHp() + MERCIFUL_HEALING > friend.getMaxHp()) {
					setVirtues(getVirtues() + friend.getMaxHp() - friend.getHp());
					friend.setHp(friend.MAX_HP);
				}
				else {
					friend.setHp(friend.getHp() + MERCIFUL_HEALING);
					setVirtues(getVirtues()+ MERCIFUL_HEALING);
				}
				if(friend.isStun()) friend.setStun(0); // priority #1 -> stun
				else if(friend.isBleed()) friend.setBleed(0); // #2 -> bleed
				else if(friend.isFreeze()) friend.setFreeze(0); // #3 -> freeze
				else if(friend.isBurn()) friend.setBurn(0); // #4 -> burn
			}
		}
		return check;
	}
	
	public void shieldOfCourage(Character enemy, Character friend) // damage 1 enemy and shield 1 ally for 1 turn
	{
		int damage = this.getAtk();
		enemy.attackByEnemy(damage);
		friend.setShield(1);
	}
	
	public boolean echoOfLiberation(ArrayList<Character> enemies) // deal damage based on accumulated healing amount
	{
		if(virtues == 0) return false;
		int damage = this.getAtk() / 2 + virtues / 3;
		virtues = 0;
		
		for(int i=0;i<enemies.size();i++)
		{
			Character temp = enemies.get(i);
			if(!temp.isDead())
			{
				temp.attackByEnemy(damage);
			}
			
		}
		return true;
	}
	
	public int getVirtues() {
		return virtues;
	}
	
	public void setVirtues(int virtues) {
		if(virtues > 500) virtues = 500;
		this.virtues = virtues;
	}

	@Override
	public String getInstance() {
		return "Guardian";
	}
}
