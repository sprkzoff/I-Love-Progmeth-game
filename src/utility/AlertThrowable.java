package utility;

import character.Character;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public interface AlertThrowable {
	public void throwAlert(Character character, String reason);
}
