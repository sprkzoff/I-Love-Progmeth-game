 package pane;

import javafx.geometry.Insets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import character.Archer;
import character.Character;
import character.Guardian;
import character.Mage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CharacterField extends GridPane {
	private Text name;
	private Text hp;
	private Text mp;
	private Text focus;
	private Text stun;
	private Text freeze;
	private Text burn;
	private Text bleed;
	private Text shield;
	private Text virtues;
	
	private ColoredProgressBar virtuesBar;
	private ColoredProgressBar hpBar;
	private ColoredProgressBar hpBarMed;
	private ColoredProgressBar hpBarLow;
	private ColoredProgressBar mpBar;
	
	private Character owner;
	
	private static final int ROW_PUSHER = 0;

	public CharacterField(Character character) {
		setPadding(new Insets(10));
		setHgap(5);
		setVgap(5);
		owner = character;
		name = new Text(" " + character.getInstance().toString() + " ");
		hp = new Text(" " + Integer.toString(character.getHp()) + "/" + Integer.toString(character.MAX_HP));
		stun = new Text("Stun: 0");
		freeze = new Text("Freeze: 0");
		burn = new Text("Burn: 0");
		bleed = new Text("Bleed: 0");
		shield = new Text("Shield: 0");
		hpBar = new ColoredProgressBar("green-bar", 1);
		hpBar.setPrefWidth(100);
		hpBar.setPrefHeight(18);
		hpBarMed = new ColoredProgressBar("orange-bar", 1);
		hpBarMed.setPrefSize(100, 18);
		hpBarLow = new ColoredProgressBar("red-bar", 1);
		hpBarLow.setPrefSize(100, 18);
		hpBarMed.setVisible(false);
		hpBarLow.setVisible(false);
		
		add(name, 0, 0 + ROW_PUSHER);
		add(hpBar, 1, 0 + ROW_PUSHER);
		add(hpBarMed, 1, 0 + ROW_PUSHER);
		add(hpBarLow, 1, 0 + ROW_PUSHER);
		add(hp, 2, 0 + ROW_PUSHER);
		add(stun, 3, 0 + ROW_PUSHER);
		add(freeze, 4, 0 + ROW_PUSHER);
		add(burn, 3, 1 + ROW_PUSHER);
		add(bleed, 4, 1 + ROW_PUSHER);
		add(shield, 5, 0 + ROW_PUSHER);
		if (character instanceof Mage) {
			mp = new Text(" 100/100");
			mpBar = new ColoredProgressBar("blue-bar", 1);
			mpBar.setPrefWidth(100);
			mpBar.setPrefHeight(18);
			add(mpBar, 1, 1 + ROW_PUSHER);
			add(mp, 2, 1 + ROW_PUSHER);
		}
		if (character instanceof Archer) {
			focus = new Text("Focus: 0");
			add(focus, 1, 1 + ROW_PUSHER);
		}
		if (character instanceof Guardian) {
			virtues = new Text(" 150/500");
			virtuesBar = new ColoredProgressBar("skyblue-bar", 0.3);
			virtuesBar.setPrefWidth(100);
			virtuesBar.setPrefHeight(18);
			add(virtuesBar, 1, 1 + ROW_PUSHER);
			add(virtues, 2, 1 + ROW_PUSHER);
		}
		getStylesheets().add(getClass().getResource("progress.css").toExternalForm());
		update();
	}

	public Text getName() {
		return name;
	}

	public void setName(Text name) {
		this.name = name;
	}

	public Text getHp() {
		return hp;
	}

	public void setHp(Text hp) {
		this.hp = hp;
	}

	public ColoredProgressBar getHpBar() {
		return hpBar;
	}

	public void setHpBar(ColoredProgressBar hpBar) {
		this.hpBar = hpBar;
	}

	public Character getOwner() {
		return owner;
	}

	public void setOwner(Character owner) {
		this.owner = owner;
	}
	
	public void update() {
		this.hp.setText(" " + Integer.toString(owner.getHp()) + "/" + Integer.toString(owner.MAX_HP));
		hpBar.setProgress((double) owner.getHp() / (double) owner.MAX_HP);
		hpBarMed.setProgress((double) owner.getHp() / (double) owner.MAX_HP);
		hpBarLow.setProgress((double) owner.getHp() / (double) owner.MAX_HP);
		this.stun.setText("Stun: " + Integer.toString(owner.getStun()));
		this.freeze.setText("Freeze: " + Integer.toString(owner.getFreeze()));
		this.burn.setText("Burn: " + Integer.toString(owner.getBurn()));
		this.bleed.setText("Bleed: " + Integer.toString(owner.getBleed()));
		this.shield.setText("Shield: " + Integer.toString(owner.getShield()));

		bleed.setVisible(owner.isBleed());
		burn.setVisible(owner.isBurn());
		freeze.setVisible(owner.isFreeze());
		shield.setVisible(owner.isShield());
		stun.setVisible(owner.isStun());

		if (owner instanceof Guardian) {
			Guardian guardian = (Guardian) owner;
			this.virtues.setText(" " + Integer.toString(guardian.getVirtues()) + "/500");
			virtuesBar.setProgress((double) guardian.getVirtues() / (double) 500);
		}
		
		hpBarMed.setVisible(owner.isMediumHp());
		hpBarLow.setVisible(owner.isLowHp());
	}

	public void updateMp() {
		Mage mage = (Mage) owner;
		this.mp.setText(" " + Integer.toString(mage.getMp()) + "/100");
		mpBar.setProgress((double) mage.getMp() / (double) 100);
	}

	public void updateFocus() {
		Archer archer = (Archer) owner;
		this.focus.setText("Focus: " + Integer.toString(archer.getFocus()));
	}

	public void setBackground(boolean b) {
		if (b)
			setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
		else
			setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
	}
}
