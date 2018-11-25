package logic;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pane.ControlPane;

public class EventManager {
	private ControlPane ctrlPane;

	public EventManager(ControlPane ctrlPane) {
		this.ctrlPane = ctrlPane;
	}
	
	public void setUpSkillButtonEvent(ArrayList<Button> SkillButtons) {
		for(int i=0;i<SkillButtons.size();i++)
			SkillButtons.get(i).setOnAction(new SkillButtonEventHandler(SkillButtons.get(i).getText()));
	}
	
	
	private class SkillButtonEventHandler implements EventHandler<ActionEvent> {

		private String name;
		

		public SkillButtonEventHandler(String Skillname) {
			this.name = Skillname;
		}

		@Override
		public void handle(ActionEvent arg0) {
			System.out.println(this.name);
		}
	}
}
