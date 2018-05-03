import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class DatasetTest {

    Dataset paperDataset = new Dataset();
    Dataset paperDatasetTwin = new Dataset();

    @Before
    public void initPaperDataset() {
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

        paperDataset.add(r);
        paperDataset.add(s);
        paperDataset.add(u);

        paperDatasetTwin.add(u);
        paperDatasetTwin.add(s);
        paperDatasetTwin.add(r);
    }

    Dataset largeDataset = new Dataset();

    @Before
    public void initLargeDataset() {
        
        // club 1

        Trajectory t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 0));
        t.add(3, new Place(2, 0));
        t.add(4, new Place(3, 0));
        t.add(5, new Place(4, 0));
        t.add(6, new Place(5, 0));
        t.add(7, new Place(6, 0));
        t.add(8, new Place(7, 0));
        t.add(9, new Place(8, 0));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(1, new Place(1, 2));
        t.add(2, new Place(1, 0));
        t.add(3, new Place(2, 0));
        t.add(4, new Place(3, 0));
        t.add(5, new Place(4, 1));
        t.add(6, new Place(5, 0));
        t.add(7, new Place(6, 0));
        t.add(8, new Place(7, 0));
        t.add(9, new Place(8, 0));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 0));
        t.add(3, new Place(2, 1));
        t.add(4, new Place(3, 1));
        t.add(5, new Place(4, 0));
        t.add(6, new Place(5, 0));
        t.add(7, new Place(6, 0));
        t.add(8, new Place(7, 0));
        t.add(9, new Place(6, 1));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 0));
        t.add(3, new Place(2, 0));
        t.add(4, new Place(3, 0));
        t.add(5, new Place(4, 0));
        t.add(6, new Place(5, 0));
        t.add(7, new Place(6, 0));
        t.add(8, new Place(7, 0));
        t.add(9, new Place(9, 0));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 1));
        t.add(2, new Place(1, 1));
        t.add(3, new Place(2, 1));
        t.add(4, new Place(3, 0));
        t.add(5, new Place(4, 0));
        t.add(6, new Place(5, 0));
        t.add(7, new Place(6, 0));
        t.add(8, new Place(7, 0));
        t.add(9, new Place(8, 0));
        this.largeDataset.add(t);

        // club 2
        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 1));
        t.add(3, new Place(2, 2));
        t.add(4, new Place(3, 3));
        t.add(5, new Place(4, 4));
        t.add(6, new Place(5, 5));
        t.add(7, new Place(6, 6));
        t.add(8, new Place(7, 7));
        t.add(9, new Place(8, 8));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 1));
        t.add(3, new Place(2, 3));
        t.add(4, new Place(3, 4));
        t.add(5, new Place(4, 5));
        t.add(6, new Place(5, 6));
        t.add(7, new Place(6, 7));
        t.add(8, new Place(7, 8));
        t.add(9, new Place(8, 8));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 2));
        t.add(2, new Place(1, 2));
        t.add(3, new Place(2, 2));
        t.add(4, new Place(3, 3));
        t.add(5, new Place(4, 4));
        t.add(6, new Place(5, 5));
        t.add(7, new Place(6, 6));
        t.add(8, new Place(7, 7));
        t.add(9, new Place(8, 8));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 1));
        t.add(3, new Place(2, 2));
        t.add(4, new Place(3, 3));
        t.add(5, new Place(4, 4));
        t.add(6, new Place(5, 5));
        t.add(7, new Place(6, 7));
        t.add(8, new Place(8, 8));
        t.add(9, new Place(9, 9));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 1));
        t.add(3, new Place(1, 2));
        t.add(4, new Place(2, 3));
        t.add(5, new Place(4, 4));
        t.add(6, new Place(5, 5));
        t.add(7, new Place(6, 6));
        t.add(8, new Place(7, 7));
        t.add(9, new Place(8, 8));
        this.largeDataset.add(t);

        // club 3

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(0, 1));
        t.add(3, new Place(0, 2));
        t.add(4, new Place(0, 3));
        t.add(5, new Place(0, 4));
        t.add(6, new Place(0, 5));
        t.add(7, new Place(0, 6));
        t.add(8, new Place(0, 7));
        t.add(9, new Place(0, 8));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(1, new Place(1, 0));
        t.add(2, new Place(1, 1));
        t.add(3, new Place(0, 2));
        t.add(4, new Place(0, 3));
        t.add(5, new Place(0, 4));
        t.add(6, new Place(0, 5));
        t.add(7, new Place(0, 6));
        t.add(8, new Place(0, 7));
        t.add(9, new Place(1, 8));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(0, 1));
        t.add(3, new Place(0, 2));
        t.add(4, new Place(0, 3));
        t.add(5, new Place(1, 4));
        t.add(6, new Place(1, 5));
        t.add(7, new Place(2, 6));
        t.add(8, new Place(1, 7));
        t.add(9, new Place(0, 8));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 2));
        t.add(2, new Place(0, 2));
        t.add(3, new Place(0, 2));
        t.add(4, new Place(0, 3));
        t.add(5, new Place(0, 4));
        t.add(6, new Place(0, 5));
        t.add(7, new Place(0, 6));
        t.add(8, new Place(0, 7));
        t.add(9, new Place(0, 8));
        this.largeDataset.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(0, 1));
        t.add(3, new Place(0, 2));
        t.add(4, new Place(0, 3));
        t.add(5, new Place(0, 4));
        t.add(6, new Place(0, 5));
        t.add(7, new Place(0, 7));
        t.add(8, new Place(0, 8));
        t.add(9, new Place(0, 9));
        this.largeDataset.add(t);
    }

    @Test
    public void addTrajectory() {
        int oldSize = this.paperDataset.size();

        Trajectory t = new Trajectory();
        t.add(4, new Place(3, 2));
        t.add(2, new Place(5, 5));
        t.add(6, new Place(9, 9));

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
        expected.add(1, new Place(2, 6));
        expected.add(2, new Place(6, 7));
        expected.add(3, new Place(8, 4));

        Trajectory actual = mS.computeMedian(this.paperDataset.getTrajectories());

        assertEquals(expected, actual);
    }

    @Test
    public void xMedianYAsInPaper() {
        MedianStrategy mS = new XMedianY();

        Trajectory expected = new Trajectory();
        expected.add(1, new Place(2, 9));
        expected.add(2, new Place(6, 2));
        expected.add(3, new Place(8, 5));

        Trajectory actual = mS.computeMedian(this.paperDataset.getTrajectories());
        
        assertEquals(expected, actual);
    }

    // MDAV tests

    DistanceMeasure euclidean = new EuclideanDistance();
    DistanceMeasure shortTimeSeries = new ShortTimeSeriesDistance();

    @Test
    public void nooneIsCloserThanClosest() {
        Trajectory t = new Trajectory();
        t.add(1, new Place(0, 3));
        t.add(2, new Place(6, 2));
        t.add(3, new Place(9, 3));

        Trajectory closestEuclidean = this.paperDataset.closestTrajectoryTo(t, euclidean);
        Trajectory closestShortTimeSeries = this.paperDataset.closestTrajectoryTo(t, shortTimeSeries);

        for (Trajectory e : this.paperDataset.getTrajectories()) {
            assertTrue(euclidean.computeDistance(t, e) >= euclidean.computeDistance(t, closestEuclidean));
            assertTrue(shortTimeSeries.computeDistance(t, e) >= shortTimeSeries.computeDistance(t, closestShortTimeSeries));
        }
    }

    @Test
    public void protectedHasTheSameAmountTrajectories() {
        DistanceMeasure dM = new ShortTimeSeriesDistance();
        MedianStrategy mS = new XMedianY();

        Dataset result = this.largeDataset.protectedByMDAV(4, dM, mS);

        assertEquals(result.size(), this.largeDataset.size());
    }

}