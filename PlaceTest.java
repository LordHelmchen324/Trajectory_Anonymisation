import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Comparator;

public class PlaceTest {

    @Test
    public void shouldEqual() {
        Place p1 = new Place(3, 2);
        Place p2 = new Place(3, 2);

        assertTrue(p1.equals(p2));
    }

    @Test
    public void compareX() {
        Place smallerX = new Place(3, 5);
        Place greaterX = new Place(5, 3);

        Comparator<Place> c = new Place.XComparator();

        assertTrue(c.compare(smallerX, greaterX) < 0);
        assertTrue(c.compare(greaterX, smallerX) > 0);
        assertTrue(c.compare(smallerX, smallerX) == 0);
        assertTrue(c.compare(greaterX, greaterX) == 0);
    }

    @Test
    public void compareY() {
        Place smallerY = new Place(5, 3);
        Place greaterY = new Place(3, 5);

        Comparator<Place> c = new Place.YComparator();

        assertTrue(c.compare(smallerY, greaterY) < 0);
        assertTrue(c.compare(greaterY, smallerY) > 0);
        assertTrue(c.compare(smallerY, smallerY) == 0);
        assertTrue(c.compare(greaterY, greaterY) == 0);
    }

}