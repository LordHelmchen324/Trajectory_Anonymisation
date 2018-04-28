import java.util.HashSet;
import java.util.List;
import java.util.Set;

class XMedianY extends MedianStrategy {

    @Override
    public Trajectory computeMedian(List<Trajectory> trajectories) {
        System.out.println("    > Computing XMedianY ... ");

        Trajectory median = new Trajectory();

        // get a set of all timestamps;
        List<Place> places = this.allPlaces(trajectories);
        Set<Long> timestamps = new HashSet<Long>();
        System.out.println("    > Getting timestamps ... ");
        for (Place p : places) timestamps.add(p.getT());
        System.out.println("    > Got the timestamps.");

        // for all timestamps, find the corresponding median Place
        System.out.println("    > Go through all timestamps ... ");
        for (Long t : timestamps) {
            Place xMedian = this.findXMedianPlaceAtTime(trajectories, t);
            
            Place p = new Place(xMedian.getX(), xMedian.getY(), t);
            median.add(p);
        }
        System.out.println("    > Finished.");
        
        return median;
    }

}