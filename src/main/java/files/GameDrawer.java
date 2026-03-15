package files;

import files.PlayerLocationOnMap.PlayerLocation;
import files.WayElements.Way;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import javafx.scene.image.*;

import java.util.ArrayList;
import java.util.List;


public class GameDrawer extends Canvas{
    private GraphicsContext g;
    private BeziersCurve beziersCurve;

    private static GameDrawer instance = new GameDrawer();

    public static GameDrawer getInstance(){
        return instance;
    }

    private static final int CURVE_POINTS = 150;
    private final double SCALE_FACTOR = 0.09;
    private final double CIRCLE_RADIUS = 50;
    private final double GAME_PIECES_SCALER = 0.2;
    private final double GAME_PICIES_RADIUS = 0.3;
    private final Point2D[] miniPoints;


    private GameDrawer(){
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

        miniPoints = beziersCurve.getPoints(150);
        this.draw();
    }

    public void draw(){
        this.getGraphicsContext2D().clearRect(0,0, getHeight(), getWidth());

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

        int n = Way.getInstance().getElements().size();
        List<List<Player>> nPlayersOnPos = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nPlayersOnPos.add(new ArrayList<>());
        }

        for (Player player : PlayerController.getInstance().getPlayers()) {
            int pos = player.getPosition();
            nPlayersOnPos.get(pos).add(player);
        }

        System.out.println(nPlayersOnPos);

        for (int i = 0; i < nPlayersOnPos.size(); i++) {

            int playersOnThisCell = nPlayersOnPos.get(i).size();

            if (playersOnThisCell == 0)
                continue;

            int idx = (int) Math.round((double) i / (count - 1) * (CURVE_POINTS - 1));

            List<Point2D> positions =
                    PlayerLocation.getByN(playersOnThisCell, miniPoints[idx], GAME_PICIES_RADIUS).getPoints();

            for (int j = 0; j < playersOnThisCell; j++) {
                Image image = nPlayersOnPos.get(i).get(j).getImage();

                Point2D truePosition = Utils.fromCordsToPixels(positions.get(j), g.getCanvas().getWidth(), g.getCanvas().getHeight(),
                        g.getCanvas().heightProperty().get()*SCALE_FACTOR);
                System.out.println(truePosition);
                g.drawImage(
                        image,
                        truePosition.getX() - image.getWidth() * GAME_PIECES_SCALER/2,
                        truePosition.getY() - image.getHeight() * GAME_PIECES_SCALER/2,
                        image.getWidth() * GAME_PIECES_SCALER,
                        image.getHeight() * GAME_PIECES_SCALER
                );
            }
        }

        /*


        Image img = new Image("/images/gamePiece.png");

        Image tinted = Utils.shiftHue(img, Color.RED);
        g.drawImage(tinted, 300, 300);

*/

    }



}
