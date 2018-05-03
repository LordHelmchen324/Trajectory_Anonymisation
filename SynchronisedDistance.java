import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SynchronisedDistance implements DistanceMeasure {

    @Override
    public void prepareDataset(Dataset d) {
        this.synchroniseTrajectories(d);
    }

    private void synchroniseTrajectories(Dataset d) {
        // Get a set of all timestamps of all existing places within the data set
        Set<Long> ts = new HashSet<Long>();
        for (Trajectory tj : d.getTrajectories()) {
            ts.addAll(tj.getTimestamps());
        }

        for (Trajectory r : d.getTrajectories()) {
            long minT = Collections.min(r.getTimestamps());    // TODO: Return timestamps as set ?
            long maxT = Collections.max(r.getTimestamps());

            // For all timestamps in the data set ...
            for (long t : ts) {
                // If the timestamp is withing the time frame the Trajectory was recorded ...
                if (minT < t && t < maxT) {
                    // If the trajectory has no place for timestamp t ... add an interpolated one
                    if (!r.getTimestamps().contains(t)) {
                        long timeBefore = minT;
                        for (long rT: r.getTimestamps()) {
                            if (rT < t && (t - rT < t - timeBefore || timeBefore == -1)) timeBefore = rT;
                        }
                        Place placeBefore = r.getPlaceAtTime(timeBefore);

                        long timeAfter = maxT;
                        for (long rT: r.getTimestamps()) {
                            if (rT > t && (rT - t < timeAfter - t || timeAfter == -1)) timeAfter = rT;
                        }
                        Place placeAfter = r.getPlaceAtTime(timeAfter);

                        long xInterpolated = placeBefore.getX() + (placeAfter.getX() - placeBefore.getX()) / (timeAfter - timeBefore);
                        long yInterpolated = placeBefore.getY() + (placeAfter.getY() - placeBefore.getY()) / (timeAfter - timeBefore);
                        r.add(t, new Place(xInterpolated, yInterpolated));
                    }
                }
            }
        }
    }

	@Override
	public double computeDistance(Trajectory r, Trajectory s) {
		return 0;
	}

}