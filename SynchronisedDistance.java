import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SynchronisedDistance implements DistanceMeasure {

    private Map<Trajectory, Integer> dict;
    private double[][] shortestDistanceMatrix;

    @Override
	public void createSupportData(Dataset d) {
        System.out.println(" -> SynchronisedDistance: Creating support data ...");

        System.out.println("    > Copying dataset ... ");
        Dataset temp = new Dataset(d);

        System.out.println("    > Synchronising trajectories ...");
        this.synchroniseTrajectories(temp);

        System.out.println("    > Creating dictionary of trajectories to in indicies ...");
        this.dict = new HashMap<Trajectory, Integer>();
        int i = 0;
        for (Trajectory r : d.getTrajectories()) {
            this.dict.put(r, i);
            i++;
        }

        System.out.println("    > Building distance graph ...");
        double[][] distanceGraph = this.makeDistanceGraph(temp);

        System.out.println("    > Computing shortest distance matrix ...");
        this.shortestDistanceMatrix = this.computeShortestDistanceMatrix(distanceGraph);
    }

	@Override
	public void removeImpossibleTrajectoriesFromDataset(Dataset d) {
		return;   // TODO: Remove everything but the largest part
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

    private double[][] makeDistanceGraph(Dataset temp) {
        Set<Trajectory> trajectories = temp.getTrajectories();

        double[][] distanceGraph = new double[trajectories.size()][trajectories.size()];

        for (Trajectory r : trajectories) {
            for (Trajectory s : trajectories) {
                double d;
                int rIndex = this.dict.get(r);
                int sIndex = this.dict.get(s);

                if (s == r) {
                    d = 0;
                } else if (this.percentContemporary(r, s) > 0) {
                    d = this.directDistance(r, s);
                } else {
                    d = Double.POSITIVE_INFINITY;
                }

                distanceGraph[rIndex][sIndex] = distanceGraph[sIndex][rIndex] = d;
            }
        }

        return distanceGraph;
    }

    private double percentContemporary(Trajectory r, Trajectory s) {
        long rFirstT = Collections.min(r.getTimestamps());
        long rLastT = Collections.max(r.getTimestamps());
        long sFirstT = Collections.min(s.getTimestamps());
        long sLastT = Collections.max(s.getTimestamps());

        long I = Math.max(Math.min(rLastT, sLastT) - Math.max(rFirstT, sFirstT), 0);

        return 100 * Math.min((double)I / (double)(rLastT - rFirstT), (double)I / (double)(sLastT - sFirstT));
    }

    private double directDistance(Trajectory r, Trajectory s) {
        Set<Long> ot = new HashSet<Long>(r.getTimestamps());
        ot.retainAll(s.getTimestamps());

        double sum = 0.0;
        for (long t : ot) {
            double a1 = Math.pow(r.getPlaceAtTime(t).getX() - s.getPlaceAtTime(t).getX(), 2);
            double a2 = Math.pow(r.getPlaceAtTime(t).getY() - s.getPlaceAtTime(t).getY(), 2);
            sum += (a1 + a2) / Math.pow(ot.size(), 2);
        }

        return Math.sqrt(sum) / this.percentContemporary(r, s);
    }

    private double[][] computeShortestDistanceMatrix(double[][] distanceGraph) {
        int n = distanceGraph.length;

        double[][] shortestDistanceMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                shortestDistanceMatrix[i][j] = distanceGraph[i][j];
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distanceGraph[i][k] + distanceGraph[k][j] < distanceGraph[i][j]) {
                        distanceGraph[i][j] = distanceGraph[i][k] + distanceGraph[k][j];
                    }
                }
            }
        }

        return distanceGraph;
    }

	@Override
	public double computeDistance(Trajectory r, Trajectory s) {
        int rIndex = this.dict.get(r);
        int sIndex = this.dict.get(s);

        return this.shortestDistanceMatrix[rIndex][sIndex];
	}

}