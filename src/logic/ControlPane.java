package logic;

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
	public ControlPane(Character character) {
		setAlignment(Pos.CENTER);
		setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		buttons = new ArrayList<Button>();
		Button b = new Button("Attack");
		b.setPrefWidth(400);
		add(b, 0, 0);
		buttons.add(b);
		for(int i = 0; i < character.getSkillNames().size(); i++) {
			Button skillButton = new Button(character.getSkillNames().get(i));
			skillButton.setPrefWidth(400);
			buttons.add(skillButton);
			if(i == 0) add(skillButton, 1, 0);
			else if(i == 1) add(skillButton, 0, 1);
			else if(i == 2) add(skillButton, 1, 1);
		}
		
	}
}
