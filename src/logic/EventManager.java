package logic;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pane.CharacterPane;
import pane.ControlPane;

public class EventManager {
	private ControlPane ctrlPane;
	private CharacterPane c1;
	private CharacterPane c2;
	
	public EventManager(ControlPane ctrlPane, CharacterPane c1, CharacterPane c2) {
		this.ctrlPane = ctrlPane;
		this.c1 = c1;
		this.c2 = c2;
	}
	
	
}
