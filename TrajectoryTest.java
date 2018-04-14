import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Random;

public class TrajectoryTest {

    // tests for Euclidean Distance

    @Test
    public void euclideanDistanceOfRAndS() {
        Trajectory r = new Trajectory();
        r.add(new Place(3, 6, 1));
        r.add(new Place(1, 7, 2));
        r.add(new Place(8, 5, 3));

        Trajectory s = new Trajectory();
        s.add(new Place(2, 9, 1));
        s.add(new Place(7, 8, 2));
        s.add(new Place(2, 4, 3));

        double expected = 9.16515138991168;
        double actual = Trajectory.euclideanDistance(r, s);

        assertEquals(expected, actual, 0.001);
    }

    // tests for Short Time Series Distance

    @Test
    public void shortTimeSeriesDistanceOfRAndS() {
        Trajectory r = new Trajectory();
        r.add(new Place(3, 6, 1));
        r.add(new Place(1, 7, 2));
        r.add(new Place(8, 5, 3));

        Trajectory s = new Trajectory();
        s.add(new Place(2, 9, 1));
        s.add(new Place(7, 8, 2));
        s.add(new Place(2, 4, 3));

        double expected = 14.52583904633395;
        double actual = Trajectory.shortTimeSeriesDistance(r, s);

        assertEquals(expected, actual, 0.001);
    }

    // tests for autocorrelation

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

    @Test
    public void autocorrelationShouldBeLowerEqualThenAtZero() {
        Trajectory t = new Trajectory();
        t.add(new Place(3, 6, 1));
        t.add(new Place(1, 7, 2));
        t.add(new Place(8, 5, 3));

        double autocorrelationAtZero = t.autocorrelation(0.0);

        Random r = new Random();
        double shift = r.nextDouble();
        double shiftedAutocorrelation = t.autocorrelation(shift);

        assertTrue(shiftedAutocorrelation <= autocorrelationAtZero);
    }

}