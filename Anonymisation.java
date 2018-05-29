import java.util.LinkedList;
import java.util.List;

class Anonymisation {

    public static void main(String[] args) {
        Dataset d = Dataset.fromJSON("../Geolife Trajectories 1.3/MS_GeoLife_pandas-downsampled.json");

        DistanceMeasure dM = new SynchronisedDistance();
        List<Dataset> ds = new LinkedList<Dataset>(); ds.add(d);
        dM.createSupportData(ds);
        dM.removeImpossibleTrajectoriesFromDataset(d);
        MedianStrategy mS = new XMedianY();

        Dataset result = d.protectedByMDAV(9, dM, mS, 60000);

        Dataset.toJSON(result, "../Geolife Trajectories 1.3/MS_GeoLife_pandas-downsampled_PROTECTED.json");
        Dataset.toCSV(result, "../Geolife Trajectories 1.3/MS_GeoLife_pandas-downsampled_PROTECTED.csv");
    }

}