import java.util.Comparator;

class Place {

    private long x, y, t;

    public Place(long x, long y, long t) {
        if (t < 0) {
            System.err.println("Illegaly attempted to create Place (" + x + "," + y + "," + t + ") with t < 0 !");
            System.exit(1);
        }

        this.x = x;
        this.y = y;
        this.t = t;
    }

    public Place(Place original) {
        this(original.x, original.y, original.t);
    }

    public static Place makeOrigin() {
        return new Place(0, 0, 0);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + "," + this.t + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Place) {
            Place p = (Place)o;
            return (this.x == p.x) && (this.y == p.y) && (this.t == p.t);
        } else return false;
    }

    public long getX() { return this.x; }
    public long getY() { return this.y; }
    public long getT() { return this.t; }

    public static class XComparator implements Comparator<Place> {
        @Override
        public int compare(Place o1, Place o2) {
            return (int)(o1.getX() - o2.getX());
        }
    }

    public static class YComparator implements Comparator<Place> {
        @Override
        public int compare(Place o1, Place o2) {
            return (int)(o1.getY() - o2.getY());
        }
    }

    public static class TComparator implements Comparator<Place> {
        @Override
        public int compare(Place o1, Place o2) {
            return (int)(o1.getT() - o2.getT());
        }
    }

}