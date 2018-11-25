package character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Character {
	private int atk;
	private int hp;
	private ImageView imageView;
	
	private ArrayList<String> buffs;
	private ArrayList<String> debuffs;
	private boolean dead;
	private boolean stun;
	private boolean freeze;
	private boolean burn;
	private boolean bleed;
	public final int MAX_HP;
	
	private ArrayList<String> skillNames;
	
	public boolean isBleed() {
		return bleed;
	}

	public void setBleed(boolean bleed) {
		this.bleed = bleed;
	}
	
	public Character() {
		super();
		setImage("resources/default.jpg");
		this.dead = false;
		this.stun = false;
		this.freeze = false;
		this.burn = false;
		this.atk = 0;
		this.hp = 1000;
		this.MAX_HP = this.hp;
		this.buffs = new ArrayList<String>(); //change type of array later
		this.debuffs = new ArrayList<String>();
	}
	
	
	
	public void attack(Character enemy)
	{
		enemy.attacked_by_enemy(this.getAtk());
	}
	
	public boolean isStun() {
		return stun;
	}
	public void setStun(boolean stun) {
		this.stun = stun;
	}
	public boolean isFreeze() {
		return freeze;
	}
	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}
	public boolean isBurn() {
		return burn;
	}
	public void setBurn(boolean burn) {
		this.burn = burn;
	}
	public Character(int atk,int hp) {
		super();
		setImage("resources/default.jpg");
		this.dead = false;
		this.stun = false;
		this.freeze = false;
		this.burn = false;
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
		this.stun = false;
		this.freeze = false;
		this.burn = false;
		this.atk = atk;
		this.hp = hp;
		this.MAX_HP = this.hp;
		this.buffs = new ArrayList<String>(); //change type of array later
		this.debuffs = new ArrayList<String>();
	}
	
	public void attacked_by_enemy(int damage)
	{
		if(this.getHp()-damage <= 0)
		{
			this.setHp(0);
			this.setDead(true);
		}
		else
		{
			this.setHp(this.getHp()-damage);
		}
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
}
