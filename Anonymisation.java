public class Anonymisation {

    public static void main(String[] args) {
        Trajectory r = new Trajectory();
        r.add(Place(3, 6, 1));
        r.add(Place(1, 7, 2));
        r.add(Place(8, 5, 2));

        Trajectory s = new Trajectory();
        r.add(Place(2, 9, 1));
        r.add(Place(7, 8, 2));
        r.add(Place(2, 4, 3));

        Trajectory u = new Trajectory();
        r.add(Place(0, 3, 1));
        r.add(Place(6, 2, 2));
        r.add(Place(9, 2, 3));
    
        double aeud = Trajectory.euclideanDistance(r, s);
        System.out.println("Adapted Euclidean Distance r, s = " + aeud);
    
        double astsd = Trajectory.shortTimeSeriesDistance(r, s);
        System.out.println("Adapted Short Time Series Distance r, s = " + astsd);
    }

}