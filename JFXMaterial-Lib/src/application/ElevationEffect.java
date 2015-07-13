package application;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * Created by tareq on 7/13/15.
 */
public class ElevationEffect extends DropShadow {
  public   ElevationEffect() {
        super();
        setRadius(10);
        setWidth(21);
        setHeight(21);
        setBlurType(BlurType.GAUSSIAN);
        setColor(new Color(0, 0, 0, 0.3));
    }

    public  ElevationEffect(double rad) {
        super();
        setRadius(rad);
        setWidth(21);
        setHeight(21);
        setBlurType(BlurType.GAUSSIAN);
        setColor(new Color(0, 0, 0, 0.3));
    }

}
