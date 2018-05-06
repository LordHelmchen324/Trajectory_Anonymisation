import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

class Anonymisation {

    public static void main(String[] args) {
        Dataset d = new Dataset();

        File datasetFile = new File("../Geolife Trajectories 1.3/translated.json");
        try (BufferedReader r = new BufferedReader(new FileReader(datasetFile))) {
            System.out.print("Reading data set from JSON file ... ");

            Gson gson = new Gson();
            //d = makeLargeDataset();
            d = gson.fromJson(r, Dataset.class);

            System.out.print("done!\n");
            System.out.println(" -> Size of the data set = " + d.size() + "\n");
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file at path \"" + datasetFile.getName() + "\".");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("An I/O exception occured: " + e.getLocalizedMessage());
            System.exit(1);
        }

        System.out.println("Preparing DistanceMeasure and MedianStrategy ...");

        DistanceMeasure dM = new SynchronisedDistance();
        dM.createSupportData(d);
        dM.removeImpossibleTrajectoriesFromDataset(d);

        MedianStrategy mS = new XMedianY();

        System.out.println("DistanceMeasure and MedianStrategy prepared.");

        System.out.println("Protecting data set ...");

        Dataset result = d.protectedByMDAV(4, dM, mS);

        System.out.println("Data set protection finished.\n");

        //System.out.println("Orignal:\n" + d + "\n" + "Protected:\n" + result);

