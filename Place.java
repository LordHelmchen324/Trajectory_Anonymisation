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

}