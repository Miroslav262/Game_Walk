package files;

import javafx.geometry.Point2D;

public class Utils {

    public static Point2D fromCordsToPixels(Point2D cords, double xMax, double yMax, double scale) {
        double x = cords.getX()*scale;
        double y = cords.getY()*scale;

        double px = x + xMax/2.0;
        double py = yMax/2.0 - y;

        return new Point2D(px, py);
    }
}
