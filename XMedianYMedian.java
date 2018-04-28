import java.util.HashSet;
import java.util.List;
import java.util.Set;

class XMedianYMedian extends MedianStrategy {

    @Override
    public Trajectory computeMedian(List<Trajectory> trajectories) {
        System.out.println("    > Computing XMedianY ... ");

        Trajectory median = new Trajectory();

        // get a set of all timestamps;
        List<Place> places = this.allPlaces(trajectories);
        Set<Long> timestamps = new HashSet<Long>();
        for (Place p : places) timestamps.add(p.getT());

        // for all timestamps, find the corresponding median Place
        for (Long t : timestamps) {
            Place xMedian = this.findXMedianPlaceAtTime(trajectories, t);
            Place yMedian = this.findYMedianPlaceAtTime(trajectories, t);

            Place p = new Place(xMedian.getX(), yMedian.getY(), t);
            median.add(p);
        }

        return median;
    }

}