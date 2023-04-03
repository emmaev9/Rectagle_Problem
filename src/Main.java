
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static int countRectangles(List<Point> points){
        if(points.size() < 4){ //no rectangles
            return 0;
        }
        int numberOfRectangles = 0;

        Map<Double, Integer> xMap = new HashMap<>();  //for counting the frequency for each X coordinate
        Map<Double, Integer> yMap = new HashMap<>();  //for counting the frequency for each Y coordinate

        for(Point point: points){ //update the xMap and yMap
            xMap.putIfAbsent(point.getX(), 0);
            yMap.putIfAbsent(point.getY(), 0);

            xMap.put(point.getX(), xMap.get(point.getX()) + 1);
            yMap.put(point.getY(), yMap.get(point.getY()) + 1);
        }

        for(int i=0;i<points.size()-1;i++){
            for(int j=i+1;j<points.size();j++){
                double X1 = points.get(i).getX();
                double Y1 = points.get(i).getY();
                double X2 = points.get(j).getX();
                double Y2 = points.get(j).getY();

                if(X1 == X2 || Y1 == Y2){
                    // I use this if statement to skip the current pair of points if they lie on the same vertical line or
                    // horizontal line, because rectangles cannot be formed by points lying on the same line.
                    continue; // skip the current iteration of the inner loop
                }
                if(xMap.get(X1)>1 && xMap.get(X2)>1 && yMap.get(Y1) > 1 && yMap.get(Y2) > 1){
                    // check if there are at least two other points that have the same x-coordinate as X1 and X2, and at least two other points
                    // that have the same y-coordinate as Y1 and Y2 => we can form a rectangle that is parallel
                    // with the X and Y axes using the four points (X1, Y1), (X2, Y2), and two other points that have the same x-coordinate as X1 and X2,
                    // and the same y-coordinate as Y1 and Y2, respectively.
                    numberOfRectangles++;
                }
            }
        }
        return numberOfRectangles/2; // divide by 2 beacause each rectangle is counted twice
    }
    public static void main(String[] args) throws IOException {
        List<Point> points = new ArrayList<>();
        BufferedReader br  = new BufferedReader(new BufferedReader(new FileReader("Input.txt")));
        String line = "";
        while((line = br.readLine()) != null){
            String[] t = line.split(",");
            double X = Double.parseDouble(t[0].trim());
            double Y = Double.parseDouble(t[1].trim());

            Point p = new Point(X,Y);
            points.add(p);
        }
        System.out.println("Given points: ");
        for(Point p: points){
            p.printPoint();
        }
        System.out.println("The number of rectangles that can be created with the given points: " + countRectangles(points));

    }
}
