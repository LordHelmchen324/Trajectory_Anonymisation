import java.util.LinkedList;
import java.util.List;

class XMedianY extends MedianStrategy {

    @Override
    public Trajectory computeMedian(List<Trajectory> trajectories) {
        Trajectory median = new Trajectory();

        // get a set of all timestamps;
        List<Place> places = this.allPlaces(trajectories);
        List<Integer> timestamps = new LinkedList<Integer>();
        for (Place p : places) {
            if ( !timestamps.contains(p.t) ) timestamps.add(p.t);
        }

        // for all timestamps, find the corresponding median Place
        for (Integer t : timestamps) {
            Place xMedian = this.findXMedianPlaceAtTime(trajectories, t);
            
            Place p = new Place(xMedian.x, xMedian.y, t);
            median.add(p);
        }

        return median;
    }

}