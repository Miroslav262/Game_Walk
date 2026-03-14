package files;

import files.WayElements.Way;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import javafx.scene.image.*;


public class GameDrawer extends Canvas{
    private GraphicsContext g;
    private BeziersCurve beziersCurve;

    private static final int CURVE_POINTS = 150;
    private final double SCALE_FACTOR = 0.09;
    private final double CIRCLE_RADIUS = 50;


    public GameDrawer(){
        g = this.getGraphicsContext2D();
        CurveGenerator curveGenerator = new CurveGenerator();
        beziersCurve = curveGenerator.getCurve();
        this.getGraphicsContext2D().getCanvas().setOnZoom(event -> this.draw());
        this.getGraphicsContext2D().getCanvas().widthProperty().addListener(
                (obs, oldV, newV) -> this.draw()
        );
        this.getGraphicsContext2D().getCanvas().heightProperty().addListener(
                (obs, oldV, newV) -> this.draw()
        );

        this.draw();
    }

    public void draw(){
        this.getGraphicsContext2D().clearRect(0,0, getHeight(), getWidth());
        Point2D[] miniPoints = beziersCurve.getPoints(150);

        for(Point2D point2D : miniPoints){
            g.setFill(Color.BLACK);
            Point2D pointInScreenCords = Utils.fromCordsToPixels(point2D, g.getCanvas().getWidth(), g.getCanvas().getHeight(),
                    g.getCanvas().heightProperty().get()*SCALE_FACTOR);
            g.fillOval(pointInScreenCords.getX(), pointInScreenCords.getY(), 4, 4);
        }

        int count = Way.getInstance().getElements().size();

        for(int i = 1; i<count-1; i++){
            int idx = (int) Math.round((double) i / (count - 1) * (CURVE_POINTS - 1));
            Point2D point2D = miniPoints[idx];
            g.setFill(Color.web("008A00"));
            Point2D pointInScreenCords = Utils.fromCordsToPixels(point2D, g.getCanvas().getWidth(), g.getCanvas().getHeight(),
                    g.getCanvas().heightProperty().get()*SCALE_FACTOR);
            g.fillOval(pointInScreenCords.getX()-CIRCLE_RADIUS/2, pointInScreenCords.getY()-CIRCLE_RADIUS/2, CIRCLE_RADIUS, CIRCLE_RADIUS);

            Image img = Way.getInstance().getElements().get(i).getMiniImage();
            double size = CIRCLE_RADIUS * 0.85;

            g.drawImage(
                    img,
                    pointInScreenCords.getX() - size / 2,
                    pointInScreenCords.getY() - size / 2,
                    size,
                    size
            );
        }
        Point2D start = miniPoints[0];
        Point2D finish = miniPoints[miniPoints.length-1];

        g.setFill(Color.web("99FF99"));

        Point2D startInScreenCords = Utils.fromCordsToPixels(start, g.getCanvas().getWidth(), g.getCanvas().getHeight(),
                g.getCanvas().heightProperty().get()*SCALE_FACTOR);
        Point2D finishInScreenCords = Utils.fromCordsToPixels(finish, g.getCanvas().getWidth(), g.getCanvas().getHeight(),
                g.getCanvas().heightProperty().get()*SCALE_FACTOR);

        double xSize = CIRCLE_RADIUS*3;
        double ySize = CIRCLE_RADIUS*1.5;

        g.fillOval(startInScreenCords.getX()-xSize/2, startInScreenCords.getY()-ySize/2, xSize, ySize);
        g.fillOval(finishInScreenCords.getX()-xSize/2, finishInScreenCords.getY()-ySize/2, xSize, ySize);

        Image imgStart = Way.getInstance().getElements().getFirst().getMiniImage();
        Image imgFinish = Way.getInstance().getElements().getLast().getMiniImage();

        double sizeSF = CIRCLE_RADIUS * 3.25;


        g.drawImage(
                imgStart,
                startInScreenCords.getX() - xSize / 1.85,
                startInScreenCords.getY() - ySize * 1.55,
                sizeSF,
                sizeSF
        );

        g.drawImage(
                imgFinish,
                finishInScreenCords.getX() - xSize / 1.85,
                finishInScreenCords.getY() - ySize * 1.55,
                sizeSF,
                sizeSF
        );


    }
}
