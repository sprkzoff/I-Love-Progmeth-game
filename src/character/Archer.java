package character;

import java.util.Random;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
		addSkillDescription("Target: yourself\n"
				+ "Set yourself into a focus state for 2 turns, while you're in focus state you have a total of 75% critical chance.");
		addSkillDescription("Archer2");
		addSkillDescription("Attack damage: " + Integer.toString(this.getAtk() / 2) + "\n" + "Target: single enemy\n"
				+ "Has a 50% chance of stunning an enemy, consequencely gives you +2 turns of focus if the stun occured.");
	}

	public boolean focusShot() {
		if (isFocus())
			return false;
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

	public boolean critical() {
		Random random = new Random();
		int ran = random.nextInt(100);
		if (isFocus())
			return ran < FOCUS_CRIT_CHANCE;
		else
			return ran < CRIT_CHANCE;
	}

	public boolean Knockback(Character enemy) {
		Random random = new Random();
		int ran = random.nextInt(100);
		boolean check = minorAttackWithCritical(enemy);
		if (check)
			throwAlert(this, "Critical");
		if (ran > 50) {
			enemy.setStun(1);
			setFocus(this.focus + 2);
			throwAlert(this, "Stun");
			return true;
		} else {
			setFocus(this.focus + 1);
			return false;
		}

	}

	public boolean minorAttackWithCritical(Character enemy) {
		if (critical()) {
			enemy.attackByEnemy(this.getAtk());
			return true;
		} else {
			enemy.attackByEnemy(this.getAtk() / 2);
			return false;
		}
	}

	public boolean attackWithCritical(Character enemy) {
		if (critical()) {
			enemy.attackByEnemy(this.getAtk() * 2);
			return true;
		} else {
			enemy.attackByEnemy(this.getAtk());
			return false;
		}
	}

	@Override
	public String getInstance() {
		return "Archer";
	}
	
	@Override
	public void throwAlert(Character character, String reason) {
		String title = "Caution";
		String headerText = "you're so noob";
		String contentText = "ggez";
		Alert alert = new Alert(AlertType.INFORMATION);
		if (reason == "Critical") {
			title = "Success!";
			headerText = "Critical Hit!";
			contentText = "Your archer has landed a critical hit, nice job!";
		} else if (reason == "Stun") {
			title = "Success!";
			headerText = "Stunned!";
			contentText = "Quick! use this chance to wipe the enemy out";
		}
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
}
