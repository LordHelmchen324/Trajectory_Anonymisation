import static org.junit.Assert.*;
import org.junit.Test;

public class PlaceTest {

    @Test
    public void shouldEqual() {
        Place p1 = new Place(3, 2, 4);
        Place p2 = new Place(3, 2, 4);

        assertTrue(p1.equals(p2));
    }

}