package character;

import java.util.ArrayList;

public class Guardian extends Character {
	private int virtues;
	public static int MERCIFUL_HEALING = 100;

	public Guardian() {
		super(200, 1100);
		this.virtues = 150;
		setImage("guardian.jpg");
		setDeadImage("guardian_dead.jpg");
		setSkillNames("Merciful Intervention", "Shield of Courage", "Echo of Liberation");
		addSkillDescription("Healing: " + Integer.toString(MERCIFUL_HEALING) + "\n" + "Target: All allies\n"
				+ "Heal all of your allies by a small amount while cleanse one of their debuffs (Prioritize: Stun > Bleed > Freeze > Burn)");
		addSkillDescription("Attack damage: " + Integer.toString(getAtk()) + "\n" + "Target: 1 enemy and 1 ally\n"
				+ "Damage your opponent, then shield your ally for one turn. Making them take only half damage from direct damage sources.");
		addSkillDescription("Attack Damage: " + Integer.toString((int) (getAtk() / 2))
				+ " + 50% of your current virtues\n" + "Target: All enemies\n"
				+ "Release all of your virtues to damage all enemies, will consume all of your vitrues afterward.");
	}

	public boolean mercifulIntervention(ArrayList<Character> friends) {
		boolean check = false;
		for (int i = 0; i < friends.size(); i++) {
			Character friend = friends.get(i);
			if (!friend.isDead() && (friend.isAffectedByStatus() || friend.getHp() < friend.getMaxHp())) {
				check = true;
				if (friend.getHp() + MERCIFUL_HEALING > friend.getMaxHp()) {
					setVirtues(getVirtues() + friend.getMaxHp() - friend.getHp());
					friend.setHp(friend.MAX_HP);
				} else {
					friend.setHp(friend.getHp() + MERCIFUL_HEALING);
					setVirtues(getVirtues() + MERCIFUL_HEALING);
				}
				if (friend.isStun())
					friend.setStun(0); // priority #1 -> stun
				else if (friend.isBleed())
					friend.setBleed(0); // #2 -> bleed
				else if (friend.isFreeze())
					friend.setFreeze(0); // #3 -> freeze
				else if (friend.isBurn())
					friend.setBurn(0); // #4 -> burn
			}
		}
		return check;
	}

	public void shieldOfCourage(Character enemy, Character friend) { // damage 1 enemy and shield 1 ally for 1 turn
		int damage = this.getAtk();
		enemy.attackByEnemy(damage);
		friend.setShield(1);
	}

	public boolean echoOfLiberation(ArrayList<Character> enemies) { // deal damage based on accumulated healing amount
		if (virtues == 0)
			return false;
		int damage = this.getAtk() / 2 + virtues / 2;
		virtues = 0;

		for (int i = 0; i < enemies.size(); i++) {
			Character temp = enemies.get(i);
			if (!temp.isDead()) {
				temp.attackByEnemy(damage);
			}

		}
		return true;
	}

	public int getVirtues() {
		return virtues;
	}

	public void setVirtues(int virtues) {
		if (virtues > 500)
			virtues = 500;
		this.virtues = virtues;
	}

	@Override
	public String getInstance() {
		return "Guardian";
	}
}
