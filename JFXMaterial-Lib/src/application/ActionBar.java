package application;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;


/**
 * Created by tareq on 7/13/15.
 */
public class ActionBar extends Pane {
    private MaterialText materialText;
    private RotateTransition rotateTransition;
    DrawerLayout drawerLayout;
    private int currentPosition = 0;
    boolean animationFinished = true;

    public boolean isDrawerOpen() {
        return drawerOpen;
    }

    public void setDrawerOpen(boolean drawerOpen) {
        this.drawerOpen = drawerOpen;
    }

    private boolean drawerOpen = false;
    private boolean isAnimationFinished = true;
    private Pane pane;
    private HBox hBox;
    private Line line;
    ArrayList<Pane> contentArrayList = new ArrayList<>();
    Pane firstContent;

    public void setAnimationProgress(double val) {
        pane.setRotate(val * 90);
    }

   public ActionBar(String title) {
        super();
        DoubleProperty heightProperty = new SimpleDoubleProperty();
        hBox = new HBox();
        line = new Line();
        line.setStroke(Color.WHITE);

        line.setStrokeWidth(3);
        rotateTransition = new RotateTransition();
        materialText = new MaterialText("", 17);
        sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (!getScene().getStylesheets().contains(getClass().getResource("application.css").toExternalForm()))
                    getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                newValue.widthProperty().addListener((ob, o, n) -> {
                    setPrefSize(n.doubleValue(), 100);
                    hBox.setPrefWidth(n.doubleValue());

                });
                heightProperty.bind(newValue.heightProperty());
                hBox.setLayoutY(50);
            }
        });
        line.setTranslateY(97);
        hBox.setFillHeight(true);
        hBox.setAlignment(Pos.CENTER);

        getStyleClass().add("material-toolbar");

        setTitle(title);
        getChildren().addAll(hBox, line);
    }

    private void addContent(Pane content) {
        if (contentArrayList.size() != 0) {
            content.translateXProperty().bind(contentArrayList.get(contentArrayList.size() - 1).translateXProperty().add(firstContent.widthProperty()));
        } else {
            firstContent = content;
        }
        contentArrayList.add(content);
        content.setTranslateY(100);
        content.prefWidthProperty().bind(prefWidthProperty());
        content.setPrefHeight(100);
    }

    public ArrayList<Pane> getContent() {
        return contentArrayList;
    }


    public void addTab(TabTitle tabTitle, Pane content) {
        addContent(content);
        tabTitle.setPrefSize(70, 48);
        hBox.getChildren().add(tabTitle);
        tabTitle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    int pos = hBox.getChildren().size() - 1;

                    @Override
                    public void handle(MouseEvent event) {
                        if (animationFinished && (pos - currentPosition) != 0) {
                            animationFinished = false;
                            TranslateTransition transition = new TranslateTransition();
                            transition.setByX(tabTitle.getWidth() * (pos - currentPosition));
                            transition.setDuration(Duration.millis(150 * Math.abs(pos - currentPosition)));
                            transition.setInterpolator(Interpolator.LINEAR);
                            transition.setNode(line);
                            transition.setOnFinished(e -> {
                                currentPosition = pos;
                                animationFinished = true;
                            });
                            transition.play();
                        }
                    }
                }
        );
        for (int i = 0; i < hBox.getChildren().size(); i++) {

            TabTitle tab = (TabTitle) hBox.getChildren().get(i);

            if (i == 2) {
                tab.widthProperty().addListener(e -> {
                    line.setStartX(5);
                    line.setEndX(tab.getWidth() - 5);
                });
                firstContent.translateXProperty().bind(line.translateXProperty().multiply(-contentArrayList.size()));
            }
            tab.prefWidthProperty().bind(hBox.prefWidthProperty().divide(hBox.getChildren().size()));

        }

    }


    public void setTitle(String title) {
        materialText.setText(title);
        materialText.setFill(Color.WHITE);
        materialText.setLayoutX(50);
        materialText.setLayoutY(30);
        ImageView imageMenu = new ImageView(new Image(getClass().getResourceAsStream("assets/menu.png")));
        ImageView imagePrevious = new ImageView(new Image(getClass().getResourceAsStream("assets/previous.png")));
        imagePrevious.setScaleX(0.6);
        imagePrevious.setScaleY(0.6);
        imageMenu.setScaleX(0.6);
        imageMenu.setScaleY(0.6);
        imagePrevious.setRotate(+90);
        imageMenu.setOpacity(1);
        imagePrevious.setOpacity(0);
        pane = new Pane(imagePrevious, imageMenu);
        TabTitle item = new TabTitle(pane);
        getChildren().addAll(materialText, item);
        rotateTransition.setCycleCount(1);
        rotateTransition.setDuration(Duration.millis(500));

        pane.rotateProperty().addListener((observable, oldValue, newValue) -> {
            imageMenu.setOpacity(1.0 + newValue.doubleValue() / 90.0);
            imagePrevious.setOpacity(-newValue.doubleValue() / 90.0);
        });
        item.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (isAnimationFinished) {
                    if (!drawerOpen) {
                        rotateTransition.setNode(pane);
                        rotateTransition.setByAngle(-90);
                        isAnimationFinished = false;
                        rotateTransition.setOnFinished(ev -> {
                            isAnimationFinished = true;
                            drawerLayout.setDrawerOpen(true);
                            drawerOpen = true;
                        });
                        drawerLayout.startOpenAnimation(false);
                    } else {
                        rotateTransition.setByAngle(90);
                        isAnimationFinished = false;
                        rotateTransition.setOnFinished(ev -> {
                            isAnimationFinished = true;
                            drawerOpen = false;
                            drawerLayout.setDrawerOpen(false);
                        });
                        drawerLayout.startOpenAnimation(true);

                    }
                    rotateTransition.play();
                }
            }
        });


    }
}
