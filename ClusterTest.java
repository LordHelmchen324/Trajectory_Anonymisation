import static org.junit.Assert.*;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class ClusterTest {

    @Test
    public void allShouldBeEqualAfterXMedianYMedian() {
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

        List<Trajectory> trajectories = new LinkedList<Trajectory>();
        trajectories.add(r);
        trajectories.add(s);
        trajectories.add(u);

        Cluster c = new Cluster(trajectories);
        c.convertXMedianYMedian();

        Trajectory first = c.getTrajectories().get(0);
        Trajectory second = c.getTrajectories().get(1);
        Trajectory third = c.getTrajectories().get(2);

        assertTrue(first.equals(second) && second.equals(third));
    }

    @Test
    public void allShouldBeEqualAfterXMedianY() {
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

        List<Trajectory> trajectories = new LinkedList<Trajectory>();
        trajectories.add(r);
        trajectories.add(s);
        trajectories.add(u);

        Cluster c = new Cluster(trajectories);
        c.convertXMedianY();

        Trajectory first = c.getTrajectories().get(0);
        Trajectory second = c.getTrajectories().get(1);
        Trajectory third = c.getTrajectories().get(2);

        assertTrue(first.equals(second) && second.equals(third));
    }

    @Test
    public void testResultFromPaperForXMedianYMedian() {
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

        List<Trajectory> trajectories = new LinkedList<Trajectory>();
        trajectories.add(r);
        trajectories.add(s);
        trajectories.add(u);

        Cluster c = new Cluster(trajectories);
        c.convertXMedianYMedian();

        Trajectory expected = new Trajectory();
        expected.add(new Place(2, 6, 1));
        expected.add(new Place(6, 7, 2));
        expected.add(new Place(8, 4, 3));

        assertTrue(c.getTrajectories().get(0).equals(expected));
        assertTrue(c.getTrajectories().get(1).equals(expected));
        assertTrue(c.getTrajectories().get(2).equals(expected));
    }

    @Test
    public void testResultFromPaperForXMedianY() {
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

        List<Trajectory> trajectories = new LinkedList<Trajectory>();
        trajectories.add(r);
        trajectories.add(s);
        trajectories.add(u);

        Cluster c = new Cluster(trajectories);
        c.convertXMedianY();

        for (Trajectory t : c.getTrajectories()) {
            assertEquals(2, t.getPlaces().get(0).x);
            assertEquals(9, t.getPlaces().get(0).y);
            assertEquals(1, t.getPlaces().get(0).t);
        }
        for (Trajectory t : c.getTrajectories()) {
            assertEquals(6, t.getPlaces().get(1).x);
            assertEquals(2, t.getPlaces().get(1).y);
            assertEquals(2, t.getPlaces().get(1).t);
        }
        for (Trajectory t : c.getTrajectories()) {
            assertEquals(8, t.getPlaces().get(2).x);
            assertEquals(5, t.getPlaces().get(2).y);
            assertEquals(3, t.getPlaces().get(2).t);
        }
    }

}