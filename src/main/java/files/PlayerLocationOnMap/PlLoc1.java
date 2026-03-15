package files.PlayerLocationOnMap;

import javafx.geometry.Point2D;

import java.util.List;

public class PlLoc1 extends PlayerLocation {

    private final Point2D point0;
    private final double radius;

    public PlLoc1(Point2D point0, double radius){
        this.point0 = point0;
        this.radius = radius;
    }

    @Override
    public List<Point2D> getPoints() {
        double angle = Math.PI * 0.5; // 135° — слева сверху

        double x = point0.getX() + radius * Math.cos(angle);
        double y = point0.getY() + radius * Math.sin(angle);

        return List.of(new Point2D(x, y));
    }
}

