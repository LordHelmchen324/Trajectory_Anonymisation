import java.util.Comparator;

class Place {

    public int x, y, t;

    public Place(int x, int y, int t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }

    public String description() {
        return "(" + x + "," + y + "," + t + ")";
    }

    public static Place makeOrigin() {
        return new Place(0, 0, 0);
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