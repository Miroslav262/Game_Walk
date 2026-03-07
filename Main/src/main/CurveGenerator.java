package main;

import javafx.geometry.Point2D;

import java.util.Random;

public class CurveGenerator {
    Random random;
    public CurveGenerator(int seed){
        random = new Random(seed);
    }

    public CurveGenerator(){
        random = new Random();
    }

    public BeziersCurve getCurve(){
        Point2D a,b,c,d;

        if(random.nextBoolean()){

            a = new Point2D(-4, -4);
            d = new Point2D(4, 4);
            if(random.nextBoolean()){
                c = new Point2D(random.nextDouble(-20,-15), random.nextDouble(-4, 4));
                b = new Point2D(random.nextDouble(15, 20), random.nextDouble(-4, 4));
            }
            else{
                b = new Point2D(random.nextDouble(-4, 4), random.nextDouble(15, 20));
                c = new Point2D(random.nextDouble(-4, 4),random.nextDouble(-20, -15));
            }

        }
        else{
            a = new Point2D(-4, 4);
            d = new Point2D(4, -4);
            if(random.nextBoolean()){
                c = new Point2D(random.nextDouble(-4, 4), random.nextDouble(15,20));
                b = new Point2D(random.nextDouble(-4, 4), random.nextDouble(-20, -15));
            }
            else{
                b = new Point2D(random.nextDouble(15, 20), random.nextDouble(-4, 4));
                c = new Point2D(random.nextDouble(-20, -15), random.nextDouble(-4, 4));
            }
        }



        return new BeziersCurve(a, b, c, d);
    }
}