        // TODO: Dummy
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("python plot_ru_map.py 50.0 25.0 25.0 42.0");
        } catch (IOException e) {
            System.err.println("An I/O exception occured: " + e.getLocalizedMessage());
            System.exit(1);
        }
    }

    public static Dataset makeSmallDataset() {
        Dataset d = new Dataset();

        Trajectory t1 = new Trajectory();
        t1.add(1, new Place(1, 0));
        t1.add(2, new Place(3, 2));
        t1.add(3, new Place(4, 2));
        t1.add(4, new Place(5, 2));
        t1.add(5, new Place(5, 2));
        t1.add(6, new Place(9, 7));
        d.add(t1);

        Trajectory t11 = new Trajectory();
        t11.add(1, new Place(1, 0));
        t11.add(2, new Place(3, 2));
        t11.add(3, new Place(4, 2));
        t11.add(4, new Place(5, 2));
        t11.add(5, new Place(5, 2));
        t11.add(6, new Place(9, 7));
        d.add(t11);

        Trajectory t2 = new Trajectory();
        t2.add(1, new Place(1, 0));
        t2.add(2, new Place(3, 2));
        t2.add(3, new Place(4, 2));
        t2.add(4, new Place(5, 2));
        t2.add(5, new Place(5, 2));
        t2.add(6, new Place(9, 7));
        d.add(t2);
        d.add(t2);

        Trajectory t22 = new Trajectory();
        t22.add(1, new Place(1, 0));
        t22.add(2, new Place(3, 6));
        t22.add(3, new Place(5, 3));
        t22.add(4, new Place(9, 7));
        t22.add(5, new Place(2, 4));
        t22.add(6, new Place(4, 2));
        d.add(t22);

        return d;
    }

    public static Dataset makeLargeDataset() {
        Dataset d = new Dataset();
        
        // club 1

        Trajectory t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 0));
        t.add(3, new Place(2, 0));
        t.add(4, new Place(3, 0));
        t.add(5, new Place(4, 0));
        t.add(6, new Place(5, 0));
        t.add(7, new Place(6, 0));
        t.add(8, new Place(7, 0));
        t.add(9, new Place(8, 0));
        d.add(t);

        t = new Trajectory();
        t.add(1, new Place(1, 2));
        t.add(2, new Place(1, 0));
        t.add(3, new Place(2, 0));
        t.add(4, new Place(3, 0));
        t.add(5, new Place(4, 1));
        t.add(6, new Place(5, 0));
        t.add(7, new Place(6, 0));
        t.add(8, new Place(7, 0));
        t.add(9, new Place(8, 0));
        d.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 0));
        t.add(3, new Place(2, 1));
        t.add(4, new Place(3, 1));
        t.add(5, new Place(4, 0));
        t.add(6, new Place(5, 0));
        t.add(7, new Place(6, 0));
        t.add(8, new Place(7, 0));
        t.add(9, new Place(6, 1));
        d.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 0));
        t.add(3, new Place(2, 0));
        t.add(4, new Place(3, 0));
        t.add(5, new Place(4, 0));
        t.add(6, new Place(5, 0));
        t.add(7, new Place(6, 0));
        t.add(8, new Place(7, 0));
        t.add(9, new Place(9, 0));
        d.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 1));
        t.add(2, new Place(1, 1));
        t.add(3, new Place(2, 1));
        t.add(4, new Place(3, 0));
        t.add(5, new Place(4, 0));
        t.add(6, new Place(5, 0));
        t.add(7, new Place(6, 0));
        t.add(8, new Place(7, 0));
        t.add(9, new Place(8, 0));
        d.add(t);

        // club 2
        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 1));
        t.add(3, new Place(2, 2));
        t.add(4, new Place(3, 3));
        t.add(5, new Place(4, 4));
        t.add(6, new Place(5, 5));
        t.add(7, new Place(6, 6));
        t.add(8, new Place(7, 7));
        t.add(9, new Place(8, 8));
        d.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 1));
        t.add(3, new Place(2, 3));
        t.add(4, new Place(3, 4));
        t.add(5, new Place(4, 5));
        t.add(6, new Place(5, 6));
        t.add(7, new Place(6, 7));
        t.add(8, new Place(7, 8));
        t.add(9, new Place(8, 8));
        d.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 2));
        t.add(2, new Place(1, 2));
        t.add(3, new Place(2, 2));
        t.add(4, new Place(3, 3));
        t.add(5, new Place(4, 4));
        t.add(6, new Place(5, 5));
        t.add(7, new Place(6, 6));
        t.add(8, new Place(7, 7));
        t.add(9, new Place(8, 8));
        d.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 1));
        t.add(3, new Place(2, 2));
        t.add(4, new Place(3, 3));
        t.add(5, new Place(4, 4));
        t.add(6, new Place(5, 5));
        t.add(7, new Place(6, 7));
        t.add(8, new Place(8, 8));
        t.add(9, new Place(9, 9));
        d.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(1, 1));
        t.add(3, new Place(1, 2));
        t.add(4, new Place(2, 3));
        t.add(5, new Place(4, 4));
        t.add(6, new Place(5, 5));
        t.add(7, new Place(6, 6));
        t.add(8, new Place(7, 7));
        t.add(9, new Place(8, 8));
        d.add(t);

        // club 3

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(0, 1));
        t.add(3, new Place(0, 2));
        t.add(4, new Place(0, 3));
        t.add(5, new Place(0, 4));
        t.add(6, new Place(0, 5));
        t.add(7, new Place(0, 6));
        t.add(8, new Place(0, 7));
        t.add(9, new Place(0, 8));
        d.add(t);

        t = new Trajectory();
        t.add(1, new Place(1, 0));
        t.add(2, new Place(1, 1));
        t.add(3, new Place(0, 2));
        t.add(4, new Place(0, 3));
        t.add(5, new Place(0, 4));
        t.add(6, new Place(0, 5));
        t.add(7, new Place(0, 6));
        t.add(8, new Place(0, 7));
        t.add(9, new Place(1, 8));
        d.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(0, 1));
        t.add(3, new Place(0, 2));
        t.add(4, new Place(0, 3));
        t.add(5, new Place(1, 4));
        t.add(6, new Place(1, 5));
        t.add(7, new Place(2, 6));
        t.add(8, new Place(1, 7));
        t.add(9, new Place(0, 8));
        d.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 2));
        t.add(2, new Place(0, 2));
        t.add(3, new Place(0, 2));
        t.add(4, new Place(0, 3));
        t.add(5, new Place(0, 4));
        t.add(6, new Place(0, 5));
        t.add(7, new Place(0, 6));
        t.add(8, new Place(0, 7));
        t.add(9, new Place(0, 8));
        d.add(t);

        t = new Trajectory();
        t.add(1, new Place(0, 0));
        t.add(2, new Place(0, 1));
        t.add(3, new Place(0, 2));
        t.add(4, new Place(0, 3));
        t.add(5, new Place(0, 4));
        t.add(6, new Place(0, 5));
        t.add(7, new Place(0, 7));
        t.add(8, new Place(0, 8));
        t.add(9, new Place(0, 9));
        d.add(t);

        return d;
    }

}