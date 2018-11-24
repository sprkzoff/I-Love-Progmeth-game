package logic;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CharacterPane extends VBox {
	
	public CharacterPane(ArrayList<Character> characters) {
		//setAlignment(Pos.CENTER);
		setPrefWidth(450);
		setPadding(new Insets(15));
		setSpacing(10);
		
		setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		for(int i = 0; i < characters.size(); i++) {
			CharacterField characterField = new CharacterField(characters.get(i));
			//characterField.setAlignment(Pos.CENTER);
			getChildren().addAll(characters.get(i).getImage(), characterField);
		}
	}
}
