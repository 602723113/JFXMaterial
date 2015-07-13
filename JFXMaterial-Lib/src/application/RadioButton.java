package application;

import application.colorThemes.Theme;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.geom.Rectangle2D;

/**
 * Created by tareq on 7/12/15.
 */
public class RadioButton extends Selectable {
    public RadioButton() {
        setUp(null);
    }

    public RadioButton(MaterialText sideText, double horizontalSpacing) {
        setUp(sideText);
        sideText.setLayoutX(20 + horizontalSpacing);
        sideText.setLayoutY(13);
        getChildren().add(sideText);
    }

    @Override
    void setUp(MaterialText sideText) {
        setPrefSize(30, 30);
        Circle baseCircle = new Circle(defaultRadius / 2);
        baseCircle.setStroke(new Color(0.6, 0.6, 0.6, 1));
        baseCircle.setFill(Color.TRANSPARENT);
        baseCircle.setStrokeWidth(2f);
        baseCircle.setCenterX(defaultRadius / 2);
        baseCircle.setCenterY(defaultRadius / 2);


        Circle smallBaseCircle = new Circle(defaultRadius / 4);
        smallBaseCircle.setFill(new Color(0.6, 0.6, 0.6, 1));
        smallBaseCircle.setVisible(false);
        smallBaseCircle.setCenterX(defaultRadius / 2);
        smallBaseCircle.setCenterY(defaultRadius / 2);

        Circle ripple = new Circle(7.5);
        ripple.setFill(Color.GRAY);
        ripple.setOpacity(0.2);
        ripple.setVisible(false);
        ripple.setCenterX(defaultRadius / 2);
        ripple.setCenterY(defaultRadius / 2);
        getChildren().addAll(baseCircle, smallBaseCircle, ripple);
        addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (!isSelected && animationFinished) {
                    if (sideText != null)
                        sideText.setFill(Color.web(Theme.BlueGreen.ACCENT_COLOR));
                    smallBaseCircle.setFill(Color.web(Theme.BlueGreen.ACCENT_COLOR));
                    baseCircle.setStroke(Color.web(Theme.BlueGreen.ACCENT_COLOR));
                    smallBaseCircle.setVisible(true);
                    animateRipple(ripple, isSelected, 30);
                    isSelected = !isSelected;
                } else if (animationFinished) {
                    animateRipple(ripple, isSelected, 30);
                    if (sideText != null)
                        sideText.setFill(Color.BLACK);
                    smallBaseCircle.setFill(new Color(0.6, 0.6, 0.6, 1));
                    baseCircle.setStroke(new Color(0.6, 0.6, 0.6, 1));
                    smallBaseCircle.setVisible(false);
                    isSelected = !isSelected;
                }
            }
        });
    }
}
