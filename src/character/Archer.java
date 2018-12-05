package character;

import java.util.Random;

import logic.Main;

public class Archer extends Character {
	private int focus;
	private static final int FOCUS_CRIT_CHANCE = 75;
	private static final int CRIT_CHANCE = 30;
	public Archer() {
		super(200, 800);
		this.focus = 0;
		setImage("resources/archer.jpg");
		setDeadImage("resources/archer_dead.jpg");
		setSkillNames("Focus Shot", "Critical", "Knockback");
	}
	

	public boolean focusShot()
	{
		if(isFocus()) return false;
		else {
			setFocus(2);
			return true;
		}
	}
	
	public int getFocus() {
		return focus;
	}
	
	public boolean isFocus() {
		return focus > 0;
	}


	public void setFocus(int focus) {
		this.focus = focus;
	}


	public boolean critical() //passive critical
	{
		Random random = new Random();
		int ran = random.nextInt(100);
		if(isFocus()) return ran < FOCUS_CRIT_CHANCE;
		else return ran < CRIT_CHANCE; 
	}
	
	public boolean Knockback (Character enemy)
	{
		Random random = new Random();
		int ran = random.nextInt(100);
		boolean check = minorAttackWithCritical(enemy);
		if(check) Main.throwAlert(this, "Critical");
		if(ran > 50) {
			enemy.setStun(1);
			setFocus(this.focus + 2);
			Main.throwAlert(this, "Stun");
			return true;
		}
		else {
			setFocus(this.focus + 1);
			return false;
		}
		
	}
	
	
	public boolean minorAttackWithCritical(Character enemy) {
		if(critical())
		{
			enemy.attackByEnemy(this.getAtk());
			return true;
		}
		else
		{
			enemy.attackByEnemy(this.getAtk()/2);
			return false;
		}
	}
	
	public boolean attackWithCritical(Character enemy) {
		if(critical())
		{
			enemy.attackByEnemy(this.getAtk()*2);
			return true;
		}
		else
		{
			enemy.attackByEnemy(this.getAtk());
			return false;
		}
	}

	@Override
	public String getInstance() {
		return "Archer";
	}
	
	
}
