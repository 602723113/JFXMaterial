package application;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by tareq on 7/13/15.
 */
public class MaterialRootLayout extends Pane {
    private double oldX, startX;
    private boolean animationFinished;
    private boolean startAnimation;
    private Pane firstContent;
    private boolean translateAnimFinished = true;


    public MaterialRootLayout(ActionBar actionBar, DrawerLayout drawerLayout) {
        super(actionBar, drawerLayout);
        drawerLayout.actionBar = actionBar;
        actionBar.drawerLayout = drawerLayout;
        getChildren().addAll(0, actionBar.getContent());
        firstContent = actionBar.getContent().get(0);
        for (int i = 0; i < actionBar.getContent().size(); i++) {
            actionBar.getContent().get(i).prefHeightProperty().bind(heightProperty().subtract(100));
        }
        addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {

            if (e.getX() < drawerLayout.getPrefWidth() / 2) {
                if (!drawerLayout.isDrawerOpen()) {
                    if (oldX != 0 && e.getX() > oldX && drawerLayout.getTranslateX() <= 0) {
                        if (e.getX() < drawerLayout.getWidth() / 2) {
                            startAnimation = true;
                            animationFinished = true;
                        }
                    }
                    if (oldX == 0)
                        startX = 0;
                    oldX = e.getX();
                }
            }
        });
        addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (startAnimation) {
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(drawerLayout);
                transition.setDuration(Duration.millis(500));
                transition.setOnFinished(ev -> {
                    drawerLayout.setDrawerOpen(true);
                });

                if (animationFinished) {
                    transition.setToX(drawerLayout.getWidth());
                    transition.setOnFinished(ev -> {
                        drawerLayout.setDrawerOpen(true);
                        actionBar.setDrawerOpen(true);
                        oldX = 0;
                        startX = 0;

                    });
                } else {
                    transition.setToX(0);
                    transition.setOnFinished(ev -> {
                        drawerLayout.setDrawerOpen(false);
                        actionBar.setDrawerOpen(false);
                        oldX = 0;
                        startX = 0;
                    });
                }
                transition.play();

                animationFinished = false;
            }

        });

    }
}
