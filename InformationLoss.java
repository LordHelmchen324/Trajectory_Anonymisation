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
        for (int i = 0; i < s; i++) {
            Trajectory ro = o.getTrajectories().get(i);
            Trajectory rp = p.getTrajectories().get(i);

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
        for (int i = 0; i < s; i++) {
            Trajectory ro = o.getTrajectories().get(i);
            Trajectory rp = p.getTrajectories().get(i);
            int n = ro.length();

            double[] hs = { 0, n / 4, n / 2, 3 * n / 4 };
            for (double h : hs) {
                double rhoo = ro.autocorrelation(h);
                double rhop = rp.autocorrelation(h);

                sum += Math.abs(rhoo - rhop) / Math.max(rhoo, rhop);
            }
        }

        return sum / (4 * s);
    }

    // IL_2
    private static double averageAbsoluteDifference(Dataset o, Dataset p) {
        int s = o.size();

        double sum = 0.0;
        for (int i = 0; i < s; i++) {
            Trajectory ro = o.getTrajectories().get(i);
            Trajectory rp = p.getTrajectories().get(i);

            int n = ro.length();    // TODO: What if original and protected have different length?
            for (int j = 0; j < n; j++) {    // TODO: They don't really have the same timestamps!
                Place po = ro.getPlaces().get(j);
                Place pp = rp.getPlaces().get(j);

                double a1 = Math.abs(Math.abs(po.getX()) - Math.abs(pp.getX()));
                double b1 = Math.max(Math.abs(po.getX()), Math.abs(pp.getX()));
                double a2 = Math.abs(Math.abs(po.getY()) - Math.abs(pp.getY()));
                double b2 = Math.max(Math.abs(po.getY()), Math.abs(pp.getY()));

                sum += a1 / b1 + a2 / b2;
            }
        }
        
        return sum / (2 * s * n);   // TODO: n of which Trajectoriy?
    }

    // IL_3
    private static double spaceDistortion(Dataset o, Dataset p) {
        int s = o.size();

        double sum = 0.0;
        for (int i = 0; i < s; i++) {
            double a = 
        }
    }

}