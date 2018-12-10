package exception;

import character.Character;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utility.AlertThrowable;

public class NoDebuffException extends Exception implements AlertThrowable {

	public NoDebuffException() {

	}

	@Override
	public void throwAlert(Character character, String reason) {
		String title = "Caution";
		String headerText = "you're so noob";
		String contentText = "ggez";
		Alert alert = new Alert(AlertType.INFORMATION);
		if (reason == "") {
			headerText = "You can't cleanse that target";
			contentText = "The character you chose has no debuff, no need to cleanse him/her!!!";
		}
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
}
