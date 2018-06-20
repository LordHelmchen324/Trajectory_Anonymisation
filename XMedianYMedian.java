import java.util.List;
import java.util.Map;

class XMedianYMedian extends MedianStrategy {

    @Override
    public Trajectory computeMedian(List<Trajectory> trajectories) {
        Trajectory median = new Trajectory();

        Map<Long,List<Place>> placesByTime = this.placesByTime(trajectories);

        // for all timestamps, find the corresponding median Place        
        for (Map.Entry<Long,List<Place>> entry : placesByTime.entrySet()) {
            Place xMedian = this.findXMedianPlace(entry.getValue());
            Place yMedian = this.findYMedianPlace(entry.getValue());

            median.add(entry.getKey(), new Place(xMedian.getX(), yMedian.getY()));
        }
        
        return median;
    }

}