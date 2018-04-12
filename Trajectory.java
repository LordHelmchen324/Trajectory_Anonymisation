import java.util.ArrayList;
import java.util.List;

class Trajectory {

    private List<Place> places;

    Trajectory() {
        this.places = new ArrayList<Place>();
    }

    Trajectory(Trajectory source) {
        this.places = new ArrayList<Place>();
        for (int i = 0; i < source.lenght(); i++) {
            this.places.add(source.places.get(i));
        }
    }

    int lenght() {
        return this.places.size();
    }

    Place getPlaceAtIndex(int i) {
        return places.get(i);
    }

    void add(Place p) {
        this.places.add(p);
    }

    void insertPlaceAtBeginning(Place p) {
        this.places.add(0, p);
    }

    // Euclidean Distance

    static double euclideanDistance(final Trajectory r, final Trajectory s) {
        if (r.lenght() != s.lenght()) {
            System.err.println("Trajectories have different length!");
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

    // Short Time Series Distance

    static double shortTimeSeriesDistance(final Trajectory r, final Trajectory s) {
        if (r.lenght() != s.lenght()) {
            System.err.println("Trajectories have different length!");
        }

        Place origin = new Place(0, 0, 0);
        Trajectory rCopy = new Trajectory(r);
        rCopy.insertPlaceAtBeginning(origin);
        Trajectory sCopy = new Trajectory(s);
        sCopy.insertPlaceAtBeginning(origin);

        double result = 0.0;
        for (int i = 0; i < rCopy.lenght() - 1 ; i++) {
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

    // Autocorrelation

    private double meanX() {
        int sum = 0;
        for (int i = 0; i < this.lenght(); i++) {
            sum += this.places.get(i).x;
        }
    
        return (double)sum / (double)this.lenght();
    }

    private double meanY() {
        int sum = 0;
        for (int i = 0; i < this.lenght(); i++) {
            sum += this.places.get(i).y;
        }
    
        return (double)sum / (double)this.lenght();
    }

    private double gammaPrimeA(double h) {
        int iterCount = this.lenght() - (int)Math.abs(h);
        double result = 0.0;
        for (int i = 0; i < iterCount; i++) {
            Place place = this.getPlaceAtIndex(i);
            Place shiftedPlace = this.getPlaceAtIndex(i + (int)Math.abs(h));
        
            result += ((double)shiftedPlace.x - this.meanX()) * ((double)place.x - this.meanX()) + ((double)shiftedPlace.y - this.meanY()) * ((double)place.y - this.meanY());
        }
    
        return result / (double)this.lenght();
    }

    double autocorrelation(double h) {
        return this.gammaPrimeA(h) / this.gammaPrimeA(0);
    }

}