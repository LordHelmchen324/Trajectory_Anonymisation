import java.util.LinkedList;
import java.util.List;

abstract class MedianStrategy {

    protected List<Place> allPlaces(List<Trajectory> trajectories) {
        List<Place> places = new LinkedList<Place>();
        for (Trajectory t : trajectories) {
            places.addAll(t.getPlaces());
        }
        return places;
    }

    protected List<Place> placesAtTime(List<Trajectory> trajectories, int t) {
        List<Place> places = this.allPlaces(trajectories);
        List<Place> concurrentPlaces = new LinkedList<Place>();
        for (Place p : places) {
            if (p.t == t) concurrentPlaces.add(p);
        }
        return concurrentPlaces;
    }

    protected Place findXMedianPlaceAtTime(List<Trajectory> trajectories, int t) {
        List<Place> concurrentPlaces = this.placesAtTime(trajectories, t);

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

    protected Place findYMedianPlaceAtTime(List<Trajectory> trajectories, int t) {
        List<Place> concurrentPlaces = this.placesAtTime(trajectories, t);

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

    // has to be overidden by subclasses
    public Trajectory computeMedian(List<Trajectory> trajectories) {
        return null;
    }

}