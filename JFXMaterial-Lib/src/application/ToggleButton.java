/*
 *  Copyright 2015 Tareq Si Salem
 *  <tareq.sisalem@gmail.com>
 */
package application;

import application.colorThemes.Theme;
import javafx.animation.TranslateTransition;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


/**
 * @author tareq
 */
public class ToggleButton extends Selectable {
    private boolean slideAnimationFinished = true;
    private TranslateTransition slideTransition;

    public ToggleButton() {
        setUp(null);
    }

    public ToggleButton(MaterialText sideText, double horizontalSpacing) {
        setUp(sideText);
        sideText.setLayoutX(20 + horizontalSpacing);
        sideText.setLayoutY(12);
        getChildren().add(sideText);
    }

    @Override
    void setUp(MaterialText sideText) {
        setPrefSize(30, 30);
        Rectangle baseRect = new Rectangle(30, 15);
        baseRect.setArcHeight(15);
        baseRect.setArcWidth(15);
        baseRect.setFill(Color.GRAY);
        Circle circle = new Circle(12);
        circle.setCenterY(7.5);
        circle.setCenterX(7.5);
        circle.setFill(Color.gray(0.8));
        circle.setEffect(new ElevationEffect(5));
        Circle ripple = new Circle(7.5);
        ripple.translateXProperty().bind(circle.translateXProperty());
        ripple.setCenterY(7.5);
        ripple.setCenterX(7.5);
        ripple.setVisible(false);
        ripple.setOpacity(0.2);
        getChildren().addAll(baseRect, circle, ripple);
        addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (!isSelected && animationFinished && slideAnimationFinished) {
                    animateRipple(ripple, isSelected, 30);
                    animateCircle(circle);
                    circle.setFill(accentColor);
                    if (sideText != null)
                        sideText.setFill(Color.web(Theme.BlueGreen.ACCENT_COLOR));
                    isSelected = !isSelected;
                } else if (animationFinished && slideAnimationFinished) {
                    animateCircle(circle);
                    animateRipple(ripple, isSelected, 30);
                    circle.setFill(Color.gray(0.8));
                    isSelected = !isSelected;
                    if (sideText != null)
                        sideText.setFill(Color.BLACK);
                }
            }
        });
    }

    private void animateCircle(Circle circle) {
        if (slideTransition == null) {
            slideTransition = new TranslateTransition(Duration.millis(500), circle);
        }
        if (isSelected) {
            slideTransition.setByX(-18);
        } else {
            slideTransition.setByX(18);

        }
        slideTransition.setCycleCount(1);
        slideTransition.play();
        slideAnimationFinished = false;
        slideTransition.setOnFinished(e -> slideAnimationFinished = true);
    }
}
