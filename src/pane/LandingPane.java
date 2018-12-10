package pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LandingPane extends Stage {
	private StackPane root;
	private Button startButton;
	private ImageView bg;
	private boolean click;

	public LandingPane() {
		root = new StackPane();
		click = false;
		root.getStylesheets().add(getClass().getResource("/logic/application.css").toExternalForm());
		root.setAlignment(Pos.CENTER);

		String imagePath = ClassLoader.getSystemResource("landingpic.jpg").toString();
		Image image = new Image(imagePath, 800, 600, false, false);
		bg = new ImageView(image);

		// start button
		startButton = new Button();
		startButton.setTranslateX(0);
		startButton.setTranslateY(250);
		startButton.getStylesheets().add(getClass().getResource("startButtonstyle.css").toExternalForm());
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				click = true;
				close();
			}
		});

		startButton.setText("Start Game!!!");

		root.getChildren().addAll(bg, startButton);
		Scene scene = new Scene(root);
		setScene(scene);
		setResizable(false);
		setTitle("I Love Progmeth");
	}

	public boolean isReady() {
		return click == true;
	}
}
