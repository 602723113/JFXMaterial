package application;

import application.colorThemes.Theme;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


/**
 * Created by tareq on 7/12/15.
 */
abstract class Selectable extends Pane {
    double defaultRadius = 17;
    boolean isSelected = false;
    boolean animationFinished = true;
    double radiusSize = 8.5;
    double rippleSpeed = 0.5; //0.5 px/10ms
    Color accentColor = Color.web(Theme.BlueGreen.ACCENT_COLOR);

    public boolean isSelected() {
        return isSelected;
    }

    void animateRipple(Circle ripple, boolean selected, int spread) {
        ripple.setVisible(true);
        if (selected) {
            ripple.setFill(Color.web(Theme.BlueGreen.ACCENT_COLOR));
        } else {
            ripple.setFill(Color.GRAY);

        }
        Timeline anim = new Timeline(new KeyFrame(Duration.millis(10), ev -> {
            ripple.setRadius(radiusSize += rippleSpeed);
        }));
        anim.setCycleCount(spread);
        anim.setOnFinished(ev2 -> {
            animationFinished = true;
            radiusSize = 7.5;
            ripple.setRadius(radiusSize);
            ripple.setVisible(false);
        });
        anim.play();
    }

    abstract void setUp(MaterialText sideText);
}
