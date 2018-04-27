public class EuclideanDistance implements DistanceMeasure {

    @Override
    public double computeDistance(final Trajectory r, final Trajectory s) {
        if (r.length() != s.length()) {
            System.err.println("Trajectories have different length!");
            System.exit(-1);
        }

        long result = 0;
        for (int i = 0; i < r.length(); i++) {
            Place p = r.getPlaceAtIndex(i);
            Place q = s.getPlaceAtIndex(i);
        
            long tsqrd = (p.getT() - q.getT()) * (p.getT() - q.getT());
            long xsqrd = (p.getX() - q.getX()) * (p.getX() - q.getX());
            long ysqrd = (p.getY() - q.getY()) * (p.getY() - q.getY());
        
            result += (1 + tsqrd) * (xsqrd + ysqrd);
        }
    
        return Math.sqrt((double)result);
    }
    
}