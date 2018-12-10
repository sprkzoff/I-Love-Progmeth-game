package pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import character.Character;

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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class CharacterPane extends GridPane {
	private ArrayList<Character> characters;
	private ArrayList<CharacterField> characterFields;
	private ArrayList<ImageView> imageViews;
	private ArrayList<ImageView> deadImageViews;
	
	private ArrayList<ImageView> burnImages;
	private ArrayList<ImageView> freezeImages;
	private ArrayList<ImageView> bleedImages;
	private ArrayList<ImageView> stunImages;
	private ArrayList<ImageView> shieldImages;
	
	private static final String BURN_URL = "fire.gif";
	private static final String FREEZE_URL = "freeze.gif";
	private static final String BLEED_URL = "bleed.gif";
	private static final String STUN_URL = "stun.gif";
	private static final String SHIELD_URL = "shield.gif";

	public CharacterPane(ArrayList<Character> characters) {
		setAlignment(Pos.CENTER);
		this.characters = characters;
		getStylesheets().add(getClass().getResource("/logic/application.css").toExternalForm());
		setPrefWidth(450);
		setPadding(new Insets(15));
		setVgap(10);

		characterFields = new ArrayList<CharacterField>();
		imageViews = new ArrayList<ImageView>();
		deadImageViews = new ArrayList<ImageView>();
		burnImages = new ArrayList<ImageView>();
		bleedImages = new ArrayList<ImageView>();
		freezeImages = new ArrayList<ImageView>();
		stunImages = new ArrayList<ImageView>();
		shieldImages = new ArrayList<ImageView>();
		
		addBurnImages();
		addFreezeImages();
		addBleedImages();
		addStunImages();
		addShieldImages();
		
		setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		for (int i = 0; i < characters.size(); i++) {
			CharacterField characterField = new CharacterField(characters.get(i));
			characterFields.add(characterField);
			imageViews.add(characters.get(i).getImage());
			deadImageViews.add(characters.get(i).getDeadImage());
			add(characters.get(i).getDeadImage(), 0, 2 * i);
			add(characters.get(i).getImage(), 0, 2 * i);

			add(characterField, 0, 2 * i + 1);
		}
		
		for(int i = 0; i < 3; i++) {
			add(shieldImages.get(i), 0, 2 * i);
			add(burnImages.get(i), 0, 2 * i);
			add(freezeImages.get(i), 0, 2 * i);
			add(bleedImages.get(i), 0, 2 * i);
			add(stunImages.get(i), 0, 2 * i);
			
			
			bleedImages.get(i).setTranslateX(125);
			stunImages.get(i).setTranslateX(125);
			shieldImages.get(i).setTranslateX(125);
			
			burnImages.get(i).setVisible(false);
			freezeImages.get(i).setVisible(false);
			bleedImages.get(i).setVisible(false);
			stunImages.get(i).setVisible(false);
			shieldImages.get(i).setVisible(false);
		}
	}
	
	public void addBurnImages() {
		for(int i = 0; i < 3; i++) {
			String imagePath = ClassLoader.getSystemResource(BURN_URL).toString();
			Image image = new Image(imagePath, 400, 150, false, false);
			ImageView imageView = new ImageView(image);
			burnImages.add(imageView);
		}
	}
	
	public void addFreezeImages() {
		for(int i = 0; i < 3; i++) {
			String imagePath = ClassLoader.getSystemResource(FREEZE_URL).toString();
			Image image = new Image(imagePath, 150, 150, false, false);
			ImageView imageView = new ImageView(image);
			freezeImages.add(imageView);
		}
	}
	
	public void addBleedImages() {
		for(int i = 0; i < 3; i++) {
			String imagePath = ClassLoader.getSystemResource(BLEED_URL).toString();
			Image image = new Image(imagePath, 150, 150, false, false);
			ImageView imageView = new ImageView(image);
			bleedImages.add(imageView);
		}
	}
	
	public void addStunImages() {
		for(int i = 0; i < 3; i++) {
			String imagePath = ClassLoader.getSystemResource(STUN_URL).toString();
			Image image = new Image(imagePath, 150, 150, false, false);
			ImageView imageView = new ImageView(image);
			stunImages.add(imageView);
		}
	}
	
	public void addShieldImages() {
		for(int i = 0; i < 3; i++) {
			String imagePath = ClassLoader.getSystemResource(SHIELD_URL).toString();
			Image image = new Image(imagePath, 150, 150, false, false);
			ImageView imageView = new ImageView(image);
			shieldImages.add(imageView);
		}
	}
	
	public ArrayList<Character> getCharacters() {
		return this.characters;
	}

	public void update() {
		
		
		
		for (int i = 0; i < 3; i++) {
			if(characters.get(i).getHp() <= 0) {
				characters.get(i).setDead(true);
			}
			if (characters.get(i).isDead()) {
				imageViews.get(i).setVisible(false);
			}
			burnImages.get(i).setVisible(characters.get(i).isBurn());
			freezeImages.get(i).setVisible(characters.get(i).isFreeze());
			bleedImages.get(i).setVisible(characters.get(i).isBleed());
			stunImages.get(i).setVisible(characters.get(i).isStun());
			shieldImages.get(i).setVisible(characters.get(i).isShield());
		}
	}

	public boolean isDefeated() {
		for (int i = 0; i < characters.size(); i++) {
			if (!characters.get(i).isDead())
				return false;
		}
		return true;
	}
	
	public ArrayList<CharacterField> getCharacterFields() {
		return this.characterFields;
	}
}
