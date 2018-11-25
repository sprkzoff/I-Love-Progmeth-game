package pane;

import javafx.scene.control.ProgressBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeListener;

import character.Character;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CharacterField extends HBox{
	private Text name;
	private Text hp;
	private ColoredProgressBar hpBar;
	private Character Owner;
	
	public CharacterField(Character character) {
		Owner = character;
		name = new Text(" "+character.getInstance().toString()+" ");
		hp = new Text(" "+Integer.toString(character.getHp())+"/"+Integer.toString(character.MAX_HP));
		hpBar = new ColoredProgressBar("green-bar",1);
		hpBar.setPrefWidth(100);
		hpBar.setPrefHeight(26);
		getStylesheets().add(getClass().getResource("progress.css").toExternalForm());
		
		getChildren().addAll(name,hpBar,hp);
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
		return Owner;
	}

	public void setOwner(Character owner) {
		Owner = owner;
	}
}

