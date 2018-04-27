import java.util.LinkedList;
import java.util.List;

class XMedianY extends MedianStrategy {

    @Override
    public Trajectory computeMedian(List<Trajectory> trajectories) {
        Trajectory median = new Trajectory();

        // get a set of all timestamps;
        List<Place> places = this.allPlaces(trajectories);
        List<Long> timestamps = new LinkedList<Long>();
        for (Place p : places) {
            if ( !timestamps.contains(p.getT()) ) timestamps.add(p.getT());
        }

        // for all timestamps, find the corresponding median Place
        for (Long t : timestamps) {
            Place xMedian = this.findXMedianPlaceAtTime(trajectories, t);
            
            Place p = new Place(xMedian.getX(), xMedian.getY(), t);
            median.add(p);
        }

        return median;
    }

}