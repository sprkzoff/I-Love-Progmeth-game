package character;

import java.util.ArrayList;

public class Mage extends Character {
	private int mp;
	
	public Mage() {
		super(120, 700);
		setImage("resources/mage.jpg");
		setDeadImage("resources/mage_dead.jpg");
		setSkillNames("Freezing Field", "Chaos Meteor", "Detonate");
		this.mp = 100;
		addDescription("Attack Damage: " + Integer.toString((int)(getAtk())) + "\n"
				+ "Target: All enemies\n"
				+ "Make all of your foes freeze for 2 turns.");
		addDescription("Attack Damage: " + Integer.toString((int)(getAtk())) + "\n"
				+ "Target: All enemies\n"
				+ "Make all of your foes burn for 2 turn, if they're already burned, deals 2x damage to them instead.");
		addDescription("Attack Damage: Missing MP x 5\n"
				+ "Target: single enemy\n"
				+ "Detonate the target, making them take damage based on your missing MP.");
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public boolean freezingField(ArrayList<Character> characters) { //AoE cc with moderate amount of dmg
		int damage = this.getAtk();
		if(this.getMp() < 40) return false;
		this.mp -= 40;
		for(int i = 0; i < characters.size(); i++) {
			Character temp = characters.get(i);
			if(!(temp instanceof Mage) && !temp.isDead()) {
				temp.setFreeze(2); //maybe freeze multiple turns
				temp.attackByEnemy(damage);
			}
		}
		return true;
	}
	public boolean chaosMeteor(ArrayList<Character> characters) { //deal dmg to all enemy and set burn on them, deal additional dmg if they're already burned
		int damage = this.getAtk();
		if(this.getMp() < 60) return false;
		this.mp -= 60;
		for(int i = 0; i < characters.size(); i++) {
			Character temp = characters.get(i);
			if(!temp.isDead()) {
				temp.setBurn(2);
				if(!temp.isBurn()) temp.attackByEnemy(damage);
				else temp.attackByEnemy(damage * 2);
			}
		}
		return true;
	}
	public boolean detonate(Character character) { //deal damage based on missing mp
		if(this.getMp() < 15) return false;
		character.attackByEnemy((100 - this.mp) * 5); // attack by missing mp
		this.mp -= 15;
		return true;
	}
	public void updateMage() { //call this when game loop occurs 
		this.mp += 15;
		if(this.mp > 100) this.mp = 100;
	}
	
	@Override
	public String getInstance() {
		return "Mage";
	}
	
}
