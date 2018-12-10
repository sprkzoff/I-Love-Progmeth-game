package character;

import java.util.ArrayList;

import exception.NotEnoughManaException;

public class Mage extends Character {
	private int mp;

	public Mage() {
		super(120, 700);
		setImage("resources/mage.jpg");
		setDeadImage("resources/mage_dead.jpg");
		setSkillNames("Freezing Field", "Chaos Meteor", "Detonate");
		this.mp = 100;
		addSkillDescription("Attack Damage: " + Integer.toString((int) (getAtk())) + "\n" + "Target: All enemies\n"
				+ "Make all of your foes freeze for 2 turns.");
		addSkillDescription("Attack Damage: " + Integer.toString((int) (getAtk())) + "\n" + "Target: All enemies\n"
				+ "Make all of your foes burn for 2 turn, if they're already burned, deals 2x damage to them instead.");
		addSkillDescription("Attack Damage: Missing MP x 5\n" + "Target: single enemy\n"
				+ "Detonate the target, making them take damage based on your missing MP.");
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public void freezingField(ArrayList<Character> characters) throws NotEnoughManaException {
		int damage = this.getAtk();
		if (this.getMp() < 40) {
			throw new NotEnoughManaException();
		}
		this.mp -= 40;
		for (int i = 0; i < characters.size(); i++) {
			Character temp = characters.get(i);
			if (!(temp instanceof Mage) && !temp.isDead()) {
				temp.setFreeze(2); // maybe freeze multiple turns
				temp.attackByEnemy(damage);
			}
		}
	}

	public void chaosMeteor(ArrayList<Character> characters) throws NotEnoughManaException { 
		int damage = this.getAtk();
		if (this.getMp() < 60) {
			throw new NotEnoughManaException();
		}
		this.mp -= 60;
		for (int i = 0; i < characters.size(); i++) {
			Character temp = characters.get(i);
			if (!temp.isDead()) {
				temp.setBurn(2);
				if (!temp.isBurn())
					temp.attackByEnemy(damage);
				else
					temp.attackByEnemy(damage * 2);
			}
		}
	}

	public void detonate(Character character) throws NotEnoughManaException { // deal damage based on missing mp
		if (this.getMp() < 15) {
			throw new NotEnoughManaException();
		}
		character.attackByEnemy((100 - this.mp) * 5); // attack by missing mp
		this.mp -= 15;
	}

	public void updateMage() { // call this when game loop occurs
		this.mp += 15;
		if (this.mp > 100)
			this.mp = 100;
	}

	@Override
	public String getInstance() {
		return "Mage";
	}

}
