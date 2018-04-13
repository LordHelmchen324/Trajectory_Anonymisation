import java.util.LinkedList;
import java.util.List;

class Anonymisation {

    public static void main(String[] args) {
        TrajectoryTest trajTest = new TrajectoryTest();
        trajTest.testAutocorrelation();

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

        List<Trajectory> trajectories = new LinkedList<Trajectory>();
        trajectories.add(r);
        trajectories.add(s);
        trajectories.add(u);
        Cluster c = new Cluster(trajectories);
        c.convertXMedianYMedian();
        for (Trajectory t : c.getTrajectories()) {
            System.out.println("------");
            for (Place p : t.getPlaces()) {
                System.out.println("(" + p.x + "," + p.y + "," + p.t + ")");
            }
        }
    }

}