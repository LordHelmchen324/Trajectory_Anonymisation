import java.util.ArrayList;
import java.util.List;

class Trajectory {

    private List<Place> places = new ArrayList<Place>();

    public Trajectory() { }

    public Trajectory(Trajectory original) {
        for (Place p : original.places) this.add(new Place(p));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Trajectory) {
            Trajectory t = (Trajectory)o;
            if (this.lenght() != t.lenght()) return false;
            for (int i = 0; i < this.lenght(); i++) {
                if (!this.places.get(i).equals(t.places.get(i))) return false;
            }
            return true;
        } else return false;
    }

    public int lenght() {
        return this.places.size();
    }

    public Place getPlaceAtIndex(int i) {
        return places.get(i);
    }

    public List<Place> getPlaces() {
        return this.places;
    }

    public void add(Place p) {
        this.places.add(p);
    }

    public void insertPlaceAtBeginning(Place p) {
        this.places.add(0, p);
    }

    // Autocorrelation

    private double meanX() {
        int sum = 0;
        for (Place p : this.places) {
            sum += p.x;
        }
    
        return (double)sum / (double)this.lenght();
    }

    private double meanY() {
        int sum = 0;
        for (Place p : this.places) {
            sum += p.y;
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

    public double autocorrelation(double h) {
        return this.gammaPrimeA(h) / this.gammaPrimeA(0);
    }

}