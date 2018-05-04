import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class TrajectoryTest {

    @Test
    public void shouldEqual() {
        Trajectory r1 = new Trajectory();
        r1.add(1, new Place(3, 6));
        r1.add(2, new Place(1, 7));
        r1.add(3, new Place(8, 5));

        Trajectory r2 = new Trajectory();
        r2.add(1, new Place(3, 6));
        r2.add(2, new Place(1, 7));
        r2.add(3, new Place(8, 5));

        assertTrue(r1.equals(r2));
    }

    @Test
    public void copyEquals() {
        Trajectory t = new Trajectory();
        t.add(1, new Place(3, 6));
        t.add(2, new Place(1, 7));
        t.add(3, new Place(8, 5));

        Trajectory tCopy = new Trajectory(t);

        assertEquals(t, tCopy);
    }

    @Test
    public void copyDoesHaveSameListOfPlaces() {
        Trajectory t = new Trajectory();
        t.add(1, new Place(3, 6));
        t.add(2, new Place(1, 7));
        t.add(3, new Place(8, 5));

        Trajectory tCopy = new Trajectory(t);

        assertTrue(t.getPlaces() != tCopy.getPlaces());
    }

    @Test
    public void copyDoesNotContainSamePlace() {
        Trajectory t = new Trajectory();
        t.add(1, new Place(3, 6));
        t.add(2, new Place(1, 7));
        t.add(3, new Place(8, 5));

        Trajectory tCopy = new Trajectory(t);

        List<Place> tPlaces = t.getPlaces();
        List<Place> tCopyPlaces = tCopy.getPlaces();

        for (Place p : tPlaces) {
            for (Place p2 : tCopyPlaces) {
                assertTrue(p != p2);
            }
        }
    }

    @Test
    public void placesAreOrderedWhenAddededInOrder() {
        Trajectory t = new Trajectory();
        t.add(0, new Place(3, 2));
        t.add(1, new Place(3, 6));
        t.add(2, new Place(1, 7));
        t.add(3, new Place(8, 5));
        t.add(9, new Place(6, 3));

        List<Long> times = new ArrayList<Long>(t.getTimestamps());

        for (int i = 0; i < times.size() - 1; i++) {
            assertTrue(times.get(i) < times.get(i + 1));
        }
    }

    @Test
    public void placesAreOrderedWhenAddededUnordered() {
        Trajectory t = new Trajectory();
        t.add(3, new Place(8, 5));
        t.add(0, new Place(3, 2));
        t.add(1, new Place(3, 6));
        t.add(9, new Place(6, 3));
        t.add(2, new Place(1, 7));

        List<Long> times = new ArrayList<Long>(t.getTimestamps());

        for (int i = 0; i < times.size() - 1; i++) {
            assertTrue(times.get(i) < times.get(i + 1));
        }
    }

    // tests for Euclidean Distance

    DistanceMeasure euclidean = new EuclideanDistance();

    @Test
    public void euclideanDistanceOfRAndS() {
        Trajectory r = new Trajectory();
        r.add(1, new Place(3, 6));
        r.add(2, new Place(1, 7));
        r.add(3, new Place(8, 5));

        Trajectory s = new Trajectory();
        s.add(1, new Place(2, 9));
        s.add(2, new Place(7, 8));
        s.add(3, new Place(2, 4));

        double expected = 9.16515138991168;
        double actual = this.euclidean.computeDistance(r, s);

        assertEquals(expected, actual, 0.001);
    }

    // tests for Short Time Series Distance

    DistanceMeasure shortTimeSeries = new ShortTimeSeriesDistance();

    @Test
    public void shortTimeSeriesDistanceOfRAndS() {
        Trajectory r = new Trajectory();
        r.add(1, new Place(3, 6));
        r.add(2, new Place(1, 7));
        r.add(3, new Place(8, 5));

        Trajectory s = new Trajectory();
        s.add(1, new Place(2, 9));
        s.add(2, new Place(7, 8));
        s.add(3, new Place(2, 4));

        double expected = 14.52583904633395;
        double actual = this.shortTimeSeries.computeDistance(r, s);
        
        assertEquals(expected, actual, 0.001);
    }

    // tests for autocorrelation

    @Test
    public void autocorrelationAtZeroShouldAlwaysBeOne() {
        Trajectory r = new Trajectory();
        r.add(1, new Place(3, 6));
        r.add(2, new Place(1, 7));
        r.add(3, new Place(8, 5));

        Trajectory s = new Trajectory();
        s.add(1, new Place(2, 9));
        s.add(2, new Place(7, 8));
        s.add(3, new Place(2, 4));

        Trajectory u = new Trajectory();
        u.add(1, new Place(0, 3));
        u.add(2, new Place(6, 2));
        u.add(3, new Place(9, 2));

        assertEquals(1.0, r.autocorrelation(0.0), 0.001);
        assertEquals(1.0, s.autocorrelation(0.0), 0.001);
        assertEquals(1.0, u.autocorrelation(0.0), 0.001);
    }

    @Test
    public void autocorrelationShouldBeLowerEqualThenAtZero() {
        Trajectory t = new Trajectory();
        t.add(1, new Place(3, 6));
        t.add(2, new Place(1, 7));
        t.add(3, new Place(8, 5));

        double autocorrelationAtZero = t.autocorrelation(0.0);

        Random r = new Random();
        double shift = r.nextDouble();
        double shiftedAutocorrelation = t.autocorrelation(shift);

        assertTrue(shiftedAutocorrelation <= autocorrelationAtZero);
    }

}