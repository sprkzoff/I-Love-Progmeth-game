package character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utility.AlertThrowable;

public abstract class Character implements AlertThrowable {
	private int atk;
	private int hp;

	private ImageView imageView;
	private ImageView deadImage;

	private ArrayList<String> skillDescriptions;
	private ArrayList<String> skillNames;

	private boolean dead;

	private int stun;
	private int freeze;
	private int burn;
	private int bleed;
	private int shield;

	public final int MAX_HP;

	public Character() {
		super();
		this.skillDescriptions = new ArrayList<String>();
		setImage("resources/default.jpg");
		this.dead = false;
		this.stun = 0;
		this.freeze = 0;
		this.burn = 0;
		this.atk = 0;
		this.hp = 1000;
		this.MAX_HP = this.hp;
	}

	public Character(int atk, int hp) {
		super();
		this.skillDescriptions = new ArrayList<String>();
		setImage("default.jpg");
		this.dead = false;
		this.stun = 0;
		this.freeze = 0;
		this.burn = 0;
		this.atk = atk;
		this.hp = hp;
		this.MAX_HP = this.hp;
	}

	public int getBleed() {
		return bleed;
	}

	public boolean isBleed() {
		return bleed > 0;
	}

	public void setBleed(int bleed) {
		if (bleed >= 0)
			this.bleed = bleed;
		else
			this.bleed = 0;
	}

	public int getStun() {
		return stun;
	}

	public boolean isStun() {
		return stun > 0;
	}

	public void setStun(int stun) {
		if (stun >= 0)
			this.stun = stun;
		else
			this.stun = 0;
	}

	public int getFreeze() {
		return freeze;
	}

	public boolean isFreeze() {
		return freeze > 0;
	}

	public void setFreeze(int freeze) {
		if (freeze >= 0)
			this.freeze = freeze;
		else
			this.freeze = 0;
	}

	public int getBurn() {
		return burn;
	}

	public boolean isBurn() {
		return burn > 0;
	}

	public void setBurn(int burn) {
		if (burn >= 0)
			this.burn = burn;
		else
			this.burn = 0;
	}

	public boolean isShield() {
		return shield > 0;
	}

	public void setShield(int shield) {
		if (shield >= 0)
			this.shield = shield;
		else
			this.shield = 0;
	}

	public int getShield() {
		return shield;
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
		if (atk >= 0)
			this.atk = atk;
		else
			this.atk = 0;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if (hp >= 0)
			this.hp = hp;
		else
			this.hp = 0;
	}

	public int getMaxHp() {
		return this.MAX_HP;
	}
	
	public boolean isLowHp() {
		return (double)getHp() / (double)getMaxHp() < 0.35;
	}
	
	public boolean isMediumHp() {
		return (double)getHp() / (double)getMaxHp() >= 0.35 && (double)getHp() / (double)getMaxHp() < 0.60;
	}
	
	public boolean isHighHp() {
		return (double)getHp() / (double)getMaxHp() >= 0.60;
	}

	public void attack(Character enemy) {
		enemy.attackByEnemy(this.getAtk());
	}

	public void setImage(String url) {
		String imagePath = ClassLoader.getSystemResource(url).toString();
		Image image = new Image(imagePath, 400, 150, false, false);
		imageView = new ImageView(image);

	}

	public void setDeadImage(String url) {
		String imagePath = ClassLoader.getSystemResource(url).toString();
		Image image = new Image(imagePath, 400, 150, false, false);
		deadImage = new ImageView(image);
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

	public ArrayList<String> getSkillNames() {
		return skillNames;
	}

	public String getInstance() {
		return "Character";
	}

	public int attackByEnemy(int damage) {
		if (this instanceof Assassin) {
			Random random = new Random();
			int ran = random.nextInt(100);
			if (ran < Assassin.EVADE_CHANCE) {
				throwAlert(this, "Evade");
				return 0;
			}
		}
		if (this.isBleed())
			damage *= 1.5;
		if (this.isShield())
			damage /= 2;
		if ((this.getHp() - damage <= 0)) {
			this.setHp(0);
			this.setDead(true);
		} else {
			this.setHp(this.getHp() - damage);
		}
		return damage;
	}

	public boolean isAffectedByStatus() {
		return burn > 0 || freeze > 0 || stun > 0 || bleed > 0;
	}

	public void addSkillDescription(String s) {
		skillDescriptions.add(s);
	}

	public ArrayList<String> getSkillDescriptions() {
		return skillDescriptions;
	}
	
	@Override
	public void throwAlert(Character character, String reason) {
		String title = "Caution";
		String headerText = "you're so noob";
		String contentText = "ggez";
		Alert alert = new Alert(AlertType.INFORMATION);
		if (reason == "Evade") {
			title = "Evade";
			headerText = "Awww";
			contentText = "The assassin has evaded your attack! what a lucky bastard!!";
		}
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
}
