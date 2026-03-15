package files.PlayerLocationOnMap;

import javafx.geometry.Point2D;

import java.util.List;

public abstract class PlayerLocation {
    public abstract List<Point2D> getPoints();

    public static PlayerLocation getByN(int n, Point2D point0, double radius){
        if(n == 0) return null;
        switch (n){
            case 1:
                return new PlLoc1(point0, radius);
            case 2:
                return new PlLoc2(point0, radius);
            case 3:
                return new PlLoc3(point0, radius);
            case 4:
                return new PlLoc4(point0, radius);
            default:
                return new PlLocOther(point0, radius, n);
        }
    }
}
