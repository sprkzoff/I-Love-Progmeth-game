package logic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application{
	
	ArrayList<Character> player1Characters = new ArrayList<Character>();
	ArrayList<Character> player2Characters = new ArrayList<Character>();
	
	@Override
	public void start(Stage primaryStage) {
		// Create a scene and place a button in the scene
		HBox root = new HBox();
		//root.setSpacing(10);
		//root.setPadding(new Insets(15));
		addCharactersForPlayer1();
		addCharactersForPlayer2();
		CharacterPane characterPane1 = new CharacterPane(player1Characters);
		CharacterPane characterPane2 = new CharacterPane(player2Characters);
		root.getChildren().addAll(characterPane1, characterPane2);
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setTitle("MyJavaFX"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene
		primaryStage.show();
	}

	public void addCharactersForPlayer1() {
		player1Characters.add(new Mage());
		player1Characters.add(new Warrior(20));
		player1Characters.add(new Healer());
	}
	
	public void addCharactersForPlayer2() {
		player1Characters.add(new Assasin());
		player1Characters.add(new Healer());
		player1Characters.add(new Mage());
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
