package files.PlayerLocationOnMap;

import javafx.geometry.Point2D;

import java.util.List;

public class PlLoc2 extends PlayerLocation{

    private Point2D point0;
    private double radius;

    public PlLoc2(Point2D point0, double radius){
        this.point0 = point0;
        this.radius = radius;
    }
    @Override
    public List<Point2D> getPoints() {
        return List.of(new Point2D(point0.getX()-radius, point0.getY()),
                new Point2D(point0.getX()+radius, point0.getY()));
    }
}
