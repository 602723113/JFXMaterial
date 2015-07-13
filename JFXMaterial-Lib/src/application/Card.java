package application;

import com.sun.javafx.css.StyleManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Card extends StackPane {

    // Animation control varibales
    private ElevationEffect elevationEffect;
    private int anim_var1;
    private int anim_var2;
    private boolean reverseAnim;
    private boolean isFirstAnimRunning;
    private boolean isSecondAnimRunning;
    private int incrementAmount;

    public Card() {
        super();
        setUpAnimation();
    }

    public Card(Node... n) {
        super(n);
        setUpAnimation();

    }

    public Card(double w, double h) {
        super();
        setUpAnimation();
        setMaxSize(w, h);
        setPrefSize(w, h);
    }

    private void setUpAnimation() {
        elevationEffect = new ElevationEffect();
        setEffect(elevationEffect);
        addEventHandler(
                MouseEvent.MOUSE_CLICKED,
                e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        if (!isFirstAnimRunning) {
                            Timeline timeline = new Timeline(new KeyFrame(Duration
                                    .millis(10), ev -> {
                                isFirstAnimRunning = true;
                                if (anim_var1 >= 20 && !reverseAnim) {
                                    reverseAnim = true;
                                }
                                if (reverseAnim) {
                                    anim_var1 -= 1;
                                } else {
                                    anim_var1 += 1;
                                }
                                elevationEffect.setRadius((anim_var1) + 10);
                                elevationEffect.setOffsetY((anim_var1) / 2 + 5);

                            }));
                            timeline.setOnFinished(value -> {
                                isFirstAnimRunning = false;
                                reverseAnim = false;
                                anim_var1 = 0;
                            });
                            timeline.setCycleCount(40);
                            timeline.play();
                        }
                    }
                });
    }

    public void changeContent(Node n) {
        Circle circle = new Circle();

        if (!isSecondAnimRunning) {
            double minRad;

            if (getPrefHeight() > getPrefWidth()) {
                minRad = getPrefWidth();
            } else {
                minRad = getPrefHeight();
            }

            circle.setRadius(minRad / 3);
            anim_var2 = (int) (minRad / 3);
            incrementAmount = (int) (minRad / 60);
            circle.setCenterX(getPrefWidth() / 2);
            circle.setCenterY(getPrefHeight() / 2);

            getChildren().add(n);
            n.setClip(circle);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                    e -> {
                        isSecondAnimRunning = true;
                        anim_var2 += incrementAmount;
                        circle.setRadius(anim_var2);
                    }));
            timeline.setCycleCount((int) ((minRad * 2.0 / 3.0) / incrementAmount));
            timeline.setAutoReverse(false);
            timeline.setOnFinished(e -> {
                n.setClip(null);
                isSecondAnimRunning = false;
                if (getChildren().size() != 1) {
                    getChildren().remove(0, getChildren().size() - 1);
                }
            });
            timeline.play();
        }

    }

    public void setBackgroundColor(Color c) {
        setBackground(new Background(new BackgroundFill(c, new CornerRadii(5), Insets.EMPTY)));

    }
}
