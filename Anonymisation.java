class Anonymisation {

    public static void main(String[] args) {
        Dataset d = Dataset.fromJSON("../Geolife Trajectories 1.3/MS_GeoLife_pandas-downsampled.json");

        DistanceMeasure dM = new SynchronisedDistance();
        dM.createSupportData(d);
        MedianStrategy mS = new XMedianY();

        Dataset result = d.protectedByMDAV(9, dM, mS);

        System.out.println("|d| = " + d.numberOfRecordedTimestamps());
        System.out.println("|result| = " + result.numberOfRecordedTimestamps());

        Dataset.toJSON(result, "../Geolife Trajectories 1.3/MS_GeoLife_pandas-downsampled_PROTECTED.json");
    }

}