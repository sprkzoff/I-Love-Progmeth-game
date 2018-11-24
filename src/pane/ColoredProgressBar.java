package pane;
import javafx.scene.control.ProgressBar;

class ColoredProgressBar extends ProgressBar {
    ColoredProgressBar(String styleClass, double progress) {
        super(progress);
        getStyleClass().add(styleClass);
    }
}
