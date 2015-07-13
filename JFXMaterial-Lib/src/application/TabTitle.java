package application;

import javafx.geometry.Pos;
import javafx.scene.Node;


/**
 * Created by tareq on 7/13/15.
 */
public class TabTitle extends Button {
    public TabTitle(Node n) {
        super(n);
        setUp();
        setAlignment(Pos.CENTER);
    }

    private void setUp() {
        getStyleClass().set(0, "material-item");
    }

}
