/*
 *  Copyright 2015 Tareq Si Salem
 *  <tareq.sisalem@gmail.com>
 */
package application;

import application.colorThemes.Theme;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author tareq
 */
public class CheckBox extends Selectable {

    private static Image image = new Image(CheckBox.class.getResourceAsStream("assets/check.png"));


    public CheckBox() {
        setUp(null);
    }

    public CheckBox(MaterialText sideText, double horizontalSpacing) {
        setUp(sideText);
        sideText.setLayoutX(20 + horizontalSpacing);
        sideText.setLayoutY(13);
        getChildren().add(sideText);
        setDisabled(true);

    }


    @Override
    void setUp(MaterialText sideText) {
        setPrefSize(30, 30);
        Rectangle rectangle = new Rectangle(defaultRadius, defaultRadius);
        rectangle.setArcWidth(3);
        rectangle.setArcHeight(3);
        rectangle.setStroke(new Color(0.6, 0.6, 0.6, 1));
        rectangle.setStrokeWidth(2f);
        rectangle.setFill(Color.TRANSPARENT);
        ImageView imageView = new ImageView(image);
        imageView.setVisible(false);
        Circle ripple = new Circle(7.5);
        ripple.setFill(Color.GRAY);
        ripple.setCenterX(7.5);
        ripple.setCenterY(7.5);
        ripple.setOpacity(0.2);
        ripple.setVisible(false);
        getChildren().addAll(rectangle, imageView, ripple);
        imageView.setPickOnBounds(false);
        imageView.setScaleX(0.8);
        imageView.setScaleX(0.8);
        addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.print("sdhhds");
            if (e.getButton() == MouseButton.PRIMARY) {
                if (!isSelected && animationFinished) {
                    if (sideText != null)
                        sideText.setFill(Color.web(Theme.BlueGreen.ACCENT_COLOR));
                    imageView.setVisible(true);

                    rectangle.setFill(Color.web(Theme.BlueGreen.ACCENT_COLOR));
                    animateRipple(ripple, isSelected, 30);
                    isSelected = !isSelected;
                } else if (animationFinished) {
                    animateRipple(ripple, isSelected, 30);
                    imageView.setVisible(false);
                    rectangle.setFill(Color.TRANSPARENT);
                    if (sideText != null)
                        sideText.setFill(Color.BLACK);
                    isSelected = !isSelected;

                }
            }
        });
    }


}
