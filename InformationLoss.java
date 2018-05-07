class InformationLoss {

    public static double compute(Dataset o, Dataset p) {
        if (o.size() != p.size()) {
            System.err.println("ERROR: Tried to compute information loss of different sized original and protected Datasets!");
            System.exit(-1);
        }

        double il11 = InformationLoss.averageDifferenceMeans(o, p);
        double il12 = InformationLoss.averageDifferenceAutocorrelation(o, p);
        double il1 = (il11 + il12) / 2;

        double il2 = InformationLoss.averageAbsoluteDifference(o, p);

        double il3 = InformationLoss.spaceDistortion(o, p);

        return (il1 + il2 + il3) / 3;    // TODO: Is that correct?
    }

    // IL_1.1
    private static double averageDifferenceMeans(Dataset o, Dataset p) {
        int s = o.size();

        double sum = 0.0;
        for (Trajectory ro : o.getTrajectories()) {
            Trajectory rp = p.getTrajectoryById(ro.id);
            if (rp == null) {
                System.err.println("Protected data set does not contain trajectory with id = " + ro.id +" from the original data set!");
                System.exit(-1);
            }

            double myxro = ro.averageX();
            double myxrp = rp.averageX();
            double a = Math.abs(myxro - myxrp) / Math.max(myxro, myxrp);

            double myyro = ro.averageY();
            double myyrp = rp.averageY();
            double b = Math.abs(myyro - myyrp) / Math.max(myyro, myyrp);

            sum += a + b;
        }

        return sum / (2 * s);
    }

    // IL_1.2
    private static double averageDifferenceAutocorrelation(Dataset o, Dataset p) {
        int s = o.size();

        double sum = 0.0;

        for (Trajectory ro : o.getTrajectories()) {
            Trajectory rp = p.getTrajectoryById(ro.id);
            int n = ro.length();

            double[] hs = { 0.0, (double)n / 4.0, (double)n / 2.0, 3 * (double)n / 4.0 };
            for (double h : hs) {
                double rhoo = ro.autocorrelation(h);        // TODO: kommt hier für 0 nicht immer 1 raus?
                double rhop = rp.autocorrelation(h);

                sum += Math.abs(rhoo - rhop) / Math.max(rhoo, rhop);
            }
        }

        return sum / (4 * s);
    }

    // IL_2
    private static double averageAbsoluteDifference(Dataset o, Dataset p) {
        int s = o.size();
        int n = 0;

        double sum = 0.0;
        for (Trajectory ro : o.getTrajectories()) {
            Trajectory rp = p.getTrajectoryById(ro.id);

            n = ro.length();
            for (int j = 0; j < n; j++) {
                Place po = ro.getPlaces().get(j);
                Place pp = rp.getPlaces().get(j);

                double a1 = Math.abs(Math.abs(po.getX()) - Math.abs(pp.getX()));
                double b1 = Math.max(Math.abs(po.getX()), Math.abs(pp.getX()));
                double a2 = Math.abs(Math.abs(po.getY()) - Math.abs(pp.getY()));
                double b2 = Math.max(Math.abs(po.getY()), Math.abs(pp.getY()));

                sum += a1 / b1 + a2 / b2;
            }
        }
        
        return sum / (2 * s * n);
    }

    // IL_3
    private static double spaceDistortion(Dataset o, Dataset p) {
        double sum = 0.0;
        for (Trajectory ro : o.getTrajectories()) {
            Trajectory rp = p.getTrajectoryById(ro.id);

            for (int j = 0; j < ro.length(); j++) {
                Place po = ro.getPlaces().get(j);
                Place pp = rp.getPlaces().get(j);

                sum += Math.sqrt(Math.pow(po.getX() - pp.getX(), 2) + Math.pow(po.getY() - pp.getY(), 2));
            }
        }

        return sum;
    }

}