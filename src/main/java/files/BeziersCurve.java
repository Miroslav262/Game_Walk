package files;

import javafx.geometry.Point2D;

public class BeziersCurve {
    Point2D a, b, c, d;
    private final int N = 2000;

    public BeziersCurve(Point2D a, Point2D b, Point2D c, Point2D d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    private double calculateX(double t){
        return (1-t)*(1-t)*(1-t)*a.getX()
                + 3*(1-t)*(1-t)*t*b.getX()
                + 3*(1-t)*t*t*c.getX()
                + t*t*t*d.getX();
    }

    private double calculateY(double t){
        return (1-t)*(1-t)*(1-t)*a.getY()
                + 3*(1-t)*(1-t)*t*b.getY()
                + 3*(1-t)*t*t*c.getY()
                + t*t*t*d.getY();
    }

    public Point2D getValue(double t){
        return new Point2D(calculateX(t), calculateY(t));
    }

    private double segmentLength(double t1, double t2){
        double dx = calculateX(t2) - calculateX(t1);
        double dy = calculateY(t2) - calculateY(t1);
        return Math.sqrt(dx*dx + dy*dy);
    }

    private double[] buildLengthTable(){
        double[] len = new double[N+1];
        len[0] = 0;

        for(int i=1; i<=N; i++){
            double t1 = (double)(i-1)/N;
            double t2 = (double)i/N;
            len[i] = len[i-1] + segmentLength(t1, t2);
        }
        return len;
    }

    public Point2D[] getPoints(int count){
        double[] len = buildLengthTable();
        double total = len[N];

        Point2D[] result = new Point2D[count+1];

        for(int i=0; i<=count; i++){
            double target = total * i / count;

            int low = 0, high = N;
            while(low < high){
                int mid = (low + high) / 2;
                if(len[mid] < target) low = mid + 1;
                else high = mid;
            }

            double t = (double)low / N;
            result[i] = getValue(t);
        }

        return result;
    }
}
