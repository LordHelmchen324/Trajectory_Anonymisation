import java.util.List;
import java.util.Map;

class XMedianYMedian extends MedianStrategy {

    @Override
    public Trajectory computeMedian(List<Trajectory> trajectories) {
        System.out.println("    > Starting XMedianY computation ... ");

        Trajectory median = new Trajectory();

        System.out.print("      > Collecting places by their timestamp ... ");
        Map<Long,List<Place>> placesByTime = this.placesByTime(trajectories);
        System.out.print("done!\n");

        // for all timestamps, find the corresponding median Place
        System.out.println("      > Building median trajectory of length " + placesByTime.size() + " ... ");
        
        int i = 0;
        int j = 0;
        for (Map.Entry<Long,List<Place>> entry : placesByTime.entrySet()) {
            i++;
            if (i == 10000) {
                i = 0;
                j += 10000;
                System.out.println("        > Built up to place " + j);
            }
            
            Place xMedian = this.findXMedianPlace(entry.getValue());
            Place yMedian = this.findYMedianPlace(entry.getValue());

            median.add(entry.getKey(), new Place(xMedian.getX(), yMedian.getY()));
        }

        System.out.println("    > Finished.");
        
        return median;
    }

}