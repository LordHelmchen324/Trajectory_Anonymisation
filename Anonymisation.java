class Anonymisation {

    public static void main(String[] args) {
        Dataset d = new Dataset();

        Trajectory t1 = new Trajectory();
        t1.add(new Place(1, 0, 2));
        t1.add(new Place(3, 2, 3));
        t1.add(new Place(4, 2, 4));
        t1.add(new Place(5, 2, 6));
        t1.add(new Place(5, 2, 7));
        t1.add(new Place(9, 7, 8));
        d.add(t1);

        Trajectory t11 = new Trajectory();
        t11.add(new Place(1, 0, 1));
        t11.add(new Place(3, 2, 2));
        t11.add(new Place(4, 2, 3));
        t11.add(new Place(5, 2, 4));
        t11.add(new Place(5, 2, 5));
        t11.add(new Place(9, 7, 6));
        d.add(t11);

        Trajectory t2 = new Trajectory();
        t2.add(new Place(1, 0, 0));
        t2.add(new Place(9, 7, 7));
        d.add(t2);
        d.add(t2);

        Trajectory t22 = new Trajectory();
        t22.add(new Place(1, 0, 0));
        t22.add(new Place(5, 3, 4));
        t22.add(new Place(9, 7, 7));
        d.add(t22);

        System.out.println("Before filling it up:\n" + d);
        d.fillUpToEqualLength();
        System.out.println("After filling it up:\n" + d);

        DistanceMeasure dM = new ShortTimeSeriesDistance();
        MedianStrategy mS = new XMedianY();

        Dataset result = d.protectedByMDAV(2, dM, mS);

        System.out.println("Orignal:\n" + d + "\n" + "Protected:\n" + result);
    }

}