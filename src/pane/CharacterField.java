package pane;

import javafx.scene.control.ProgressBar;

import javax.swing.event.ChangeListener;

import character.Character;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CharacterField extends HBox {
	private TextField name;
	private Text hp;
	private ColoredProgressBar hpBar;
	
	public CharacterField(Character character) {
		name = new TextField(character.getClass().toString());
		hp = new Text(" "+Integer.toString(character.getHp())+"/"+Integer.toString(character.MAX_HP));
		hpBar = new ColoredProgressBar("green-bar",1);
		hpBar.setPrefWidth(100);
		hpBar.setPrefHeight(26);
		getStylesheets().add(getClass().getResource("progress.css").toExternalForm());
		
		getChildren().addAll(name,hpBar,hp);
	} 
}

