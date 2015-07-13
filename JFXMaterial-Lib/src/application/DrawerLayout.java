package application;

import javafx.animation.TranslateTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by tareq on 7/13/15.
 */
public class DrawerLayout extends Pane {
    private double oldX;
    private double startX;
    private boolean animationFinished = false;
    ActionBar actionBar;

    public void setDrawerOpen(boolean drawerOpen) {
        this.drawerOpen = drawerOpen;
    }

    public boolean isDrawerOpen() {
        return drawerOpen;
    }

    private boolean drawerOpen = false;

    public DrawerLayout() {
        super();
        sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (!getScene().getStylesheets().contains(getClass().getResource("application.css").toExternalForm()))
                    getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                newValue.widthProperty().addListener((ob, o, n) -> {
                    if (getPrefWidth() == -1) {
                        setPrefWidth(n.doubleValue() * 0.4);
                        setLayoutX(-getPrefWidth());
                    }
                });
                newValue.heightProperty().addListener((ob, o, n) -> {
                    setPrefHeight(n.doubleValue() - 50);
                });
                setLayoutY(50);
            }
        });
        translateXProperty().addListener((observable, oldValue, newValue) -> {
            actionBar.setAnimationProgress(-newValue.doubleValue() / getWidth());
        });
        getStyleClass().add("material-drawer");
        addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (oldX == 0)
                startX = e.getX();
            if (oldX != 0 && (e.getX() - oldX) < 0) {
                if (e.getSceneX() < getWidth() / 2) {
                    animationFinished = true;
                }
                setTranslateX(getTranslateX() - 4);
            }
            oldX = e.getX();
        });
        addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            e.consume();
            if (startX != 0) {
                startOpenAnimation(animationFinished);

            }
        });

    }

    public void startOpenAnimation(boolean finished) {

        TranslateTransition transition = new TranslateTransition();
        transition.setNode(this);
        transition.setDuration(Duration.millis(500));

        if (finished) {
            transition.setToX(0);
            transition.setOnFinished(ev -> {
                drawerOpen = false;
                actionBar.setDrawerOpen(false);
                oldX = 0;
                startX = 0;

            });
        } else {
            transition.setToX(getPrefWidth());
            transition.setOnFinished(ev -> {


                drawerOpen = true;
                actionBar.setDrawerOpen(true);
                oldX = 0;
                startX = 0;

            });
        }
        transition.play();
        animationFinished = false;
    }
}
