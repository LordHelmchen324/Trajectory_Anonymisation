import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class DatasetTest {

    Dataset d = new Dataset();

    @Before
    public void initialiseDataset() {
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

        d.add(r);
        d.add(s);
        d.add(u);
    }

    // general tests

    @Test
    public void addTrajectory() {
        int oldSize = this.d.size();

        Trajectory t = new Trajectory();
        t.add(new Place(3, 2, 4));
        t.add(new Place(5, 5, 2));
        t.add(new Place(9, 9, 6));

        this.d.add(t);

        assertEquals(oldSize + 1, this.d.size());
    }

    @Test
    public void allEqualLengthAfterFillUp() {
        this.d.getTrajectories().get(0).add(new Place(5, 3, 5));
        this.d.getTrajectories().get(0).add(new Place(3, 4, 6));
        this.d.getTrajectories().get(0).add(new Place(9, 3, 8));
        this.d.getTrajectories().get(0).add(new Place(8, 2, 9));
        
        this.d.getTrajectories().get(2).add(new Place(9, 3, 8));
        this.d.getTrajectories().get(2).add(new Place(8, 2, 9));

        d.fillUpToEqualLength();

        for (int i = 0; i < d.size() - 1; i++) {
            assertEquals(this.d.getTrajectories().get(i).lenght(), this.d.getTrajectories().get(i + 1).lenght());
        }
    }

    // median tests

    MedianStrategy xMedianYMedian = new XMedianYMedian();

    @Test
    public void xMedianYMedianAsInPaper() {
        Trajectory expected = new Trajectory();
        expected.add(new Place(2, 6, 1));
        expected.add(new Place(6, 7, 2));
        expected.add(new Place(8, 4, 3));

        Trajectory actual = this.xMedianYMedian.computeMedian(this.d.getTrajectories());

        assertEquals(expected, actual);
    }

    MedianStrategy xMedianY = new XMedianY();

    @Test
    public void xMedianYAsInPaper() {
        Trajectory expected = new Trajectory();
        expected.add(new Place(2, 9, 1));
        expected.add(new Place(6, 2, 2));
        expected.add(new Place(8, 5, 3));

        Trajectory actual = this.xMedianY.computeMedian(this.d.getTrajectories());
        
        assertEquals(expected, actual);
    }

}