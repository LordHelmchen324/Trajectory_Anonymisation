public class EuclideanDistance implements DistanceMeasure {

    @Override
    public double computeDistance(final Trajectory r, final Trajectory s) {
        if (r.lenght() != s.lenght()) {
            System.err.println("Trajectories have different length!");
            System.exit(-1);
        }

        int result = 0;
        for (int i = 0; i < r.lenght(); i++) {
            Place p = r.getPlaceAtIndex(i);
            Place q = s.getPlaceAtIndex(i);
        
            int tsqrd = (p.t - q.t) * (p.t - q.t);
            int xsqrd = (p.x - q.x) * (p.x - q.x);
            int ysqrd = (p.y - q.y) * (p.y - q.y);
        
            result += (1 + tsqrd) * (xsqrd + ysqrd);
        }
    
        return Math.sqrt((double)result);
    }
    
}