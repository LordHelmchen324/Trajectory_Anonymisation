public class Anonymisation {

    public static void main(String[] args) {
        Trajectory r = new Trajectory();
        r.add(new Place(3, 6, 1));
        r.add(new Place(1, 7, 2));
        r.add(new Place(8, 5, 3));

        Trajectory s = new Trajectory();
        s.add(new Place(2, 9, 1));
        s.add(new Place(7, 8, 2));
        s.add(new Place(2, 4, 3));

        Trajectory u = new Trajectory();
        u.add(new Place(0, 3, 1));
        u.add(new Place(6, 2, 2));
        u.add(new Place(9, 2, 3));
    
        double aeud = Trajectory.euclideanDistance(r, s);
        System.out.println("Adapted Euclidean Distance r, s = " + aeud);
    
        double astsd = Trajectory.shortTimeSeriesDistance(r, s);
        System.out.println("Adapted Short Time Series Distance r, s = " + astsd);

        double autocorr0 = r.autocorrelation(0.0);
        System.out.println("Adapted Autocorrelation r, 0.0 = " + autocorr0);
        double autocorr1 = r.autocorrelation(1.0);
        System.out.println("Adapted Autocorrelation r, 1.0 = " + autocorr1);
    }

}