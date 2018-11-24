package logic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pane.CharacterPane;
import pane.ControlPane;
import pane.TextPane;

import java.util.ArrayList;

import character.Assassin;
import character.Character;
import character.Healer;
import character.Mage;
import character.Warrior;

public class Main extends Application{
	
	private ArrayList<Character> player1Characters = new ArrayList<Character>();
	private ArrayList<Character> player2Characters = new ArrayList<Character>();
	private CharacterPane characterPane1;
	private CharacterPane characterPane2;
	private ControlPane controlPane;
	private TextPane textPane;
	@Override
	public void start(Stage primaryStage) {
		
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		addCharactersForPlayer1();
		addCharactersForPlayer2();
		characterPane1 = new CharacterPane(player1Characters);
		characterPane2 = new CharacterPane(player2Characters);
		controlPane = new ControlPane(player1Characters.get(0));
		textPane = new TextPane();
		root.add(characterPane1, 0, 0);
		root.add(characterPane2, 1, 0);
		root.add(textPane, 0, 1, 2, 1);
		root.add(controlPane, 0, 2, 3, 2);
		Scene scene = new Scene(root, 1000, 750);
		primaryStage.setTitle("I love Progmeth"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene
		primaryStage.show();
	}

	public void addCharactersForPlayer1() {
		player1Characters.add(new Mage());
		player1Characters.add(new Warrior(20));
		player1Characters.add(new Healer());
	}
	
	public void addCharactersForPlayer2() {
		player2Characters.add(new Assassin());
		player2Characters.add(new Healer());
		player2Characters.add(new Mage());
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
