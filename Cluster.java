import java.util.LinkedList;
import java.util.List;

class Cluster {

    private List<Trajectory> trajectories;

    Cluster(List<Trajectory> trajectories) {
        this.trajectories = trajectories;
    }

    int size() {
        return this.trajectories.size();
    }

    List<Trajectory> getTrajectories() {
        return this.trajectories;
    }

    private List<List<Place>> allPlacesByTimestamp() {
        // add Places into list of lists of Places that were recorded at the same time
        List<List<Place>> placesByTimestamp = new LinkedList<List<Place>>();
        for (Trajectory t : this.trajectories) {
            for (Place p : t.getPlaces()) {
                boolean added = false;
                for (List<Place> ps : placesByTimestamp) {
                    if (p.t == ps.get(0).t) {
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    List<Place> placesAtNewTimestamp = new LinkedList<Place>();
                    placesAtNewTimestamp.add(p);
                    placesByTimestamp.add(placesAtNewTimestamp);
                }
            }
        }
        return placesByTimestamp;
    }

    private void setXToMedian(List<Place> ps) {
        int sum = 0;
            for (Place p : ps) sum += p.x;
            double avrg = (double)sum / ps.size();

            double minDiff = Double.MAX_VALUE;
            int centered = 0;
            for (Place p : ps) {
                double diff = Math.abs((double)p.x - avrg);
                if (diff < minDiff) {
                    minDiff = diff;
                    centered = p.x;
                }
            }

            for (Place p : ps) p.x = centered;
    }

    private void setYToMedian(List<Place> ps) {
        int sum = 0;
            for (Place p : ps) sum += p.y;
            double avrg = (double)sum / ps.size();

            double minDiff = Double.MAX_VALUE;
            int centered = 0;
            for (Place p : ps) {
                double diff = Math.abs((double)p.y - avrg);
                if (diff < minDiff) {
                    minDiff = diff;
                    centered = p.y;
                }
            }

            for (Place p : ps) p.y = centered;
    }

    void convertXMedianYMedian() {
        List<List<Place>> placesByTimestamp = this.allPlacesByTimestamp();

        // for all Places recorded at the same time, set their x-coordinate to the median
        for (List<Place> ps : placesByTimestamp) {
            this.setXToMedian(ps);
        }

        // for all Places recorded at the same time, set their y-coordinate to the median
        for (List<Place> ps : placesByTimestamp) {
            this.setYToMedian(ps);
        }
    }

}