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
	private ImageView deadImage;
	
	private ArrayList<String> buffs;
	private ArrayList<String> debuffs;
	private boolean dead;
	private int stun;
	private int freeze;
	private int burn;
	private int bleed;
	private int shield;
	public final int MAX_HP;
	
	private ArrayList<String> skillNames;
	
	public int getBleed() {
		return bleed;
	}
	public boolean isBleed() {
		return bleed > 0;
	}

	public void setBleed(int bleed) {
		if(bleed >= 0)
			this.bleed = bleed;
		else this.bleed = 0;
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
		if(stun >= 0)
			this.stun = stun;
		else this.stun = 0;
	}
	public int getFreeze() {
		return freeze;
	}
	public boolean isFreeze() {
		return freeze > 0;
	}
	public void setFreeze(int freeze) {
		if(freeze >= 0)
			this.freeze = freeze;
		else this.freeze = 0;
	}
	public int getBurn() {
		return burn;
	}
	public boolean isBurn() {
		return burn > 0;
	}
	public void setBurn(int burn) {
		if(burn >= 0)
			this.burn = burn;
		else this.burn = 0;
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
		if(atk >= 0)
			this.atk = atk;
		else this.atk = 0;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		if(hp >=0)
			this.hp = hp;
		else this.hp = 0;
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
			e.printStackTrace();
		}
	}
	public void setDeadImage(String url) {
		FileInputStream input;
		try {
			input = new FileInputStream(url);
			Image image = new Image(input, 400, 150 , false, false);
			deadImage = new ImageView(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public ImageView getDeadImage() {
		return deadImage;
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
		if(this.isBleed()) damage *= 1.5;
		if(this.isShield()) damage /= 2;
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
	
	public boolean isShield() {
		return shield > 0;
	}
	
	public void setShield(int shield) {
		if(shield >= 0)
			this.shield = shield;
		else this.shield = 0;
	}
	
	public int getShield() {
		return shield;
	}
}
