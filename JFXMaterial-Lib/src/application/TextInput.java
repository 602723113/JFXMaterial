package application;

import application.colorThemes.Theme;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;


/**
 * Created by tareq on 7/13/15.
 */
public class TextInput extends TextField {
    private Line animationLine, baseLine;
    private Group g;
    private boolean isAnimationFinished = true;

    private double animationVar;

    Timeline sizeAnimation;


    public TextInput() {
        super();
        sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (!getScene().getStylesheets().contains(getClass().getResource("application.css").toExternalForm()))
                    getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            }
        });
        getStyleClass().add("material-text-input");
        animationLine = new Line(0, 0, 0, 0);
        baseLine = new Line(0, 0, 0, 0);
        animationLine.setVisible(false);

        g = new Group(baseLine, animationLine);
        addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (isAnimationFinished) {
                sizeAnimation = new Timeline(new KeyFrame(Duration.millis(7), ev -> {
                    animationLine.setStartX(5 + getWidth() / 2 - (animationVar));
                    animationLine.setEndX(getWidth() / 2 + (animationVar) - 5);
                    animationVar += 3;
                    isAnimationFinished = false;

                }));
                sizeAnimation.setOnFinished(ev -> {
                    animationLine.setVisible(false);
                    animationVar = 0;
                    isAnimationFinished = true;
                });
                sizeAnimation.setCycleCount((int) ((getWidth() / 2) / 3));
                sizeAnimation.play();
                animationLine.setVisible(true);
            }
        });

    }


    @Override
    protected Skin<?> createDefaultSkin() {

        Skin<?> defaultSkin = super.createDefaultSkin();
        getChildren().add(0, g);
        widthProperty().addListener(e -> {
            baseLine.setStartX(10);
            baseLine.setEndX(getWidth() - 10);

        });
        animationLine.setStroke(Color.web(Theme.BlueGreen.ACCENT_COLOR));
        animationLine.setStrokeWidth(3f);
        //   animationLine.setStroke(Color.web(Theme.BlueGreen.ACCENT_COLOR));

        return defaultSkin;
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        getChildren().get(0).setTranslateY(getHeight() / 2);
    }
}
