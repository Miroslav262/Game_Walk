package files;

import files.WayElements.Way;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import javafx.scene.image.*;


public class GameDrawer {
    private GraphicsContext g;
    private BeziersCurve beziersCurve;
    private final double SCALE_FACTOR = 0.1;
    private final double CIRCLE_RADIUS = 50;


    public GameDrawer(Canvas canvas){
        g = canvas.getGraphicsContext2D();
        CurveGenerator curveGenerator = new CurveGenerator();
        beziersCurve = curveGenerator.getCurve();

        this.draw();
    }

    public void draw(){

        Point2D[] miniPoints = beziersCurve.getPoints(150);

        for(Point2D point2D : miniPoints){
            g.setFill(Color.BLACK);
            Point2D pointInScreenCords = Utils.fromCordsToPixels(point2D, g.getCanvas().getWidth(), g.getCanvas().getHeight(),
                    g.getCanvas().heightProperty().get()*SCALE_FACTOR);
            g.fillOval(pointInScreenCords.getX(), pointInScreenCords.getY(), 4, 4);
        }

        Point2D[] points = beziersCurve.getPoints(Way.getInstance().getElements().size());

        for(int i = 0; i<Way.getInstance().getElements().size(); i++){
            Point2D point2D = points[i];
            g.setFill(Color.web("008A00"));
            Point2D pointInScreenCords = Utils.fromCordsToPixels(point2D, g.getCanvas().getWidth(), g.getCanvas().getHeight(),
                    g.getCanvas().heightProperty().get()*SCALE_FACTOR);
            g.fillOval(pointInScreenCords.getX()-CIRCLE_RADIUS/2, pointInScreenCords.getY()-CIRCLE_RADIUS/2, CIRCLE_RADIUS, CIRCLE_RADIUS);

            Image img = Way.getInstance().getElements().get(i).getMiniImage();
            System.out.println(img.errorProperty());
            System.out.println("loaded=" + img.isError() + " width=" + img.getWidth());

            double size = CIRCLE_RADIUS * 0.85;
            System.out.println(
                    "i=" + i +
                            " img=" + img +
                            " x=" + (pointInScreenCords.getX() - size/2) +
                            " y=" + (pointInScreenCords.getY() - size/2)
            );

            g.drawImage(
                    img,
                    pointInScreenCords.getX() - size / 2,
                    pointInScreenCords.getY() - size / 2,
                    size,
                    size
            );


        }


    }
}
