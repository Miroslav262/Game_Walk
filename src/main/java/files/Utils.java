package files;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Utils {

    public static Color myGreenColor = Color.web("#008A0080");

    public static Point2D fromCordsToPixels(Point2D cords, double xMax, double yMax, double scale) {
        double x = cords.getX()*scale;
        double y = cords.getY()*scale;

        double px = x + xMax/2.0;
        double py = yMax/2.0 - y;

        return new Point2D(px, py);
    }

    public static Image shiftHue(Image src, Color color) {
        int w = (int) src.getWidth();
        int h = (int) src.getHeight();

        WritableImage out = new WritableImage(w, h);
        PixelReader pr = src.getPixelReader();
        PixelWriter pw = out.getPixelWriter();

        double targetHue = color.getHue();
        double targetSat = color.getSaturation();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Color c = pr.getColor(x, y);

                double b0 = c.getBrightness(); // детали (тени/свет)

                Color newColor = Color.hsb(targetHue, targetSat, b0, c.getOpacity());
                pw.setColor(x, y, newColor);
            }
        }

        return out;
    }
}
