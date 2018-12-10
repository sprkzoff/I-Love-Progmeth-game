package exception;

import character.Character;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utility.AlertThrowable;

public class FullHpException extends Exception implements AlertThrowable {
	
	public FullHpException() {
		
	}

	@Override
	public void throwAlert(Character character, String reason) {
		String title = "Caution";
		String headerText = "you're so noob";
		String contentText = "ggez";
		Alert alert = new Alert(AlertType.INFORMATION);
		if (reason == "") {
			headerText = "All of your character is still at full HP";
			contentText = "Don't use that skill you fool!";
		} 
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
		
	}
}
