package main;

import javafx.geometry.Point2D;

public class BeziersCurve {
    Point2D a, b, c, d;
    private final int N = 1000;

    public BeziersCurve(Point2D a, Point2D b, Point2D c, Point2D d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    private double calculateX(double t){
        return (1-t)*(1-t)*(1-t)*a.getX() + 3*(1-t)*(1-t)*t * b.getX() + 3*(1-t)*t*t*c.getX() + t*t*t*d.getX();
    }
    private double calculateY(double t){
        return (1-t)*(1-t)*(1-t)*a.getY() + 3*(1-t)*(1-t)*t * b.getY() + 3*(1-t)*t*t*c.getY() + t*t*t*d.getY();
    }

    public Point2D getValue(double t){
        if(t<0 || t>1){
            throw new IllegalArgumentException();
        }
        else{
           double x = calculateX(t);
           double y = calculateY(t);

           return new Point2D(x, y);
        }
    }

    private double getSum(int n, double tMax){
        double result = 0;

        for(int i = 0; i<n;i++){
            double t_i = i*tMax/n;
            double t_i_1 = (i+1)*tMax/n;

            double dx = calculateX(t_i_1) - calculateX(t_i);
            double dy = calculateY(t_i_1) - calculateY(t_i);

            result+=Math.sqrt(dx*dx+dy+dy);
        }
        return result;
    }

    private double solveT(double curT, double l){
        double cur = curT+l;
        if(cur>1) {cur = 1;}
        double prev = curT;

        do{
            prev = cur;
            cur = getSum(N, cur) - getSum(N, curT) + cur - l;
        }
        while(Math.abs(prev - cur)>0.001);

        return cur;
    }

    private double[]getT(int count){
        double current = 0;
        double l = 1.0/count;

        double[] result = new double[count+1];
        result[0] = 0;

        for(int i = 0; i<count-1; i++){
            result[i+1] = solveT(result[i], l);
        }

        result[count+1] = 1;
        return result;
    }

    public Point2D[] getPoints(int n){
        if(n<0){
            throw new IndexOutOfBoundsException("Количесво точек не может быть отрицательным");
        }
        else{
            double[]t_es = getT(n);
            Point2D [] result = new Point2D[n+1];
            for(int i = 0; i<=n; i++){
                result[i] = new Point2D(calculateX(1.0*i/n), calculateY(1.0*i/n));
            }
            return result;
        }
    }

    private double getLength(double t2, double t1, int n){
        return getSum(n, t2) - getSum(n,t1);
    }
}
