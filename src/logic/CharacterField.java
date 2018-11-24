package logic;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CharacterField extends HBox {
	private TextField name;
	private TextField hp;
	
	public CharacterField(Character character) {
		name = new TextField(character.getClass().toString());
		hp = new TextField(Integer.toString(character.getHp()));
		getChildren().addAll(name, hp);
	}
}
