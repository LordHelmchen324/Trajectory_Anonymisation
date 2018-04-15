import java.util.LinkedList;
import java.util.List;

class Dataset {

    private List<Trajectory> trajectories = new LinkedList<Trajectory>();

    public Dataset() { }

    public Dataset(Dataset original) {
        for (Trajectory t : original.trajectories) this.add(new Trajectory(t));
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

    // median computaions

    private List<Place> allPlaces() {
        List<Place> places = new LinkedList<Place>();
        for (Trajectory t : this.trajectories) {
            places.addAll(t.getPlaces());
        }
        return places;
    }

    private List<Place> placesAtTime(int t) {
        List<Place> places = this.allPlaces();
        List<Place> concurrentPlaces = new LinkedList<Place>();
        for (Place p : places) {
            if (p.t == t) concurrentPlaces.add(p);
        }
        return concurrentPlaces;
    }

    private Place findXMedianPlaceAtTime(int t) {
        List<Place> concurrentPlaces = this.placesAtTime(t);

        concurrentPlaces.sort(new Place.XComparator());
        int n = concurrentPlaces.size();

        if ((n & 1) != 0) return concurrentPlaces.get(n / 2);

        Place lowerMedian = concurrentPlaces.get(n / 2 - 1);
        Place upperMedian = concurrentPlaces.get(n / 2);
        int xMedian = (lowerMedian.x + upperMedian.x) / 2;
        if (Math.abs(xMedian - lowerMedian.x) < Math.abs(xMedian - upperMedian.x)) {
            return lowerMedian;
        } else {
            return upperMedian;
        }
    }

    private Place findYMedianPlaceAtTime(int t) {
        List<Place> concurrentPlaces = this.placesAtTime(t);

        concurrentPlaces.sort(new Place.YComparator());
        int n = concurrentPlaces.size();

        if ((n & 1) != 0) return concurrentPlaces.get(n / 2);

        Place lowerMedian = concurrentPlaces.get(n / 2 - 1);
        Place upperMedian = concurrentPlaces.get(n / 2);
        int yMedian = (lowerMedian.y + upperMedian.y) / 2;
        if (Math.abs(yMedian - lowerMedian.y) < Math.abs(yMedian - upperMedian.y)) {
            return lowerMedian;
        } else {
            return upperMedian;
        }
    }

    public Trajectory xMedianYMedian() {
        Trajectory median = new Trajectory();

        // get a set of all timestamps;
        List<Place> places = this.allPlaces();
        List<Integer> timestamps = new LinkedList<Integer>();
        for (Place p : places) {
            if ( !timestamps.contains(p.t) ) timestamps.add(p.t);
        }

        // for all timestamps, find the corresponding median Place
        for (Integer t : timestamps) {
            Place xMedian = this.findXMedianPlaceAtTime(t);
            Place yMedian = this.findYMedianPlaceAtTime(t);

            Place p = new Place(xMedian.x, yMedian.y, t);
            median.add(p);
        }

        return median;
    }

    public Trajectory xMedianY() {
        Trajectory median = new Trajectory();

        // get a set of all timestamps;
        List<Place> places = this.allPlaces();
        List<Integer> timestamps = new LinkedList<Integer>();
        for (Place p : places) {
            if ( !timestamps.contains(p.t) ) timestamps.add(p.t);
        }

        // for all timestamps, find the corresponding median Place
        for (Integer t : timestamps) {
            Place xMedian = this.findXMedianPlaceAtTime(t);
            
            Place p = new Place(xMedian.x, xMedian.y, t);
            median.add(p);
        }

        return median;
    }

    // MDAV

    private Trajectory closestTrajectoryTo(Trajectory t) {
        if (this.trajectories.isEmpty()) return t;      // TODO: error

        double minDistance = Double.MAX_VALUE;
        Trajectory closest = null;
        for (Trajectory t2 : this.trajectories) {
            double distance = Trajectory.euclideanDistance(t, t2);      // TODO: make this general and replacable
            if (distance < minDistance) {
                minDistance = distance;
                closest = t2;
            }
        }

        return closest;
    }

    private Trajectory furthestTrajectoryTo(Trajectory t) {
        if (this.trajectories.isEmpty()) return t;      // TODO: error

        double maxDistance = 0.0;
        Trajectory furthest = null;
        for (Trajectory t2 : this.trajectories) {
            double distance = Trajectory.euclideanDistance(t, t2);      // TODO: make this general and replacable
            if (distance > maxDistance) {
                maxDistance = distance;
                furthest = t2;
            }
        }

        return furthest;
    }

    private List<Trajectory> clusterAround(Trajectory t, int size) {
        List<Trajectory> cluster = new LinkedList<Trajectory>();
        cluster.add(t);
        this.remove(t);

        for (int i = 0; i < size - 1; i++) {
            Trajectory closest = this.closestTrajectoryTo(t);
            cluster.add(closest);
            this.remove(closest);
        }

        return cluster;
    }

    public Dataset protectedByMDAV(int k) {
        Dataset temp = new Dataset(this);
        List<List<Trajectory>> clusters = new LinkedList<List<Trajectory>>();

        while (temp.size() > k) {
            Trajectory avrg = temp.xMedianYMedian();      // TODO: make this general and replacable
            Trajectory furthest = temp.furthestTrajectoryTo(avrg);
            clusters.add(temp.clusterAround(furthest, k));

            if (temp.size() > k) {
                Trajectory furthest2 = temp.furthestTrajectoryTo(avrg);
                clusters.add(temp.clusterAround(furthest, k));
            }
        }

        List<Trajectory> lastCluster = new LinkedList<Trajectory>();
        for (Trajectory t : temp.trajectories) {
            lastCluster.add(t);
            temp.remove(t);
        }
        clusters.add(lastCluster);

        Dataset result = new Dataset();
        for (List<Trajectory> c : clusters) {
            
        }
    }

}