import java.util.LinkedList;
import java.util.List;

class Anonymisation {

    public static void main(String[] args) {
        //Dataset d = Dataset.fromJSON("../Geolife Trajectories 1.3/MS_GeoLife_pandas-downsampled.json");
        /*Dataset d = Dataset.fromJSON("../San Francisco Taxi Data/CabSpotting_pandas-downsampled.json");

        DistanceMeasure dM = new SynchronisedDistance();
        List<Dataset> ds = new LinkedList<Dataset>(); ds.add(d);
        dM.createSupportData(ds);
        dM.removeImpossibleTrajectoriesFromDataset(d);
        MedianStrategy mS = new XMedianY();

        //int[] ks = { 2, 3, 6, 9, 12, 14 };
        int[] ks = { 9 };
        for (int k : ks) {
            Dataset result = d.protectedByMDAV(k, dM, mS, 120000);

            //Dataset.toJSON(result, "../Geolife Trajectories 1.3/MS_GeoLife_pandas-downsampled_PROTECTED.json");
            //Dataset.toCSV(result, "../Geolife Trajectories 1.3/MS_GeoLife_pandas-downsampled_PROTECTED.csv");
            Dataset.toJSON(result, "../San Francisco Taxi Data/CabSpotting_pandas-downsampled_PROTECTED_k" + k + ".json");
            Dataset.toCSV(result, "../San Francisco Taxi Data/CabSpotting_pandas-downsampled_PROTECTED_k" + k + ".csv");
        }*/
        
        Dataset d = Dataset.randomPerfectOnGrid(1000, 60, 2000, 2000);
        Dataset.toJSON(d, "../Perfect Data Set/perfect_grid_1000_60_2000_2000.json");
        Dataset.toCSV(d, "../Perfect Data Set/perfect_grid_1000_60_2000_2000.csv");
    }

}