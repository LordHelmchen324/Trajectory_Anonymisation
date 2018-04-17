import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class TrajectoryTest {

    // general tests

    @Test
    public void shouldEqual() {
        Trajectory r1 = new Trajectory();
        r1.add(new Place(3, 6, 1));
        r1.add(new Place(1, 7, 2));
        r1.add(new Place(8, 5, 3));

        Trajectory r2 = new Trajectory();
        r2.add(new Place(3, 6, 1));
        r2.add(new Place(1, 7, 2));
        r2.add(new Place(8, 5, 3));

        assertTrue(r1.equals(r2));
    }

    @Test
    public void lengthEqualAfterLengthening() {
        Trajectory r = new Trajectory();
        r.add(new Place(3, 6, 1));
        r.add(new Place(1, 7, 2));
        r.add(new Place(8, 5, 3));
        r.add(new Place(2, 4, 4));

        Trajectory s = new Trajectory();
        s.add(new Place(2, 9, 1));
        s.add(new Place(7, 8, 2));

        s.lengthenToEqualLengthAs(r);

        assertEquals(r.length(), s.length());
    }

    @Test
    public void noNewLoactionsAddedByLengthening() {
        Trajectory r = new Trajectory();
        r.add(new Place(3, 6, 1));
        r.add(new Place(1, 7, 2));
        r.add(new Place(8, 5, 3));
        r.add(new Place(2, 4, 4));

        Trajectory s = new Trajectory();
        s.add(new Place(2, 9, 1));
        s.add(new Place(7, 8, 2));

        s.lengthenToEqualLengthAs(r);

        for (Place p : s.getPlaces()) {
            assertTrue((p.x == 2 && p.y == 9) || (p.x == 7 && p.y == 8));
        }
    }

    @Test
    public void lengthenedContainsOriginal() {
        Trajectory r = new Trajectory();
        r.add(new Place(3, 6, 1));
        r.add(new Place(1, 7, 2));
        r.add(new Place(8, 5, 3));
        r.add(new Place(2, 4, 4));
        r.add(new Place(3, 5, 5));
        r.add(new Place(4, 5, 6));

        Trajectory s = new Trajectory();
        s.add(new Place(2, 9, 1));
        s.add(new Place(7, 8, 2));
        s.add(new Place(8, 8, 5));

        Trajectory sOriginal = new Trajectory(s);

        s.lengthenToEqualLengthAs(r);

        boolean found = false;
        for (int i = 0; i < r.length() - 3; i++) {
            Trajectory t = new Trajectory();
            t.add(s.getPlaceAtIndex(i));
            t.add(s.getPlaceAtIndex(i + 1));
            t.add(s.getPlaceAtIndex(i + 2));

            if (t.equals(sOriginal)) found = true;
        }

        assertTrue(found);
    }

    // tests for Euclidean Distance

    DistanceMeasure euclidean = new EuclideanDistance();

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
        double actual = this.euclidean.computeDistance(r, s);

        assertEquals(expected, actual, 0.001);
    }

    // tests for Short Time Series Distance

    DistanceMeasure shortTimeSeries = new ShortTimeSeriesDistance();

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
        double actual = this.shortTimeSeries.computeDistance(r, s);
        
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