package application;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 * Created by tareq on 7/12/15.
 */
public class MaterialText extends Text {
    public MaterialText() {
        super();
        setDefaultFont(13);
    }

    private void setDefaultFont(double textSize) {
        setFont(Font.loadFont(getClass().getResourceAsStream("assets/Roboto-Medium.ttf"), textSize));

    }

    public MaterialText(String text) {
        super(text);
        setDefaultFont(13);
    }

    public MaterialText(String text, double textSize) {
        super(text);
        setDefaultFont(textSize);
    }

    public MaterialText(String text, Color c) {
        super(text);
        setFill(c);
    }
}
