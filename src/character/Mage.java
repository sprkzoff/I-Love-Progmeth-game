package character;

import java.util.ArrayList;

public class Mage extends Character {
	private int mp;
	
	public Mage() {
		super(300, 700);
		setSkillNames("Freezing Field", "Chaos Meteor", "Detonate");
		this.mp = 100;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public void freezingField(ArrayList<Character> characters, int damage) { //AoE cc with moderate amount of dmg
		if(this.getMp() < 25) return;
		this.mp -= 25;
		for(int i = 0; i < characters.size(); i++) {
			Character temp = characters.get(i);
			if(!(temp instanceof Mage) && !temp.isDead()) {
				temp.setFreeze(true); //maybe freeze multiple turns
				temp.attacked_by_enemy(damage);
			}
		}
	}
	public void chaosMeteor(ArrayList<Character> characters, int damage) { //deal dmg to all enemy and set burn on them, deal additional dmg if they're already burned
		if(this.getMp() < 30) return;
		this.mp -= 30;
		for(int i = 0; i < characters.size(); i++) {
			Character temp = characters.get(i);
			if(!temp.isDead()) {
				temp.setBurn(true);
				if(!temp.isBurn()) temp.attacked_by_enemy(damage);
				else temp.attacked_by_enemy(damage * 2);
			}
		}
	}
	public void detonate(Character character) { //denonate away enemy's debuffs to deal damage based on remaining mp
		if(this.getMp() < 15) return;
		this.mp -= 15;
		if(character.isBurn()) {
			character.setBurn(false);
			character.attacked_by_enemy(this.getMp()); // attack by remaining mp
		}
		if(character.isFreeze()) {
			character.setFreeze(false);
			character.attacked_by_enemy(this.getMp());
		}
		
	}
	public void updateMage() { //call this when game loop occurs 
		this.mp += 20;
	}
}
