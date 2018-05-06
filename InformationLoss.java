class InformationLoss {

    public static double compute(Dataset o, Dataset p) {
        // TODO: What if Datasets are of different size?

        double il_11 = InformationLoss.averageDifferenceMeans(o, p);
        double il_12 = InformationLoss.averageDifferenceAutocorrelation(o, p);
        double il_1 = (il_11 + il_12) / 2;

        double il_2 = InformationLoss.averageAbsoluteDifference(o, p);

        double il_3 = InformationLoss.spaceDistortion(o, p);

        return (il_1 + il_2 + il_3) / 3;    // TODO: Is that correct?
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

        double outerSum = 0.0;
        for (int h = 0; h < max; h++) {     // TODO: ???
            
            double innerSum = 0.0;
            for (int i = 0; i < s; i++) {
                double rhoo = o.getTrajectories().get(i).autocorrelation((double)h);
                double rhop = p.getTrajectories().get(i).autocorrelation((double)h);
                innerSum += Math.abs(rhoo - rhop);
            }

            outerSum += innerSum / s;
        }

        return outerSum / 4;
    }

    // IL_2
    private static double averageAbsoluteDifference(Dataset o, Dataset p) {

    }

    // IL_3
    private static double spaceDistortion(Dataset o, Dataset p) {

    }

}