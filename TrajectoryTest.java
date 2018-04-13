public class TrajectoryTest {

    void testAutocorrelation() {
        Trajectory r = new Trajectory();
        r.add(new Place(3, 6, 1));
        r.add(new Place(1, 7, 2));
        r.add(new Place(8, 5, 3));

        String autocorr0Msg = "Testing autocorrelation at 0.0 == 1.0 ... ";
        double autocorr0 = r.autocorrelation(0.0);
        if (autocorr0 == 1.0) autocorr0Msg += "PASSED";
        else autocorr0Msg += "FAILED\n  result = " + autocorr0;
        System.out.println(autocorr0Msg);
    }

}