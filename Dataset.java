import java.util.LinkedList;
import java.util.List;

class Dataset {

    private List<Trajectory> trajectories = new LinkedList<Trajectory>();

    public int size() {
        return this.trajectories.size();
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

    public Trajectory convertXMedianY() {
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

}