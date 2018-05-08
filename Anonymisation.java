class Anonymisation {

    public static void main(String[] args) {
        Dataset d = Dataset.largeDummy();

        // Assigning IDs to the Trajectories to find their protected version later
        // TODO: Make sure IDs are unique
        int i = 0;
        for (Trajectory r : d.getTrajectories()) {
            r.id = i;
            i++;
        }

        System.out.println("Preparing DistanceMeasure and MedianStrategy ...");

        DistanceMeasure dM = new ShortTimeSeriesDistance();
        MedianStrategy mS = new XMedianY();

        System.out.println("DistanceMeasure and MedianStrategy prepared.");

        System.out.println("Protecting data set ...");

        Dataset result = d.protectedByMDAV(4, dM, mS);

        System.out.println("Data set protection finished.\n");

        System.out.println(result);

        double dr = DisclosureRisk.computeViaRecordLinkage(d, result);
        double il = InformationLoss.compute(d, result);

        System.out.println("Disclosure risk: " + dr);
        System.out.println("Information loss: " + il);
        System.out.println("");

        Plotter.plotRUMap(dr, il);
    }

}