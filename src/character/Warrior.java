package character;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.Main;

public class Warrior extends Character {
	public static int MAX_HP=1200;
	
	public Warrior() {
		super(200, 1200);
		setImage("resources/warrior.jpg");
		setDeadImage("resources/warrior_dead.jpg");
		setSkillNames("War Cry", "Direct Strike", "Berserk");
		addDescription("Attack Damage: 120\n"
				+ "Target: All enemies\n"
				+ "Release your beastly roar to deal damage to all of your enemies.");
		addDescription("Attack Damage: " + Integer.toString((int)(getAtk() - 70)) + "\n"
				+ "Target: single enemy\n"
				+ "Deal damage at a single enemy, has a 50% chance of stunning the target.");
		addDescription("Attack Damage: Your Missing HP (Capped at 500)\n"
				+ "Target: single enemy\n"
				+ "Deal damage based on your missing HP.");
	}
	
	public void warcry(ArrayList<Character> enemies) //deal every enemy dmg=???
	{
		int damage = 120;
		for(int i=0;i<enemies.size();i++)
		{
			Character temp = enemies.get(i);
			if(!temp.isDead())
			{
				temp.attackByEnemy(damage);
			}
			
		}
	}
	
	public void directStrike(Character enemy) //direct hit enemy with stunning dmg=???
	{
		Random random = new Random();
		int ran = random.nextInt(100);
		int damage = this.getAtk()-70;
		enemy.attackByEnemy(damage);
		if(ran <= 50) {
			enemy.setStun(1);
			throwAlert(this, "Stun");
		}
	}
	
	public void berserk(Character enemy) //plus shield
	{
		int damage = this.getMaxHp() - this.getHp();
		if(damage > 500)
			damage = 500;
		enemy.attackByEnemy(damage);
	}
	
	@Override
	public String getInstance() {
		return "Warrior";
	}
	
	public void throwAlert(Character character, String reason) {
		String title = "Caution";
		String headerText = "you're so noob";
		String contentText = "ggez";
		Alert alert = new Alert(AlertType.INFORMATION);
		
	    headerText = "Your character is " + (reason == "Stun" ? "Stunned" : "Frozen");
	    contentText = character.getInstance() + " is " + (reason == "Stun" ? "stunned" : "frozen") + ". Sadly, he/she won't be able to act this turn.";
		
		alert.setTitle(title);
    	alert.setHeaderText(headerText);
    	alert.setContentText(contentText);
    	alert.showAndWait();
	}
}
