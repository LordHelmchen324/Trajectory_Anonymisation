import java.util.LinkedList;
import java.util.List;

class Cluster {

    private List<Trajectory> trajectories;

    public Cluster(List<Trajectory> trajectories) {
        this.trajectories = trajectories;
    }

    public int size() {
        return this.trajectories.size();
    }

    public List<Trajectory> getTrajectories() {
        return this.trajectories;
    }

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

        if ((n & 1) != 0) return concurrentPlaces.get((n + 1) / 2);

        Place lowerMedian = concurrentPlaces.get(n / 2);
        Place upperMedian = concurrentPlaces.get(n / 2 + 1);
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

        if ((n & 1) != 0) return concurrentPlaces.get((n + 1) / 2);

        Place lowerMedian = concurrentPlaces.get(n / 2);
        Place upperMedian = concurrentPlaces.get(n / 2 + 1);
        int yMedian = (lowerMedian.y + upperMedian.y) / 2;
        if (Math.abs(yMedian - lowerMedian.y) < Math.abs(yMedian - upperMedian.y)) {
            return lowerMedian;
        } else {
            return upperMedian;
        }
    }

    public void convertXMedianYMedian() {
        // get a set of all timestamps;
        List<Place> places = this.allPlaces();
        List<Integer> timestamps = new LinkedList<Integer>();
        for (Place p : places) {
            if ( !timestamps.contains(p.t) ) timestamps.add(p.t);
        }

        // for all Places recorded at the same time, set their x-coordinate to the median
        for (Integer t : timestamps) {
            Place xMedian = this.findXMedianPlaceAtTime(t);
            List<Place> concurrentPlaces = this.placesAtTime(t);
            for (Place p : concurrentPlaces) {
                p.x = xMedian.x;
            }
        }

        // for all Places recorded at the same time, set their y-coordinate to the median
        for (Integer t : timestamps) {
            Place yMedian = this.findYMedianPlaceAtTime(t);
            List<Place> concurrentPlaces = this.placesAtTime(t);
            for (Place p : concurrentPlaces) {
                p.y = yMedian.y;
            }
        }
    }

    public void convertXMedianY() {
        // get a set of all timestamps;
        List<Place> places = this.allPlaces();
        List<Integer> timestamps = new LinkedList<Integer>();
        for (Place p : places) {
            if ( !timestamps.contains(p.t) ) timestamps.add(p.t);
        }

        // for all Places recorded at the same time, set their x-coordinate to the median
        // and their y-coordinate to the corresponding y
        for (Integer t : timestamps) {
            Place xMedian = this.findXMedianPlaceAtTime(t);
            List<Place> concurrentPlaces = this.placesAtTime(t);
            for (Place p : concurrentPlaces) {
                p.x = xMedian.x;
                p.y = xMedian.y;
            }
        }
    }

}