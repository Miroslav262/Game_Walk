

package main;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.DB.DBController;

import javafx.scene.canvas.*;
import javafx.geometry.Point2D;
import java.sql.SQLOutput;
import java.util.*;
import java.util.List;

public class Main {

    /*@Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas();

        StackPane root = new StackPane(canvas);

        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("JavaFX Test");
        stage.setMaximized(true);
        stage.show();

        CurveGenerator curveGenerator = new CurveGenerator();
        BeziersCurve curve = curveGenerator.getCurve();

        //canvas.widthProperty().addListener((o, oldV, newV) -> draw(canvas, curve));
        //canvas.heightProperty().addListener((o, oldV, newV) -> draw(canvas, curve));

        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.BLACK);

        Point2D a = Utils.fromCordsToPixels(
                new Point2D(-5, -5),
                canvas.getWidth(),
                canvas.getHeight(),
                50
        );

        Point2D b = Utils.fromCordsToPixels(
                new Point2D(5, 5),
                canvas.getWidth(),
                canvas.getHeight(),
                50
        );

        double x = Math.min(a.getX(), b.getX());
        double y = Math.min(a.getY(), b.getY());
        double w = Math.abs(a.getX() - b.getX());
        double h = Math.abs(a.getY() - b.getY());

        g.fillRect(x, y, w, h);

        for(int i = 0; i<1; i++){


            g.setFill(new Color(Math.random(), Math.random(), Math.random(), 1));


            BeziersCurve curve1 = curveGenerator.getCurve();
            draw(canvas, curve1, 3, 75);
            draw(canvas, curve1, 15, 15);
        }



    }

    private void draw(Canvas canvas, BeziersCurve curve, int size, int n) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        //g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Point2D[] points = curve.getPoints(n);

        for (Point2D point : points) {
            Point2D nPoint = Utils.fromCordsToPixels(
                    point,
                    canvas.getWidth(),
                    canvas.getHeight(),
                    50
            );

            g.fillOval(nPoint.getX(), nPoint.getY(), size, size);
        }

     */
    public static void main(String[] args){
        Yaml yaml = new Yaml(new Constructor(PlayerConfig.class));
        PlayerConfig config = yaml.load(new FileInputStream("config.yaml"));

        System.out.println(config.player.name);

    }
}



