package pane;

import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class TextPane extends StackPane {
	private TextField textField;
	
	public TextPane() {
		setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		textField = new TextField("This is a text field!");
		textField.setEditable(false);
		textField.setPrefWidth(800);
		getChildren().add(textField);
	}

	public TextField getTextField() {
		return textField;
	}

	public void setTextField(String text) {
		this.textField.setText(text);
	}
	
}
