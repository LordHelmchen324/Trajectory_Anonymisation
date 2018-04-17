public class ShortTimeSeriesDistance implements DistanceMeasure {

    @Override
    public double computeDistance(final Trajectory r, final Trajectory s) {
        if (r.length() != s.length()) {
            System.err.println("Trajectories have different length!");
            System.exit(-1);
        }

        Place origin = new Place(0, 0, 0);
        Trajectory rCopy = new Trajectory(r);
        rCopy.insertPlaceAtBeginning(origin);
        Trajectory sCopy = new Trajectory(s);
        sCopy.insertPlaceAtBeginning(origin);

        double result = 0.0;
        for (int i = 0; i < rCopy.length() - 1 ; i++) {
            Place p = rCopy.getPlaceAtIndex(i);
            Place pNext = rCopy.getPlaceAtIndex(i + 1);
            Place q = sCopy.getPlaceAtIndex(i);
            Place qNext = sCopy.getPlaceAtIndex(i + 1);
        
            double a1 = (double)(qNext.x - q.x) / (double)(qNext.t - q.t);
            double a2 = (double)(pNext.x - p.x) / (double)(qNext.t - q.t);
            double asqrd = (a1 - a2) * (a1 - a2);
        
            double b1 = (double)(qNext.y - q.y) / (double)(pNext.t - p.t);
            double b2 = (double)(pNext.y- p.y) / (double)(pNext.t - p.t);
            double bsqrd = (b1 - b2) * (b1 - b2);
        
            result += asqrd + bsqrd;
        }
        
        return Math.sqrt(result);
    }
    
}