package sample;

import application.Card;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by tareq on 7/13/15.
 */
public class Content2Controller implements Initializable {
    @FXML
    Card card;
    int clicks = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        card.setOnMouseClicked(e -> {

            if (clicks == 0) {
                startAnimating();


            }
            clicks++;

        });
    }

    private void startAnimating() {
        new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                        Rectangle r1 = new Rectangle(card.getWidth(), card.getWidth());
                        r1.setFill(Color.web("#0288D1"));
                        Platform.runLater(() -> {
                            card.changeContent(r1);
                        });
                        Thread.sleep(1000);
                        Rectangle r2 = new Rectangle(card.getWidth(), card.getWidth());
                        r2.setFill(Color.web("#FF5722"));
                        Platform.runLater(() -> {
                            System.out.println("content changed");
                            card.changeContent(r2);
                        });

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
        ).start();
    }
}
