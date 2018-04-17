import java.util.LinkedList;
import java.util.List;

class Dataset {

    private List<Trajectory> trajectories = new LinkedList<Trajectory>();

    private MedianStrategy medianStrategy;

    public Dataset(MedianStrategy medianStrategy) {
        this.medianStrategy = medianStrategy;
    }

    public Dataset(Dataset original) {
        for (Trajectory t : original.trajectories) this.add(new Trajectory(t));
        this.medianStrategy = original.medianStrategy;
    }

    public void add(Trajectory t) {
        this.trajectories.add(t);
    }

    public void remove(Trajectory t) {
        this.trajectories.remove(t);
    }

    public int size() {
        return this.trajectories.size();
    }

    public Trajectory median() {
        return this.medianStrategy.computeMedian(this.trajectories);
    }

    // MDAV

    private Trajectory closestTrajectoryTo(Trajectory t, DistanceMeasure dM) {
        if (this.trajectories.isEmpty()) return t;      // TODO: error

        double minDistance = Double.MAX_VALUE;
        Trajectory closest = null;
        for (Trajectory t2 : this.trajectories) {
            double distance = dM.computeDistance(t, t2);
            if (distance < minDistance) {
                minDistance = distance;
                closest = t2;
            }
        }

        return closest;
    }

    private Trajectory furthestTrajectoryTo(Trajectory t, DistanceMeasure dM) {
        if (this.trajectories.isEmpty()) return t;      // TODO: error

        double maxDistance = 0.0;
        Trajectory furthest = null;
        for (Trajectory t2 : this.trajectories) {
            double distance = dM.computeDistance(t, t2);
            if (distance > maxDistance) {
                maxDistance = distance;
                furthest = t2;
            }
        }

        return furthest;
    }

    private List<Trajectory> clusterAround(Trajectory t, int size, DistanceMeasure dM) {
        List<Trajectory> cluster = new LinkedList<Trajectory>();
        cluster.add(t);
        this.remove(t);

        for (int i = 0; i < size - 1; i++) {
            Trajectory closest = this.closestTrajectoryTo(t, dM);
            cluster.add(closest);
            this.remove(closest);
        }

        return cluster;
    }

    public Dataset protectedByMDAV(int k, DistanceMeasure dM) {
        Dataset temp = new Dataset(this);
        List<List<Trajectory>> clusters = new LinkedList<List<Trajectory>>();

        while (temp.size() > k) {
            Trajectory avrg = temp.median();
            Trajectory furthest = temp.furthestTrajectoryTo(avrg, dM);
            clusters.add(temp.clusterAround(furthest, k, dM));

            if (temp.size() > k) {
                Trajectory furthest2 = temp.furthestTrajectoryTo(avrg, dM);
                clusters.add(temp.clusterAround(furthest2, k, dM));
            }
        }

        List<Trajectory> lastCluster = new LinkedList<Trajectory>();
        for (Trajectory t : temp.trajectories) {
            lastCluster.add(t);
            temp.remove(t);
        }
        clusters.add(lastCluster);

        Dataset result = new Dataset(this.medianStrategy);
        for (List<Trajectory> c : clusters) {
            Trajectory clusterMedian = this.medianStrategy.computeMedian(c);
            for (int i = 0; i < c.size(); i++) result.add(clusterMedian);
        }

        return result;
    }

}