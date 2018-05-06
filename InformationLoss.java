class InformationLoss {

    public static double compute(Dataset o, Dataset p) {
        double il_11 = InformationLoss.averageDifferenceMeans(o, p);
        double il_12 = InformationLoss.averageDifferenceAutocorrelation(o, p);
        double il_1 = (il_11 + il_12) / 2;

        double il_2 = InformationLoss.averageAbsoluteDifference(o, p);

        double il_3 = InformationLoss.spaceDistortion(o, p);

        return (il_1 + il_2 + il_3) / 3;    // TODO: Is that correct?
    }

    // IL_1.1
    private static double averageDifferenceMeans(Dataset o, Dataset p) {

    }

    // IL_1.2
    private static double averageDifferenceAutocorrelation(Dataset o, Dataset p) {

    }

    // IL_2
    private static double averageAbsoluteDifference(Dataset o, Dataset p) {

    }

    // IL_3
    private static double spaceDistortion(Dataset o, Dataset p) {

    }

}