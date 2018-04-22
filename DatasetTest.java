import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class DatasetTest {

    Dataset paperDataset = new Dataset();
    Dataset paperDatasetTwin = new Dataset();

    @Before
    public void initialisePaperDataset() {
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

        paperDataset.add(r);
        paperDataset.add(s);
        paperDataset.add(u);

        paperDatasetTwin.add(u);
        paperDatasetTwin.add(s);
        paperDatasetTwin.add(r);
    }

    Dataset largeDataset = new Dataset();

    @Before
    public void initialiseLargeDataset() {
        
        // club 1

        Trajectory t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 0, 2));
        t.add(new Place(2, 0, 3));
        t.add(new Place(3, 0, 4));
        t.add(new Place(4, 0, 5));
        t.add(new Place(5, 0, 6));
        t.add(new Place(6, 0, 7));
        t.add(new Place(7, 0, 8));
        t.add(new Place(8, 0, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(new Place(1, 2, 1));
        t.add(new Place(1, 0, 2));
        t.add(new Place(2, 0, 3));
        t.add(new Place(3, 0, 4));
        t.add(new Place(4, 1, 5));
        t.add(new Place(5, 0, 6));
        t.add(new Place(6, 0, 7));
        t.add(new Place(7, 0, 8));
        t.add(new Place(8, 0, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 0, 2));
        t.add(new Place(2, 1, 3));
        t.add(new Place(3, 1, 4));
        t.add(new Place(4, 0, 5));
        t.add(new Place(5, 0, 6));
        t.add(new Place(6, 0, 7));
        t.add(new Place(7, 0, 8));
        t.add(new Place(6, 1, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 0, 2));
        t.add(new Place(2, 0, 3));
        t.add(new Place(3, 0, 4));
        t.add(new Place(4, 0, 5));
        t.add(new Place(5, 0, 6));
        t.add(new Place(6, 0, 7));
        t.add(new Place(7, 0, 8));
        t.add(new Place(9, 0, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(new Place(0, 1, 1));
        t.add(new Place(1, 1, 2));
        t.add(new Place(2, 1, 3));
        t.add(new Place(3, 0, 4));
        t.add(new Place(4, 0, 5));
        t.add(new Place(5, 0, 6));
        t.add(new Place(6, 0, 7));
        t.add(new Place(7, 0, 8));
        t.add(new Place(8, 0, 9));
        this.largeDataset.add(t);

        // club 2
        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 1, 2));
        t.add(new Place(2, 2, 3));
        t.add(new Place(3, 3, 4));
        t.add(new Place(4, 4, 5));
        t.add(new Place(5, 5, 6));
        t.add(new Place(6, 6, 7));
        t.add(new Place(7, 7, 8));
        t.add(new Place(8, 8, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 1, 2));
        t.add(new Place(2, 3, 3));
        t.add(new Place(3, 4, 4));
        t.add(new Place(4, 5, 5));
        t.add(new Place(5, 6, 6));
        t.add(new Place(6, 7, 7));
        t.add(new Place(7, 8, 8));
        t.add(new Place(8, 8, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(new Place(0, 2, 1));
        t.add(new Place(1, 2, 2));
        t.add(new Place(2, 2, 3));
        t.add(new Place(3, 3, 4));
        t.add(new Place(4, 4, 5));
        t.add(new Place(5, 5, 6));
        t.add(new Place(6, 6, 7));
        t.add(new Place(7, 7, 8));
        t.add(new Place(8, 8, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 1, 2));
        t.add(new Place(2, 2, 3));
        t.add(new Place(3, 3, 4));
        t.add(new Place(4, 4, 5));
        t.add(new Place(5, 5, 6));
        t.add(new Place(6, 7, 7));
        t.add(new Place(8, 8, 8));
        t.add(new Place(9, 9, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 1, 2));
        t.add(new Place(1, 2, 3));
        t.add(new Place(2, 3, 4));
        t.add(new Place(4, 4, 5));
        t.add(new Place(5, 5, 6));
        t.add(new Place(6, 6, 7));
        t.add(new Place(7, 7, 8));
        t.add(new Place(8, 8, 9));
        this.largeDataset.add(t);

        // club 3

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(0, 1, 2));
        t.add(new Place(0, 2, 3));
        t.add(new Place(0, 3, 4));
        t.add(new Place(0, 4, 5));
        t.add(new Place(0, 5, 6));
        t.add(new Place(0, 6, 7));
        t.add(new Place(0, 7, 8));
        t.add(new Place(0, 8, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(new Place(1, 0, 1));
        t.add(new Place(1, 1, 2));
        t.add(new Place(0, 2, 3));
        t.add(new Place(0, 3, 4));
        t.add(new Place(0, 4, 5));
        t.add(new Place(0, 5, 6));
        t.add(new Place(0, 6, 7));
        t.add(new Place(0, 7, 8));
        t.add(new Place(1, 8, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(0, 1, 2));
        t.add(new Place(0, 2, 3));
        t.add(new Place(0, 3, 4));
        t.add(new Place(1, 4, 5));
        t.add(new Place(1, 5, 6));
        t.add(new Place(2, 6, 7));
        t.add(new Place(1, 7, 8));
        t.add(new Place(0, 8, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(new Place(0, 2, 1));
        t.add(new Place(0, 2, 2));
        t.add(new Place(0, 2, 3));
        t.add(new Place(0, 3, 4));
        t.add(new Place(0, 4, 5));
        t.add(new Place(0, 5, 6));
        t.add(new Place(0, 6, 7));
        t.add(new Place(0, 7, 8));
        t.add(new Place(0, 8, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(0, 1, 2));
        t.add(new Place(0, 2, 3));
        t.add(new Place(0, 3, 4));
        t.add(new Place(0, 4, 5));
        t.add(new Place(0, 5, 6));
        t.add(new Place(0, 7, 7));
        t.add(new Place(0, 8, 8));
        t.add(new Place(0, 9, 9));
        this.largeDataset.add(t);
    }

    @Test
    public void addTrajectory() {
        int oldSize = this.paperDataset.size();

        Trajectory t = new Trajectory();
        t.add(new Place(3, 2, 4));
        t.add(new Place(5, 5, 2));
        t.add(new Place(9, 9, 6));

        this.paperDataset.add(t);

        assertEquals(oldSize + 1, this.paperDataset.size());
        assertTrue(this.paperDataset.getTrajectories().contains(t));
    }

    @Test
    public void shouldEqual() {
        assertEquals(this.paperDataset, this.paperDatasetTwin);
    }

    @Test
    public void equalsAfterCopy() {
        Dataset copy = new Dataset(paperDataset);
        assertEquals(copy, paperDataset);
    }

    // median tests

    @Test
    public void xMedianYMedianAsInPaper() {
        MedianStrategy mS = new XMedianYMedian();

        Trajectory expected = new Trajectory();
        expected.add(new Place(2, 6, 1));
        expected.add(new Place(6, 7, 2));
        expected.add(new Place(8, 4, 3));

        Trajectory actual = mS.computeMedian(this.paperDataset.getTrajectories());

        assertEquals(expected, actual);
    }

    @Test
    public void xMedianYAsInPaper() {
        MedianStrategy mS = new XMedianY();

        Trajectory expected = new Trajectory();
        expected.add(new Place(2, 9, 1));
        expected.add(new Place(6, 2, 2));
        expected.add(new Place(8, 5, 3));

        Trajectory actual = mS.computeMedian(this.paperDataset.getTrajectories());
        
        assertEquals(expected, actual);
    }

    // MDAV tests

    DistanceMeasure euclidean = new EuclideanDistance();
    DistanceMeasure shortTimeSeries = new ShortTimeSeriesDistance();

    @Test
    public void nooneIsCloserThanClosest() {
        Trajectory t = new Trajectory();
        t.add(new Place(0, 3, 1));
        t.add(new Place(6, 2, 2));
        t.add(new Place(9, 3, 3));

        Trajectory closestEuclidean = this.paperDataset.closestTrajectoryTo(t, euclidean);
        Trajectory closestShortTimeSeries = this.paperDataset.closestTrajectoryTo(t, shortTimeSeries);

        for (Trajectory e : this.paperDataset.getTrajectories()) {
            assertTrue(euclidean.computeDistance(t, e) >= euclidean.computeDistance(t, closestEuclidean));
            assertTrue(shortTimeSeries.computeDistance(t, e) >= shortTimeSeries.computeDistance(t, closestShortTimeSeries));
        }
    }

}