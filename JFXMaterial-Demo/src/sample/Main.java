package sample;

import application.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        ActionBar actionBar = new ActionBar("JFXMaterial Demo");
        DrawerLayout drawerLayout = new DrawerLayout();
        drawerLayout.getChildren().add(FXMLLoader.load(getClass().getResource("drawerLayout.fxml")));
        Color white = Color.WHITE;
        actionBar.addTab(new TabTitle(new MaterialText("Welcome Screen", white)), FXMLLoader.load(getClass().getResource("Content1.fxml")));
        actionBar.addTab(new TabTitle(new MaterialText("Nice looking card", white)), FXMLLoader.load(getClass().getResource("Content2.fxml")));
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(50));
        vBox.getChildren().add(new ToggleButton(new MaterialText("ToggleButton 1"), 20));
        vBox.getChildren().add(new ToggleButton(new MaterialText("ToggleButton 2"), 20));
        vBox.getChildren().add(new CheckBox(new MaterialText("CheckBox 1"), 20));
        vBox.getChildren().add(new CheckBox(new MaterialText("CheckBox 2"), 20));
        vBox.getChildren().add(new RadioButton(new MaterialText("RadioButton 1"), 20));
        vBox.getChildren().add(new RadioButton(new MaterialText("RadioButton 2"), 20));
        vBox.getChildren().add(new TextInput());
        Button button = new Button(new MaterialText("Material button"));
        button.setPrefSize(150, 40);
        vBox.getChildren().add(button);
        actionBar.addTab(new TabTitle(new MaterialText("Material controls", white)), vBox);
        MaterialRootLayout root = new MaterialRootLayout(actionBar, drawerLayout);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 650));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
