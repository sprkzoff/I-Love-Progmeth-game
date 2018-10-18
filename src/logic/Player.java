package logic;

import java.util.ArrayList;

public class Player extends FightingCharacter{
	int exp;
	int money;
	ArrayList<Item> items;
	Equipment weapon;
	Equipment armor;
	public Player() {
		super();
		this.exp = 0;
		this.money = 0;
		this.items = new ArrayList<Item>();
	}
	// TODO constructor Player(args)
	public void equip(Equipment e) {
		if(e.type == "weapon") {
			this.atk += e.getVal();
			this.weapon = e;
		}
		else if(e.type == "armor") {
			this.def += e.getVal();
			this.armor = e;
		}
	}
	public void takeArmorOff() {
		this.def -= armor.getVal();
		this.armor = null;
	}
	public void takeWeaponOff() {
		this.atk -= weapon.getVal();
		this.weapon = null;
	}
}
