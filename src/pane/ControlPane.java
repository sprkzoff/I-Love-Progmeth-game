package pane;

import character.Character;

import java.util.ArrayList;

import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ControlPane extends GridPane {
	private ArrayList<Button> buttons;
	private ArrayList<String> skillDescriptions;
	private Character character;

	public ControlPane(Character character) {
		this.character = character;
		setAlignment(Pos.CENTER);
		setBorder(new Border(
				new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		buttons = new ArrayList<Button>();

		String skillName = "Attack";
		skillDescriptions = new ArrayList<String>();
		skillDescriptions.add("Attack damage: " + Integer.toString(character.getAtk()));
		Button b = new Button(skillName);
		b.setPrefWidth(400);
		b.getStylesheets().add(getClass().getResource("skillbuttonstyle.css").toExternalForm());
		add(b, 0, 0);
		buttons.add(b);

		for (int i = 0; i < character.getSkillNames().size(); i++) {
			skillDescriptions.add(character.getSkillDescriptions().get(i));
			skillName = character.getSkillNames().get(i);
			Button skillButton = new Button(skillName);
			skillButton.setPrefWidth(400);
			skillButton.getStylesheets().add(getClass().getResource("skillbuttonstyle.css").toExternalForm());
			if (skillName == "Evade" || skillName == "Critical")
				skillButton.setDisable(true);
			buttons.add(skillButton);
			if (i == 0)
				add(skillButton, 1, 0);
			else if (i == 1)
				add(skillButton, 0, 1);
			else if (i == 2)
				add(skillButton, 1, 1);
		}
	}

	public ArrayList<Button> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<Button> buttons) {
		this.buttons = buttons;
	}

	public Character getCharacter() {
		return character;
	}

	public ArrayList<String> getSkillDescriptions() {
		return skillDescriptions;
	}
}
