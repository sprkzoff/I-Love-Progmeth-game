package logic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pane.CharacterField;
import pane.CharacterPane;
import pane.CharacterSelectionStage;
import pane.ControlPane;
import pane.LandingPane;
import pane.TextPane;
import utility.AlertThrowable;
import utility.MusicPlayer;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import character.Archer;
import character.Assassin;
import character.Character;
import character.Guardian;
import character.Healer;
import character.Mage;
import character.Warrior;
import exception.FullHpException;
import exception.NoDebuffException;
import exception.NotEnoughManaException;

public class Main extends Application implements AlertThrowable {

	private ArrayList<Character> player1Characters;
	private ArrayList<Character> player2Characters;
	private CharacterPane characterPane1;
	private CharacterPane characterPane2;
	private ArrayList<ControlPane> controlPanes;
	private ArrayList<CharacterField> characterFields;
	private TextPane textPane;
	private CharacterSelectionStage select;
	private LandingPane landing;
	private static MusicPlayer PROOF_OF_A_HERO = new MusicPlayer("resources/001.wav");
	private static MusicPlayer KUSHALA = new MusicPlayer("resources/Kushala.wav");
	private static MusicPlayer VICTORY = new MusicPlayer("resources/victory.wav");
	private static MusicPlayer HEAL = new MusicPlayer("resources/heal.wav");
	private static MusicPlayer FREEZE = new MusicPlayer("resources/freeze.wav");
	private static MusicPlayer EXPLOSION = new MusicPlayer("resources/explosion.wav");
	private static MusicPlayer CRIT = new MusicPlayer("resources/pa.wav");
	private static MusicPlayer ATTACK = new MusicPlayer("resources/attack.wav");
	private static MusicPlayer WARCRY = new MusicPlayer("resources/warcry.wav");
	
	private Stage primaryStage;
	private int turnNumber = 0;

	private final static int FREEZING_CHANCE = 20;
	private final static int BURN_AMOUNT = 50;
	private boolean firstTime = true;

