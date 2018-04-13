import java.util.Comparator;

class Place {

    public int x, y, t;

    Place(int x, int y, int t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }

    static Place makeOrigin() {
        return new Place(0, 0, 0);
    }

    static class XComparator implements Comparator {
        int compare(Place o1, Place o2) {
            if (o1.x < o2.x) return -1;
            if (o1.x > o2.x) return 1;
            return 0;
        }
    }

    static class YComparator implements Comparator {
        int compare(Place o1, Place o2) {
            if (o1.y < o2.y) return -1;
            if (o1.y > o2.y) return 1;
            return 0;
        }
    }

}