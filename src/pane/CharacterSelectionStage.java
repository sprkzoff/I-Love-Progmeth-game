package pane;

import java.util.ArrayList;
import java.util.Optional;

import character.Character;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CharacterSelectionStage extends Stage {

	private GridPane root;
	private ArrayList<String> player1Characters;
	private ArrayList<String> player2Characters;

	private Button warriorButton;
	private Button mageButton;
	private Button healerButton;
	private Button assassinButton;
	private Button archerButton;
	private Button guardianButton;

	private TextField textField;
	private TextField player1Field;
	private TextField player2Field;

	private boolean isPlayer1sTurn = true;

	private Warrior warrior = new Warrior();
	private Mage mage = new Mage();
	private Healer healer = new Healer();
	private Assassin assassin = new Assassin();
	private Archer archer = new Archer();
	private Guardian guardian = new Guardian();

	private Label l1;
	private Label l2;

	private int charCount = 0;

	public CharacterSelectionStage() {
		root = new GridPane();
		root.getStylesheets().add(getClass().getResource("/logic/application.css").toExternalForm());
		root.setAlignment(Pos.CENTER_RIGHT);

		player1Characters = new ArrayList<String>();
		player2Characters = new ArrayList<String>();

		warriorButton = new Button();
		mageButton = new Button();
		healerButton = new Button();
		assassinButton = new Button();
		archerButton = new Button();
		guardianButton = new Button();

		setEvent(warriorButton, warrior);
		setEvent(mageButton, mage);
		setEvent(healerButton, healer);
		setEvent(assassinButton, assassin);
		setEvent(archerButton, archer);
		setEvent(guardianButton, guardian);

		warriorButton.setGraphic(warrior.getImage());
		mageButton.setGraphic(mage.getImage());
		healerButton.setGraphic(healer.getImage());
		assassinButton.setGraphic(assassin.getImage());
		archerButton.setGraphic(archer.getImage());
		guardianButton.setGraphic(guardian.getImage());

		textField = new TextField("Player 1's Turn to choose");
		textField.setPrefSize(400, 50);
		textField.setFont(new Font(20));

		l1 = new Label("   Player 1:   ");
		l1.setAlignment(Pos.CENTER);
		l1.setFont(new Font(20));
		l1.setPrefSize(150, 40);
		l2 = new Label("   Player 2:   ");
		l2.setAlignment(Pos.CENTER);
		l2.setFont(new Font(20));
		l2.setPrefSize(150, 40);

		player1Field = new TextField();
		player1Field.setPrefSize(700, 50);
		player1Field.setFont(new Font(20));

		player2Field = new TextField();
		player2Field.setPrefSize(700, 50);
		player2Field.setFont(new Font(20));

		root.add(warriorButton, 0, 0);
		root.add(mageButton, 1, 0);
		root.add(healerButton, 0, 1);
		root.add(assassinButton, 1, 1);
		root.add(archerButton, 0, 2);
		root.add(guardianButton, 1, 2);
		root.add(textField, 0, 3, 2, 3);

		GridPane statusPane = new GridPane();

		statusPane.add(l1, 0, 0);
		statusPane.add(player1Field, 1, 0);
		statusPane.add(l2, 0, 1);
		statusPane.add(player2Field, 1, 1);

		root.add(statusPane, 0, 6, 2, 7);

		Scene scene = new Scene(root);
		setScene(scene);
		setResizable(false);
		setTitle("Character Selection");
	}

	public void setEvent(Button b, Object character) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Alert alert = popUpCharacterDetails(character);
				ButtonType yes = new ButtonType("Yes");
				ButtonType no = new ButtonType("No");

				alert.getButtonTypes().setAll(yes, no);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == yes) {
					if (character instanceof Warrior) {
						process(warrior);
					} else if (character instanceof Mage) {
						process(mage);
					} else if (character instanceof Healer) {
						process(healer);
					} else if (character instanceof Assassin) {
						process(assassin);
					} else if (character instanceof Archer) {
						process(archer);
					} else if (character instanceof Guardian) {
						process(guardian);
					} else {

					}
					if (charCount == 6)
						close();
				}
			}
		});
	}

	private Alert popUpCharacterDetails(Object o) {
		Character character = (Character) o;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		String contentText = character.getInstance() + "'s Skills: | ";
		for (int i = 0; i < character.getSkillNames().size(); ++i) {
			contentText = contentText + character.getSkillNames().get(i) + " | ";
		}
		alert.setTitle("Confirmation");
		alert.setHeaderText("Are you sure you want to pick " + character.getInstance() + "?");
		alert.setContentText(contentText + "\n" + character.getInstance() + "'s Stats:\n" + "Atk: "
				+ Integer.toString(character.getAtk()) + "\n" + "HP: " + Integer.toString(character.getMaxHp()));

		return alert;
	}

	private void throwAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR!!!");
		alert.setHeaderText("You cannot choose the same character");
		alert.setContentText("Please repick your character");
		alert.showAndWait();
	}

	private void process(character.Character character) {
		if (isPlayer1sTurn) {
			if (player1Characters.contains(character.getInstance())) {
				throwAlert();
			} else {
				textField.setText(isPlayer1sTurn ? "Player 2's Turn to choose" : "Player 1's Turn to choose");
				if (isPlayer1sTurn) {
					player1Characters.add(character.getInstance());
					player1Field.setText(player1Field.getText() + character.getInstance() + " ");
				} else {
					player2Characters.add(character.getInstance());
					player2Field.setText(player2Field.getText() + character.getInstance() + " ");
				}
				isPlayer1sTurn = !isPlayer1sTurn;
				charCount++;
			}
		} else {
			if (player2Characters.contains(character.getInstance())) {
				throwAlert();
			} else {
				textField.setText(isPlayer1sTurn ? "Player 2's Turn to choose" : "Player 1's Turn to choose");
				if (isPlayer1sTurn) {
					player1Characters.add(character.getInstance());
					player1Field.setText(player1Field.getText() + character.getInstance() + " ");
				} else {
					player2Characters.add(character.getInstance());
					player2Field.setText(player2Field.getText() + character.getInstance() + " ");
				}
				isPlayer1sTurn = !isPlayer1sTurn;
				charCount++;
			}
		}
	}

	public ArrayList<String> getPlayer1Characters() {
		return player1Characters;
	}

	public ArrayList<String> getPlayer2Characters() {
		return player2Characters;
	}

	public boolean isReady() {
		return charCount == 6;
	}
}
