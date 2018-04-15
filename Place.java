import java.util.Comparator;

class Place {

    public int x, y, t;

    public Place(int x, int y, int t) {
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
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Place) {
            Place p = (Place)o;
            return (this.x == p.x) && (this.y == p.y) && (this.t == p.t);
        } else return false;
    }

    public static class XComparator implements Comparator<Place> {
        @Override
        public int compare(Place o1, Place o2) {
            return o1.x - o2.x;
        }
    }

    public static class YComparator implements Comparator<Place> {
        @Override
        public int compare(Place o1, Place o2) {
            return o1.y - o2.y;
        }
    }

}