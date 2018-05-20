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

        Trajectory r = new Trajectory();
        r.add(1, new Place(0, 0));
        r.add(2, new Place(1, 0));
        r.add(3, new Place(2, 0));
        r.add(4, new Place(3, 0));
        r.add(5, new Place(4, 0));
        r.add(6, new Place(5, 0));
        r.add(7, new Place(6, 0));
        r.add(8, new Place(7, 0));
        r.add(9, new Place(8, 0));
        this.largeDataset.add(r);

        r = new Trajectory();
        r.add(1, new Place(1, 2));
        r.add(2, new Place(1, 0));
        r.add(3, new Place(2, 0));
        r.add(4, new Place(3, 0));
        r.add(5, new Place(4, 1));
        r.add(6, new Place(5, 0));
        r.add(7, new Place(6, 0));
        r.add(8, new Place(7, 0));
        r.add(9, new Place(8, 0));
        this.largeDataset.add(r);

        r = new Trajectory();
        r.add(1, new Place(0, 0));
        r.add(2, new Place(1, 0));
        r.add(3, new Place(2, 1));
        r.add(4, new Place(3, 1));
        r.add(5, new Place(4, 0));
        r.add(6, new Place(5, 0));
        r.add(7, new Place(6, 0));
        r.add(8, new Place(7, 0));
        r.add(9, new Place(6, 1));
        this.largeDataset.add(r);

        r = new Trajectory();
        r.add(1, new Place(0, 0));
        r.add(2, new Place(1, 0));
        r.add(3, new Place(2, 0));
        r.add(4, new Place(3, 0));
        r.add(5, new Place(4, 0));
        r.add(6, new Place(5, 0));
        r.add(7, new Place(6, 0));
        r.add(8, new Place(7, 0));
        r.add(9, new Place(9, 0));
        this.largeDataset.add(r);

        r = new Trajectory();
        r.add(1, new Place(0, 1));
        r.add(2, new Place(1, 1));
        r.add(3, new Place(2, 1));
        r.add(4, new Place(3, 0));
        r.add(5, new Place(4, 0));
        r.add(6, new Place(5, 0));
        r.add(7, new Place(6, 0));
        r.add(8, new Place(7, 0));
        r.add(9, new Place(8, 0));
        this.largeDataset.add(r);

        // club 2
        r = new Trajectory();
        r.add(1, new Place(0, 0));
        r.add(2, new Place(1, 1));
        r.add(3, new Place(2, 2));
        r.add(4, new Place(3, 3));
        r.add(5, new Place(4, 4));
        r.add(6, new Place(5, 5));
        r.add(7, new Place(6, 6));
        r.add(8, new Place(7, 7));
        r.add(9, new Place(8, 8));
        this.largeDataset.add(r);

        r = new Trajectory();
        r.add(1, new Place(0, 0));
        r.add(2, new Place(1, 1));
        r.add(3, new Place(2, 3));
        r.add(4, new Place(3, 4));
        r.add(5, new Place(4, 5));
        r.add(6, new Place(5, 6));
        r.add(7, new Place(6, 7));
        r.add(8, new Place(7, 8));
        r.add(9, new Place(8, 8));
        this.largeDataset.add(r);

        r = new Trajectory();
        r.add(1, new Place(0, 2));
        r.add(2, new Place(1, 2));
        r.add(3, new Place(2, 2));
        r.add(4, new Place(3, 3));
        r.add(5, new Place(4, 4));
        r.add(6, new Place(5, 5));
        r.add(7, new Place(6, 6));
        r.add(8, new Place(7, 7));
        r.add(9, new Place(8, 8));
        this.largeDataset.add(r);

        r = new Trajectory();
        r.add(1, new Place(0, 0));
        r.add(2, new Place(1, 1));
        r.add(3, new Place(2, 2));
        r.add(4, new Place(3, 3));
        r.add(5, new Place(4, 4));
        r.add(6, new Place(5, 5));
        r.add(7, new Place(6, 7));
        r.add(8, new Place(8, 8));
        r.add(9, new Place(9, 9));
        this.largeDataset.add(r);

        r = new Trajectory();
        r.add(1, new Place(0, 0));
        r.add(2, new Place(1, 1));
        r.add(3, new Place(1, 2));
        r.add(4, new Place(2, 3));
        r.add(5, new Place(4, 4));
        r.add(6, new Place(5, 5));
        r.add(7, new Place(6, 6));
        r.add(8, new Place(7, 7));
        r.add(9, new Place(8, 8));
        this.largeDataset.add(r);

        // club 3

        r = new Trajectory();
        r.add(1, new Place(0, 0));
        r.add(2, new Place(0, 1));
        r.add(3, new Place(0, 2));
        r.add(4, new Place(0, 3));
        r.add(5, new Place(0, 4));
        r.add(6, new Place(0, 5));
        r.add(7, new Place(0, 6));
        r.add(8, new Place(0, 7));
        r.add(9, new Place(0, 8));
        this.largeDataset.add(r);

        r = new Trajectory();
        r.add(1, new Place(1, 0));
        r.add(2, new Place(1, 1));
        r.add(3, new Place(0, 2));
        r.add(4, new Place(0, 3));
        r.add(5, new Place(0, 4));
        r.add(6, new Place(0, 5));
        r.add(7, new Place(0, 6));
        r.add(8, new Place(0, 7));
        r.add(9, new Place(1, 8));
        this.largeDataset.add(r);

        r = new Trajectory();
        r.add(1, new Place(0, 0));
        r.add(2, new Place(0, 1));
        r.add(3, new Place(0, 2));
        r.add(4, new Place(0, 3));
        r.add(5, new Place(1, 4));
        r.add(6, new Place(1, 5));
        r.add(7, new Place(2, 6));
        r.add(8, new Place(1, 7));
        r.add(9, new Place(0, 8));
        this.largeDataset.add(r);

        r = new Trajectory();
        r.add(1, new Place(0, 2));
        r.add(2, new Place(0, 2));
        r.add(3, new Place(0, 2));
        r.add(4, new Place(0, 3));
        r.add(5, new Place(0, 4));
        r.add(6, new Place(0, 5));
        r.add(7, new Place(0, 6));
        r.add(8, new Place(0, 7));
        r.add(9, new Place(0, 8));
        this.largeDataset.add(r);

        r = new Trajectory();
        r.add(1, new Place(0, 0));
        r.add(2, new Place(0, 1));
        r.add(3, new Place(0, 2));
        r.add(4, new Place(0, 3));
        r.add(5, new Place(0, 4));
        r.add(6, new Place(0, 5));
        r.add(7, new Place(0, 7));
        r.add(8, new Place(0, 8));
        r.add(9, new Place(0, 9));
        this.largeDataset.add(r);
    }

    @Test
    public void addTrajectory() {
        int oldSize = this.paperDataset.size();

        Trajectory r = new Trajectory();
        r.add(4, new Place(3, 2));
        r.add(2, new Place(5, 5));
        r.add(6, new Place(9, 9));

        this.paperDataset.add(r);

        assertEquals(oldSize + 1, this.paperDataset.size());
        assertTrue(this.paperDataset.getTrajectories().contains(r));
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
        Trajectory r = new Trajectory();
        r.add(1, new Place(0, 3));
        r.add(2, new Place(6, 2));
        r.add(3, new Place(9, 3));

        Trajectory closestEuclidean = this.paperDataset.closestTrajectoryTo(r, euclidean);
        Trajectory closestShortTimeSeries = this.paperDataset.closestTrajectoryTo(r, shortTimeSeries);

        for (Trajectory e : this.paperDataset.getTrajectories()) {
            assertTrue(euclidean.computeDistance(r, e) >= euclidean.computeDistance(r, closestEuclidean));
            assertTrue(shortTimeSeries.computeDistance(r, e) >= shortTimeSeries.computeDistance(r, closestShortTimeSeries));
        }
    }

    @Test
    public void protectedHasTheSameAmountTrajectories() {
        DistanceMeasure dM = new ShortTimeSeriesDistance();
        MedianStrategy mS = new XMedianY();

        Dataset result = this.largeDataset.protectedByMDAV(4, dM, mS);

        assertEquals(result.size(), this.largeDataset.size());
    }

    @Test
    public void protectedHasTheSameAmountRecords() {
        DistanceMeasure dM = new ShortTimeSeriesDistance();
        MedianStrategy mS = new XMedianY();

        Dataset result = this.largeDataset.protectedByMDAV(4, dM, mS);

        assertEquals(result.numberOfRecordedTimestamps(), this.largeDataset.numberOfRecordedTimestamps());
    }

}