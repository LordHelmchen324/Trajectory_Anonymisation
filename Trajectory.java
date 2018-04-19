import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Trajectory {

    private List<Place> places = new ArrayList<Place>();

    public Trajectory() { }

    public Trajectory(Trajectory original) {
        for (Place p : original.places) this.add(new Place(p));
    }

    @Override
    public String toString() {
        String s = "[";
        Iterator<Place> i = this.places.iterator();
        while (i.hasNext()) {
            Place p = i.next();
            s += p.toString();
            if (i.hasNext()) s += ", ";
        }
        s += "]";
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Trajectory) {
            Trajectory t = (Trajectory)o;
            if (this.length() != t.length()) return false;
            for (int i = 0; i < this.length(); i++) {
                if (!this.places.get(i).equals(t.places.get(i))) return false;
            }
            return true;
        } else return false;
    }

    public int length() {
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
            sum += p.getX();
        }
    
        return (double)sum / (double)this.length();
    }

    private double meanY() {
        int sum = 0;
        for (Place p : this.places) {
            sum += p.getY();
        }
    
        return (double)sum / (double)this.length();
    }

    private double gammaPrimeA(double h) {
        int iterCount = this.length() - (int)Math.abs(h);
        double result = 0.0;
        for (int i = 0; i < iterCount; i++) {
            Place place = this.getPlaceAtIndex(i);
            Place shiftedPlace = this.getPlaceAtIndex(i + (int)Math.abs(h));
        
            result += ((double)shiftedPlace.getX() - this.meanX()) * ((double)place.getX() - this.meanX()) + ((double)shiftedPlace.getY() - this.meanY()) * ((double)place.getY() - this.meanY());
        }
    
        return result / (double)this.length();
    }

    public double autocorrelation(double h) {
        return this.gammaPrimeA(h) / this.gammaPrimeA(0);
    }


    // others

    public void lengthenToEqualLengthAs(Trajectory other) {
        if (other.length() == this.length()) return;
        if (this.length() > other.length()) {
            System.err.println("Attempted to lengthen Trajectory to length of shorter one!");
            return;
        }

        int missingCount = other.length() - this.length();

        for ( ; missingCount > 0; missingCount--) {
            if ((missingCount & 1) == 0) {  // even: add one at the end
                Place last = this.places.get(this.length() - 1);
                this.add(new Place(last.getX(), last.getY(), last.getT() + 1));
            } else {                        // odd: add at the start if possible, end otherwise
                Place first = this.places.get(0);
                if (first.getT() < 1) {
                    Place last = this.places.get(this.length() - 1);
                    this.add(new Place(last.getX(), last.getY(), last.getT() + 1));
                } else {
                    this.insertPlaceAtBeginning(new Place(first.getX(), first.getY(), first.getT() - 1));;
                }
            }
        }
    }

}