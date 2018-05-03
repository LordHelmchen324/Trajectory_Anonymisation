import java.util.List;

public class ShortTimeSeriesDistance implements DistanceMeasure {

    @Override
    public double computeDistance(final Trajectory r, final Trajectory s) {
        if (r.length() != s.length()) {
            System.err.println("Trajectories have different length!");
            System.exit(-1);
        }

        Place origin = new Place(0, 0);
        Trajectory rCopy = new Trajectory(r);
        rCopy.add(0, origin);
        Trajectory sCopy = new Trajectory(s);
        sCopy.add(0, origin);

        List<Long> rCopyTimestamps = rCopy.getTimestamps();
        List<Place> rCopyPlaces = rCopy.getPlaces();
        List<Long> sCopyTimestamps = sCopy.getTimestamps();
        List<Place> sCopyPlaces = sCopy.getPlaces();

        double result = 0.0;
        for (int i = 0; i < rCopy.length() - 1 ; i++) {
            long pt = rCopyTimestamps.get(i);
            Place p = rCopyPlaces.get(i);
            long ptNext = rCopyTimestamps.get(i + 1);
            Place pNext = rCopyPlaces.get(i + 1);
            long qt = sCopyTimestamps.get(i);
            Place q = sCopyPlaces.get(i);
            long qtNext = sCopyTimestamps.get(i + 1);
            Place qNext = sCopyPlaces.get(i + 1);
        
            double a1 = (double)(qNext.getX() - q.getX()) / (double)(qtNext - qt);
            double a2 = (double)(pNext.getX() - p.getX()) / (double)(qtNext - qt);
            double asqrd = (a1 - a2) * (a1 - a2);
        
            double b1 = (double)(qNext.getY() - q.getY()) / (double)(ptNext - pt);
            double b2 = (double)(pNext.getY() - p.getY()) / (double)(ptNext - pt);
            double bsqrd = (b1 - b2) * (b1 - b2);
        
            result += asqrd + bsqrd;
        }
        
        return Math.sqrt(result);
    }
    
}