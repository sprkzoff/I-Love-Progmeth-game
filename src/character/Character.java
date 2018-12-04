package character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.Main;

public abstract class Character {
	private int atk;
	private int hp;
	private ImageView imageView;
	
	private ArrayList<String> buffs;
	private ArrayList<String> debuffs;
	private boolean dead;
	private int stun;
	private int freeze;
	private int burn;
	private int bleed;
	public final int MAX_HP;
	
	private ArrayList<String> skillNames;
	
	public int getBleed() {
		return bleed;
	}
	public boolean isBleed() {
		return bleed > 0;
	}

	public void setBleed(int bleed) {
		this.bleed = bleed;
	}
	
	public Character() {
		super();
		setImage("resources/default.jpg");
		this.dead = false;
		this.stun = 0;
		this.freeze = 0;
		this.burn = 0;
		this.atk = 0;
		this.hp = 1000;
		this.MAX_HP = this.hp;
		this.buffs = new ArrayList<String>(); //change type of array later
		this.debuffs = new ArrayList<String>();
	}
	
	public void attack(Character enemy)
	{
		enemy.attackByEnemy(this.getAtk());
	}
	public int getStun() {
		return stun;
	}
	public boolean isStun() {
		return stun > 0;
	}
	public void setStun(int stun) {
		this.stun = stun;
	}
	public int getFreeze() {
		return freeze;
	}
	public boolean isFreeze() {
		return freeze > 0;
	}
	public void setFreeze(int freeze) {
		this.freeze = freeze;
	}
	public int getBurn() {
		return burn;
	}
	public boolean isBurn() {
		return burn > 0;
	}
	public void setBurn(int burn) {
		this.burn = burn;
	}
	public Character(int atk,int hp) {
		super();
		setImage("resources/default.jpg");
		this.dead = false;
		this.stun = 0;
		this.freeze = 0;
		this.burn = 0;
		this.atk = atk;
		this.hp = hp;
		this.MAX_HP = this.hp;
		this.buffs = new ArrayList<String>(); //change type of array later
		this.debuffs = new ArrayList<String>();
	}
	
	public Character(int atk,int hp, String imagePath) { //with imagePath
		super();
		setImage(imagePath);
		this.dead = false;
		this.stun = 0;
		this.freeze = 0;
		this.burn = 0;
		this.atk = atk;
		this.hp = hp;
		this.MAX_HP = this.hp;
		this.buffs = new ArrayList<String>(); //change type of array later
		this.debuffs = new ArrayList<String>();
	}
	
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMaxHp() {
		return this.MAX_HP;
	}
	public ArrayList<String> getBuffs() {
		return buffs;
	}
	public void setBuffs(ArrayList<String> buffs) {
		this.buffs = buffs;
	}
	public ArrayList<String> getDebuffs() {
		return debuffs;
	}
	public void setDebuffs(ArrayList<String> debuffs) {
		this.debuffs = debuffs;
	}
	public void setImage(String url) {
		FileInputStream input;
		try {
			input = new FileInputStream(url);
			Image image = new Image(input, 400, 150 , false, false);
			imageView = new ImageView(image);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ImageView getImage() {
		return imageView;
	}
	public void setSkillNames(String s1, String s2, String s3) {
		this.skillNames = new ArrayList<String>();
		skillNames.add(s1);
		skillNames.add(s2);
		skillNames.add(s3);
	}
	public ArrayList<String> getSkillNames(){
		return skillNames;
	}
	public String getInstance() {
		return "Character";
	}
	
	public int attackByEnemy(int damage)
	{
		if(this instanceof Assassin) {
			Random random = new Random();
			int ran = random.nextInt(100);
			if(ran < Assassin.EVADE_CHANCE) {
				Main.throwAlert(this, "Evade");
				return 0;
			}
		}
		if(this.isBleed()) damage *= 2;
		if((this.getHp()-damage <= 0))
		{
			this.setHp(0);
			this.setDead(true);
		}
		else
		{
			this.setHp(this.getHp()-damage);
		}
		return damage;
	}
	
	public boolean isAffectedByStatus() {
		return burn > 0 || freeze > 0 || stun > 0 || bleed > 0;
	}
}
