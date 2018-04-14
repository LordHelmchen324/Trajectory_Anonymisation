import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TrajectoryTest {

    @Test
    public void autocorrelationAtZeroShouldAlwaysBeOne() {
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

        assertEquals(1.0, r.autocorrelation(0.0), 0.001);
        assertEquals(1.0, s.autocorrelation(0.0), 0.001);
        assertEquals(1.0, u.autocorrelation(0.0), 0.001);
    }

}