package pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

import character.Archer;
import character.Assassin;
import character.Guardian;
import character.Healer;
import character.Mage;
import character.Warrior;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LandingPane extends Stage {
	private StackPane root;
	private Button startButton;
	private ImageView bg;
	private boolean click;
	public LandingPane() {
		root = new StackPane();
		click=false;
		root.getStylesheets().add(getClass().getResource("\\..\\logic\\application.css").toExternalForm());
		root.setAlignment(Pos.CENTER_RIGHT);
		FileInputStream input;
		try {
			input = new FileInputStream("resources\\landingpic.jpg");
			Image image = new Image(input, 599, 449 , false, false);
			bg = new ImageView(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//start button
		startButton = new Button();
		startButton.setTranslateX(-230);
		startButton.setTranslateY(188);
		startButton.getStylesheets().add(getClass().getResource("startButtonstyle.css").toExternalForm());
		startButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	click=true;
		    	close();
		    }
		});
		
		startButton.setText("Start Game!!!");
		
		
		root.getChildren().addAll(bg,startButton);
		Scene scene = new Scene(root);
		setScene(scene);
		setResizable(false);
		setTitle("I Love Progmeth");
	}
	
	public boolean isReady() {
		return click == true;
	}
}
