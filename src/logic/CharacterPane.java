package logic;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CharacterPane extends VBox{
	
	
	public CharacterPane(ArrayList<Character> characters) {
		setAlignment(Pos.CENTER);
		setPrefWidth(400);
		for(int i = 0; i < characters.size(); i++) {
			getChildren().addAll(characters.get(i).getImage());
		}
	}
}
