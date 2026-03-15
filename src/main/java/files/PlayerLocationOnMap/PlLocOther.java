package files.PlayerLocationOnMap;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class PlLocOther extends PlayerLocation {

    private final Point2D point0;
    private final double radius;
    private final int n;

    public PlLocOther(Point2D point0, double radius, int n){
        this.point0 = point0;
        this.radius = radius;
        this.n = n;
    }

    @Override
    public List<Point2D> getPoints() {
        List<Point2D> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            double angle = i * (2 * Math.PI / n);

            double x = point0.getX() + radius * Math.cos(angle);
            double y = point0.getY() + radius * Math.sin(angle);

            result.add(new Point2D(x, y));
        }

        return result;
    }
}
