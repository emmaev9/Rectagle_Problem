public class Point {
    private double X;
    private double Y;

    public Point(double x, double y){
        this.X = x;
        this.Y = y;
    }

    public Point(){}

    public double getX() {return X;}

    public void setX(double x) {X = x;}

    public double getY() {return Y;}

    public void setY(double y) {Y = y;}

    public void printPoint(){
        System.out.println("(" + X + ", " + Y + ") ");
    }
}
