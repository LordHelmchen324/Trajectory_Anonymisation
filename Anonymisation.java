class Anonymisation {

    public static void main(String[] args) {
        Dataset d = Dataset.fromJSON("./randomPerfect1000.json");

        DistanceMeasure dM = new ShortTimeSeriesDistance();
        MedianStrategy mS = new XMedianY();

        Dataset result = d.protectedByMDAV(9, dM, mS);

        Dataset.toJSON(result, "./randomPerfect1000_Result.json");
    }

}