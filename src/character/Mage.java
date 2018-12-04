package character;

import java.util.ArrayList;

public class Mage extends Character {
	private int mp;
	
	public Mage() {
		super(150, 700);
		setImage("resources/mage.jpg");
		setSkillNames("Freezing Field", "Chaos Meteor", "Detonate");
		this.mp = 100;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public boolean freezingField(ArrayList<Character> characters) { //AoE cc with moderate amount of dmg
		int damage = this.getAtk();
		if(this.getMp() < 25) return false;
		this.mp -= 25;
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
		if(this.getMp() < 101) return false;
		this.mp -= 101;
		for(int i = 0; i < characters.size(); i++) {
			Character temp = characters.get(i);
			if(!temp.isDead()) {
				temp.setBurn(3);
				if(!temp.isBurn()) temp.attackByEnemy(damage);
				else temp.attackByEnemy(damage * 2);
			}
		}
		return true;
	}
	public boolean detonate(Character character) { //denonate away enemy's debuffs to deal damage based on remaining mp
		if(this.getMp() < 15) return false;
		if(character.isBurn()) {
			character.setBurn(0);
			character.attackByEnemy(this.getMp() * 3); // attack by remaining mp
		}
		if(character.isFreeze()) {
			character.setFreeze(0);
			character.attackByEnemy(this.getMp() * 3);
		}
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