	@Override
	public void start(Stage primaryStage) {
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		if (firstTime) {
			landing = new LandingPane();
			landing.showAndWait();
			if (!landing.isReady())
				System.exit(1);
		}
		firstTime = !firstTime;

		select = new CharacterSelectionStage();
		PROOF_OF_A_HERO.start();
		select.showAndWait();
		if (!select.isReady())
			System.exit(1);
		PROOF_OF_A_HERO.doStop();

		player1Characters = new ArrayList<Character>();
		player2Characters = new ArrayList<Character>();

		addCharactersForPlayer1();
		addCharactersForPlayer2();
		characterPane1 = new CharacterPane(player1Characters);
		characterPane2 = new CharacterPane(player2Characters);

		controlPanes = new ArrayList<ControlPane>();

		for (int i = 0; i < 3; i++) {
			ControlPane c1 = new ControlPane(player1Characters.get(i));
			ControlPane c2 = new ControlPane(player2Characters.get(i));
			controlPanes.add(c1);
			controlPanes.add(c2);
		}

		textPane = new TextPane();
		root.add(characterPane1, 0, 0);
		root.add(characterPane2, 1, 0);
		root.add(textPane, 0, 1, 2, 1);

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				Button b = controlPanes.get(i).getButtons().get(j);
				Character c = controlPanes.get(i).getCharacter();
				String description = controlPanes.get(i).getSkillDescriptions().get(j);
				setEvent(b, b.getText(), c);
				setButtonDescription(b, description);
			}
			controlPanes.get(i).setVisible(false);
			root.add(controlPanes.get(i), 0, 2, 3, 2);
		}

		characterFields = new ArrayList<CharacterField>();
		for (int i = 0; i < 3; i++) {
			characterFields.add(characterPane1.getCharacterFields().get(i));
			characterFields.add(characterPane2.getCharacterFields().get(i));
		}

		controlPanes.get(0).setVisible(true);
		characterPane1.getCharacterFields().get(0).setBackground(true);

		Scene scene = new Scene(root);
		primaryStage.setTitle("I love Progmeth");
		primaryStage.setScene(scene);
		primaryStage.show();
		this.primaryStage = primaryStage;
		KUSHALA.start();
	}

	private void runGameLoop() {
		turnNumber++;
		Character current = controlPanes.get(turnNumber % 6).getCharacter();
		if (current instanceof Mage) {
			Mage mage = (Mage) current;
			if (mage.getMp() < 100)
				mage.updateMage();
		}
		handleCharacterField();
		characterPane1.update();
		characterPane2.update();
		controlPanes.get(turnNumber % 6).setVisible(true);
		controlPanes.get((turnNumber - 1) % 6).setVisible(false);

		if (current.isDead()) {
			handleCharacterField();
			runGameLoop();
		}
		
		if (current.isBleed())
			current.setBleed(current.getBleed() - 1);
		if (current.isShield())
			current.setShield(current.getShield() - 1);
		if (current.isBurn() && !current.isDead()) {
			current.setHp(current.getHp() - BURN_AMOUNT);
			current.setBurn(current.getBurn() - 1);
			
			if (current.getHp() <= 0) {
				current.setHp(0);
				current.setDead(true);
			}
		}
		
		if (current.isStun()) {
			throwAlert(current, "Stun");
			handleCharacterField();
			current.setStun(current.getStun() - 1);
			runGameLoop();
		} else if (current.isFreeze()) {
			current.setFreeze(current.getFreeze() - 1);
			Random r = new Random();
			int ran = r.nextInt(100);
			System.out.println(Integer.toString(ran));
			if (ran <= FREEZING_CHANCE) {
				throwAlert(current, "Frozen");
				handleCharacterField();
				runGameLoop();
			}
		}
		
		handleCharacterField();
		characterPane1.update();
		characterPane2.update();
	}

	private void setButtonDescription(Button b, String description) {
		Tooltip tooltip = new Tooltip(description);
		tooltip.setStyle("-fx-font-family: Arial; " + "-fx-base: #AE3522; " + "-fx-text-fill: orange;");
		b.setTooltip(tooltip);

	}

	private void setEvent(Button b, String skillName, Character character) {

		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Character selectedCharacter;
				boolean success = true;
				if (skillName == "Attack") {
					if (character instanceof Archer) {
						boolean check;
						Archer archer = (Archer) character;
						selectedCharacter = selectTarget(character);
						if (selectedCharacter == null)
							return;
						check = archer.attackWithCritical(selectedCharacter);
						if (archer.isFocus()) {
							archer.setFocus(archer.getFocus() - 1);
						}
						if (check) {
							throwAlert(character, "Critical");
							CRIT.start();
						}
						else 
							ATTACK.start();
					} else {
						selectedCharacter = selectTarget(character);
						if (selectedCharacter == null)
							return;
						selectedCharacter.attackByEnemy(character.getAtk());
						ATTACK.start();
					}

				} else if (character instanceof Warrior) {
					Warrior warrior = (Warrior) character;
					if (skillName == "War Cry") {
						warrior.warcry(getTargetCharacters());
						WARCRY.start();
					} else if (skillName == "Direct Strike") {
						selectedCharacter = selectTarget(character);
						if (selectedCharacter == null)
							return;
						warrior.directStrike(selectedCharacter);
						ATTACK.start();
					} else if (skillName == "Berserk") {
						selectedCharacter = selectTarget(character);
						if (selectedCharacter == null)
							return;
						warrior.berserk(selectedCharacter);
						ATTACK.start();
					}
				}

				else if (character instanceof Mage) {
					Mage mage = (Mage) character;
					if (skillName == "Chaos Meteor") {
						try {
							mage.chaosMeteor(getTargetCharacters());
							EXPLOSION.start();
						} catch (NotEnoughManaException ee) {
							success = false;
							ee.throwAlert(character, "");
						}
					} else if (skillName == "Freezing Field") {
						try {
							mage.freezingField(getTargetCharacters());
							FREEZE.start();
						} catch (NotEnoughManaException ee) {
							success = false;
							ee.throwAlert(character, "");
						}
					} else if (skillName == "Detonate") {
						selectedCharacter = selectTarget(character);
						if (selectedCharacter == null)
							return;
						try {
							mage.detonate(selectedCharacter);
							ATTACK.start();
						} catch (NotEnoughManaException ee) {
							success = false;
							ee.throwAlert(character, "");
						}
					}
				}

				else if (character instanceof Healer) {
					Healer healer = (Healer) character;
					if (skillName == "Heal") {
						selectedCharacter = selectFriendlyTarget(character);
						if (selectedCharacter == null)
							return;
						success = healer.heal(selectedCharacter);
						if (!success)
							throwAlert(character, "Your friend is dead");
						else HEAL.start();
					} else if (skillName == "Cleansing") {
						selectedCharacter = selectFriendlyTarget(character);
						if (selectedCharacter == null)
							return;
						try {
							healer.cleansing(selectedCharacter);
							HEAL.start();
						} catch (NoDebuffException ee) {
							success = false;
							ee.throwAlert(character, "");
						}

							
					} else if (skillName == "Hands Of God") {
						try {
							healer.handsOfGod(getFriendlyTargetCharacters());
							HEAL.start();
						} catch (FullHpException ee) {
							success = false;
							ee.throwAlert(character, "");
						}
					}
				}

				else if (character instanceof Assassin) {
					Assassin assassin = (Assassin) character;
					if (skillName == "Stealth Attack") {
						selectedCharacter = selectTarget(character);
						if (selectedCharacter == null)
							return;
						assassin.stealthAttack(selectedCharacter);
						CRIT.start();
					} else if (skillName == "Bleeding Blade") {
						selectedCharacter = selectTarget(character);
						if (selectedCharacter == null)
							return;
						success = assassin.bleedingBlade(selectedCharacter);
						
						if (!success)
							throwAlert(character, "Is already bleeding");
						else
							CRIT.start();
					}
				}

				else if (character instanceof Archer) {
					Archer archer = (Archer) character;
					if (skillName == "Focus Shot") {
						success = archer.focusShot();
						if (!success)
							throwAlert(character, "Is already focused");
					} else if (skillName == "Knockback") {
						selectedCharacter = selectTarget(character);
						if (selectedCharacter == null)
							return;
						archer.Knockback(selectedCharacter);
						ATTACK.start();
					}
				}

				else if (character instanceof Guardian) {
					Guardian guardian = (Guardian) character;
					if (skillName == "Merciful Intervention") {
						success = guardian.mercifulIntervention(getFriendlyTargetCharacters());
						if (!success)
							throwAlert(character, "No debuff and already at full HP");
						else
							HEAL.start();
					} else if (skillName == "Shield of Courage") {
						selectedCharacter = selectTarget(character);
						Character friendlySelectedCharacter = selectFriendlyTarget(character);
						if (selectedCharacter == null || friendlySelectedCharacter == null)
							return;
						guardian.shieldOfCourage(selectedCharacter, friendlySelectedCharacter);
						ATTACK.start();
					} else if (skillName == "Echo of Liberation") {
						success = guardian.echoOfLiberation(getTargetCharacters());
						if (!success)
							throwAlert(character, "No virtues");
						else
							ATTACK.start();
					}
				}

				if (success)
					runGameLoop();
				checkVictoryAndThrowAlert();
			}

		});
	}

	private void handleCharacterField() {
		for (int i = 0; i < 3; i++) {
			pane.CharacterField c1 = characterPane1.getCharacterFields().get(i);
			pane.CharacterField c2 = characterPane2.getCharacterFields().get(i);
			c1.update();
			c2.update();
			if (c1.getOwner() instanceof Mage) {
				c1.updateMp();
			}
			if (c2.getOwner() instanceof Mage) {
				c2.updateMp();
			}
			if (c1.getOwner() instanceof Archer)
				c1.updateFocus();
			if (c2.getOwner() instanceof Archer)
				c2.updateFocus();
		}
		characterFields.get(turnNumber % 6).setBackground(true);
		characterFields.get((turnNumber - 1) % 6).setBackground(false);
	}
	
	@Override
	public void throwAlert(Character character, String reason) {
		String title = "Caution";
		String headerText = "you're so noob";
		String contentText = "ggez";
		Alert alert = new Alert(AlertType.INFORMATION);
		if (reason == "Your friend is dead") {
			headerText = "You can't heal that target";
			contentText = "The character you chose is already dead, please choose to heal someone else";
		} else if (reason == "Is already bleeding") {
			headerText = "The target is already bleeded";
			contentText = "The character you chose is already bleed, please choose to attack someone else";
		} else if (reason == "Is already focused") {
			headerText = "Already Focused";
			contentText = "This character is already focused!!!";
		} else if (reason == "Evade") {
			title = "Evade";
			headerText = "Awww";
			contentText = "The assassin has evaded your attack! what a lucky bastard!!";
		} else if (reason == "Critical") {
			title = "Success!";
			headerText = "Critical Hit!";
			contentText = "Your archer has landed a critical hit, nice job!";
		} else if (reason == "Stun") {
			title = "Success!";
			headerText = "Stunned!";
			contentText = "Quick! use this chance to wipe the enemy out";
		} else if (reason == "No debuff and already at full HP") {
			headerText = "Am I a joke to you?";
			contentText = "Why would you wanna heal and cleanse debuff when all of your teammate's HP is already full and doesn't affected by any debuff at all!!!";
		} else if (reason == "No virtues") {
			headerText = "Not enough virtues";
			contentText = "You have no virtues to perform this attack!";
		} else {
			headerText = "Your character is " + (reason == "Stun" ? "Stunned" : "Frozen");
			contentText = character.getInstance() + " is " + (reason == "Stun" ? "stunned" : "frozen")
					+ ". Sadly, he/she won't be able to act this turn.";
		}
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

	private ArrayList<Character> getTargetCharacters() {
		return turnNumber % 2 == 1 ? player1Characters : player2Characters;
	}

	private ArrayList<Character> getFriendlyTargetCharacters() {
		return turnNumber % 2 == 0 ? player1Characters : player2Characters;
	}

	private Character selectTarget(Character character) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Select your HOSTILE target");
		alert.setHeaderText("Choose your option.");
		alert.setContentText("Which enemy do you prefer to use your skill on?");

		ArrayList<Character> targets = new ArrayList<Character>();

		if (turnNumber % 2 == 0) { // player 1's turn
			targets = player2Characters;
		} else {
			targets = player1Characters;
		}

		ButtonType t1 = new ButtonType(targets.get(0).getInstance());
		ButtonType t2 = new ButtonType(targets.get(1).getInstance());
		ButtonType t3 = new ButtonType(targets.get(2).getInstance());

		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(t1, t2, t3, cancel);
		alert.getDialogPane().lookupButton(t1).setDisable(targets.get(0).isDead());
		alert.getDialogPane().lookupButton(t2).setDisable(targets.get(1).isDead());
		alert.getDialogPane().lookupButton(t3).setDisable(targets.get(2).isDead());
		Optional<ButtonType> result = alert.showAndWait();

		Character returnCharacter;
		if (result.get() == t1)
			returnCharacter = targets.get(0);
		else if (result.get() == t2)
			returnCharacter = targets.get(1);
		else if (result.get() == t3)
			returnCharacter = targets.get(2);
		else
			return null;

		return returnCharacter;
	}

	private Character selectFriendlyTarget(Character character) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Select your FRIENDLY target");
		alert.setHeaderText("Choose your option.");
		alert.setContentText("Which ally do you prefer to use your skill on?");

		ArrayList<Character> targets = new ArrayList<Character>();

		if (turnNumber % 2 == 0) { // player 1's turn
			targets = player1Characters;
		} else {
			targets = player2Characters;
		}
		ButtonType t1 = new ButtonType(targets.get(0).getInstance());
		ButtonType t2 = new ButtonType(targets.get(1).getInstance());
		ButtonType t3 = new ButtonType(targets.get(2).getInstance());
		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(t1, t2, t3, cancel);
		alert.getDialogPane().lookupButton(t1).setDisable(targets.get(0).isDead());
		alert.getDialogPane().lookupButton(t2).setDisable(targets.get(1).isDead());
		alert.getDialogPane().lookupButton(t3).setDisable(targets.get(2).isDead());
		Optional<ButtonType> result = alert.showAndWait();

		Character returnCharacter;
		if (result.get() == t1)
			returnCharacter = targets.get(0);
		else if (result.get() == t2)
			returnCharacter = targets.get(1);
		else if (result.get() == t3)
			returnCharacter = targets.get(2);
		else
			return null;

		return returnCharacter;
	}

	public void addCharactersForPlayer1() {
		for (int i = 0; i < select.getPlayer1Characters().size(); i++) {
			if (select.getPlayer1Characters().get(i) == "Warrior")
				player1Characters.add(new Warrior());
			if (select.getPlayer1Characters().get(i) == "Mage")
				player1Characters.add(new Mage());
			if (select.getPlayer1Characters().get(i) == "Assassin")
				player1Characters.add(new Assassin());
			if (select.getPlayer1Characters().get(i) == "Healer")
				player1Characters.add(new Healer());
			if (select.getPlayer1Characters().get(i) == "Archer")
				player1Characters.add(new Archer());
			if (select.getPlayer1Characters().get(i) == "Guardian")
				player1Characters.add(new Guardian());
		}
	}

	public void addCharactersForPlayer2() {
		for (int i = 0; i < select.getPlayer2Characters().size(); i++) {
			if (select.getPlayer2Characters().get(i) == "Warrior")
				player2Characters.add(new Warrior());
			if (select.getPlayer2Characters().get(i) == "Mage")
				player2Characters.add(new Mage());
			if (select.getPlayer2Characters().get(i) == "Assassin")
				player2Characters.add(new Assassin());
			if (select.getPlayer2Characters().get(i) == "Healer")
				player2Characters.add(new Healer());
			if (select.getPlayer2Characters().get(i) == "Archer")
				player2Characters.add(new Archer());
			if (select.getPlayer2Characters().get(i) == "Guardian")
				player2Characters.add(new Guardian());
		}
	}

	private boolean player1isLost() {
		for (int i = 0; i < 3; i++) {
			Character character = characterPane1.getCharacters().get(i);
			if (!character.isDead())
				return false;
		}
		return true;
	}

	private boolean player2isLost() {
		for (int i = 0; i < 3; i++) {
			Character character = characterPane2.getCharacters().get(i);
			if (!character.isDead())
				return false;
		}
		return true;
	}

	private void checkVictoryAndThrowAlert() {
		if (player1isLost() || player2isLost()) {
			KUSHALA.doStop();
			VICTORY.start();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert!");
			alert.setHeaderText("We have a winner!");
			alert.setContentText(player1isLost() ? "CONGRATULATIONS!! Player 2 is the one who really loves PROGMETH!! \nWant to play again?"
					: "CONGRATULATIONS!! Player 1 is the one who really loves PROGMETH!! \nWant to play again?");

			ButtonType ok = new ButtonType("Ok");
			ButtonType cancel = new ButtonType("Cancel");

			alert.getButtonTypes().setAll(ok, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ok) {
				VICTORY.doStop();
				this.primaryStage.close();
				start(new Stage());
			} else if (result.get() == cancel) {
				System.out.println("End Credits!");
				this.primaryStage.close();
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public ArrayList<ControlPane> getControlPanes() {
		return controlPanes;
	}
}