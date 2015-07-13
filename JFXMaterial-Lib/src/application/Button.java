package application;

import application.colorThemes.Theme;
import com.sun.javafx.css.StyleManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Created by tareq on 7/13/15.
 */
public class Button extends javafx.scene.control.Button {
    boolean animationFinished = true;
    double radiusSize = 0;
    double rippleSpeed = 3; //0.5 px/10ms

    public Button() {
        super();
        setUp();
    }

    public  Button(String text) {
        super("", new MaterialText(text));
        setUp();
    }

    public  Button(Node n) {

        super("", n);
        setUp();
    }

    private void setUp() {
        sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (!getScene().getStylesheets().contains(getClass().getResource("application.css").toExternalForm()))
                    getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            }
        });
        getStyleClass().add("material-button");
        addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Rectangle r = new Rectangle(getWidth(), getHeight());

            prefHeightProperty().addListener(ev -> r.setHeight(getHeight()));
            Circle ripple = new Circle(7.5);
            ripple.setClip(r);
            ripple.setFill(Color.GRAY);
            ripple.setCenterX(e.getX());
            ripple.setCenterY(e.getY());
            ripple.setOpacity(0.2);
            ripple.setVisible(false);
            animateRipple(ripple, true, 50);
            getChildren().add(0, ripple);
        });
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
}
