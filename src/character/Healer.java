package character;

import java.util.ArrayList;

public class Healer extends Character {
	private static final int HEALING_POWER = 400;
	private static final int ALL_HEAL_HEALING_POWER = 200;

	public Healer() {
		super(80, 1000);
		setImage("resources/druid.jpg");
		setDeadImage("resources/druid_dead.jpg");
		setSkillNames("Heal", "Cleansing", "Hands Of God");
		addSkillDescription("Healing: " + Integer.toString(HEALING_POWER) + "\n" + "Target: single ally\n"
				+ "Heal a targeted ally.");
		addSkillDescription("Healing: " + Integer.toString(HEALING_POWER / 2) + "\n" + "Target: single ally\n"
				+ "Cleanse all of the debuffs on a single ally and heal them for the moderate amount.");
		addSkillDescription("Healing: " + Integer.toString(ALL_HEAL_HEALING_POWER) + "\n" + "Target: All allies\n"
				+ "Heal all of your allies by a moderate amount.");
	}

	public boolean heal(Character friend) {
		if (friend.isDead())
			return false; // heal failed
		else {
			if (friend.getHp() + HEALING_POWER > friend.getMaxHp())
				friend.setHp(friend.getMaxHp());
			else
				friend.setHp(friend.getHp() + HEALING_POWER);
		}
		return true;
	}

	public boolean cleansing(Character friend) {
		if (friend.getHp() + HEALING_POWER / 2 > friend.getMaxHp())
			friend.setHp(friend.getMaxHp());
		else
			friend.setHp(friend.getHp() + HEALING_POWER / 2);
		if (friend.isAffectedByStatus()) {
			friend.setBleed(0);
			friend.setBurn(0);
			friend.setFreeze(0);
			friend.setStun(0);
			return true;
		}
		return false;
	}

	public boolean handsOfGod(ArrayList<Character> friends) {
		boolean success = false;
		for (int i = 0; i < friends.size(); i++) {
			Character temp = friends.get(i);
			if (!temp.isDead()) {
				if (temp.getHp() + ALL_HEAL_HEALING_POWER > temp.getMaxHp())
					temp.setHp(temp.getMaxHp());
				else
					temp.setHp(temp.getHp() + ALL_HEAL_HEALING_POWER);
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
