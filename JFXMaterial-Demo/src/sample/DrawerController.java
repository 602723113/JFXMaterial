package sample;

import application.MaterialText;
import application.TabTitle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by tareq on 7/13/15.
 */
public class DrawerController implements Initializable {
    @FXML
    VBox vbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        {
            TabTitle account = new TabTitle(new MaterialText("Gmail account"));
            account.setPrefSize(240, 50);
            account.setPadding(new Insets(20));
            account.setTextFill(Color.WHITE);
            account.setAlignment(Pos.CENTER_LEFT);
            vbox.getChildren().add(account);
        }
        {
            TabTitle account = new TabTitle(new MaterialText("Options"));
            account.setPrefSize(240, 50);
            account.setTextFill(Color.GREY);
            account.setPadding(new Insets(20));

            account.setAlignment(Pos.CENTER_LEFT);
            vbox.getChildren().add(account);
        }
        {
            TabTitle account = new TabTitle(new MaterialText("About"));
            account.setPrefSize(240, 50);
            account.setTextFill(Color.GREY);
            account.setPadding(new Insets(20));

            account.setAlignment(Pos.CENTER_LEFT);
            vbox.getChildren().add(account);
        }
    }
}
