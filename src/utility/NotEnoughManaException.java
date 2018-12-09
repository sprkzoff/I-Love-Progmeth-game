package utility;

import logic.Main;
import character.Character;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NotEnoughManaException extends Exception implements AlertThrowable {
	private Character character;
	
	public NotEnoughManaException(Character character) {
		this.character = character;
	}
	
	public void throwAlert(Character character, String reason) {
		String title = "Caution";
		String headerText = "you're so noob";
		String contentText = "ggez";
		Alert alert = new Alert(AlertType.INFORMATION);
		if(reason == "") {
			headerText = "Not Enough Mana!!";
			contentText = "You don't have enough mana to cast that spell!";
		}
		alert.setTitle(title);
    	alert.setHeaderText(headerText);
    	alert.setContentText(contentText);
    	alert.showAndWait();
	}
}
