package files.PlayerLocationOnMap;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class PlLoc4 extends PlayerLocation {

    private final Point2D point0;
    private final double radius;

    public PlLoc4(Point2D point0, double radius){
        this.point0 = point0;
        this.radius = radius;
    }

    @Override
    public List<Point2D> getPoints() {
        List<Point2D> result = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            double angle = Math.PI / 4 + i * (Math.PI / 2);

            double x = point0.getX() + radius * Math.cos(angle);
            double y = point0.getY() + radius * Math.sin(angle);

            result.add(new Point2D(x, y));
        }

        return result;
    }
}
