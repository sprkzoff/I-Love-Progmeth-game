package pane;
import java.util.ArrayList;
import java.util.Optional;

import character.Assassin;
import character.Character;
import character.Healer;
import character.Mage;
import character.Warrior;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CharacterPane extends GridPane {
	private ArrayList<Character> characters;
	private ArrayList<CharacterField> characterFields;
	private ArrayList<ImageView> imageViews;
	private ArrayList<ImageView> deadImageViews;
	public CharacterPane(ArrayList<Character> characters) {
		//setAlignment(Pos.CENTER);
		this.characters = characters;
		getStylesheets().add(getClass().getResource("\\..\\logic\\application.css").toExternalForm());
		setPrefWidth(450);
		setPadding(new Insets(15));
		setVgap(10);
		
		characterFields = new ArrayList<CharacterField>();
		imageViews = new ArrayList<ImageView>();
		deadImageViews = new ArrayList<ImageView>();
		
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		for(int i = 0; i < characters.size(); i++) {
			CharacterField characterField = new CharacterField(characters.get(i));
			characterFields.add(characterField);
			imageViews.add(characters.get(i).getImage());
			deadImageViews.add(characters.get(i).getDeadImage());
			//characterField.setAlignment(Pos.CENTER);
			add(characters.get(i).getDeadImage(), 0, 2 * i);
			add(characters.get(i).getImage(), 0, 2 * i);
			
			add(characterField, 0, 2 * i + 1);
		}
	}
	
	public ArrayList<Character> getCharacters() {
		return this.characters;
	}
	
	public void update() {
		for(int i = 0; i < 3; i++) {
			if(characters.get(i).isDead()) {
				imageViews.get(i).setVisible(false);
			}
		}
	}
	
	public boolean isDefeated() {
		for(int i = 0; i < characters.size(); i++) {
			if(!characters.get(i).isDead()) return false;
		}
		return true;
	}
	
	public ArrayList<CharacterField> getCharacterFields(){
		return this.characterFields;
	}
}
