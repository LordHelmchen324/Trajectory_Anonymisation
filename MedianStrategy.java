import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

abstract class MedianStrategy {

    protected Map<Long,List<Place>> placesByTime(List<Trajectory> trajectories) {
        Map<Long,List<Place>> placesByTime = new HashMap<Long, List<Place>>();

        for (Trajectory t : trajectories) {
            List<Place> places = t.getPlaces();

            for (Place p : places) {
                List<Place> concurrentPlaces = placesByTime.get(p.getT());
                if (concurrentPlaces == null) {
                    concurrentPlaces = new LinkedList<Place>();
                    concurrentPlaces.add(p);
                    placesByTime.put(p.getT(), concurrentPlaces);
                } else {
                    concurrentPlaces.add(p);
                }
            }
        }

        return placesByTime;
    }

    protected Place findXMedianPlace(List<Place> places) {
        places.sort(new Place.XComparator());
        int n = places.size();

        if ((n & 1) != 0) return places.get(n / 2);

        Place lowerMedian = places.get(n / 2 - 1);
        Place upperMedian = places.get(n / 2);
        long xMedian = (lowerMedian.getX() + upperMedian.getX()) / 2;
        if (Math.abs(xMedian - lowerMedian.getX()) < Math.abs(xMedian - upperMedian.getX())) {
            return lowerMedian;
        } else {
            return upperMedian;
        }
    }

    protected Place findYMedianPlace(List<Place> places) {
        places.sort(new Place.YComparator());
        int n = places.size();

        if ((n & 1) != 0) return places.get(n / 2);

        Place lowerMedian = places.get(n / 2 - 1);
        Place upperMedian = places.get(n / 2);
        long yMedian = (lowerMedian.getY() + upperMedian.getY()) / 2;
        if (Math.abs(yMedian - lowerMedian.getY()) < Math.abs(yMedian - upperMedian.getY())) {
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