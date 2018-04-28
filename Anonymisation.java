import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

class Anonymisation {

    public static void main(String[] args) {
        System.out.print("Reading data set from JSON file ... ");

        File datasetFile = new File("../Geolife Trajectories 1.3/translated.json");
        try (BufferedReader r = new BufferedReader(new FileReader(datasetFile))) {
            Gson gson = new Gson();
            Dataset d = gson.fromJson(r, Dataset.class);

            System.out.print("done!\n");
            System.out.println("Size of the data set = " + d.size() + "\n");

            DistanceMeasure dM = new ShortTimeSeriesDistance();
            MedianStrategy mS = new XMedianY();

            System.out.println("Protecting data set ...");

            Dataset result = d.protectedByMDAV(9, dM, mS);

            System.out.println("Data set protection finished.\n");

            System.out.println("Orignal:\n" + d + "\n" + "Protected:\n" + result);
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file at path \"" + datasetFile.getName() + "\".");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("An I/O exception occured: " + e.getLocalizedMessage());
            System.exit(1);
        }
    }

    public static Dataset makeSmallDataset() {
        Dataset d = new Dataset();

        Trajectory t1 = new Trajectory();
        t1.add(new Place(1, 0, 1));
        t1.add(new Place(3, 2, 2));
        t1.add(new Place(4, 2, 3));
        t1.add(new Place(5, 2, 4));
        t1.add(new Place(5, 2, 5));
        t1.add(new Place(9, 7, 6));
        d.add(t1);

        Trajectory t11 = new Trajectory();
        t11.add(new Place(1, 0, 1));
        t11.add(new Place(3, 2, 2));
        t11.add(new Place(4, 2, 3));
        t11.add(new Place(5, 2, 4));
        t11.add(new Place(5, 2, 5));
        t11.add(new Place(9, 7, 6));
        d.add(t11);

        Trajectory t2 = new Trajectory();
        t2.add(new Place(1, 0, 1));
        t2.add(new Place(3, 2, 2));
        t2.add(new Place(4, 2, 3));
        t2.add(new Place(5, 2, 4));
        t2.add(new Place(5, 2, 5));
        t2.add(new Place(9, 7, 6));
        d.add(t2);
        d.add(t2);

        Trajectory t22 = new Trajectory();
        t22.add(new Place(1, 0, 1));
        t22.add(new Place(3, 6, 2));
        t22.add(new Place(5, 3, 3));
        t22.add(new Place(9, 7, 4));
        t22.add(new Place(2, 4, 5));
        t22.add(new Place(4, 2, 6));
        d.add(t22);

        return d;
    }

    public static Dataset makeLargeDataset() {
        Dataset d = new Dataset();
        
        // club 1

        Trajectory t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 0, 2));
        t.add(new Place(2, 0, 3));
        t.add(new Place(3, 0, 4));
        t.add(new Place(4, 0, 5));
        t.add(new Place(5, 0, 6));
        t.add(new Place(6, 0, 7));
        t.add(new Place(7, 0, 8));
        t.add(new Place(8, 0, 9));
        d.add(t);

        t = new Trajectory();
        t.add(new Place(1, 2, 1));
        t.add(new Place(1, 0, 2));
        t.add(new Place(2, 0, 3));
        t.add(new Place(3, 0, 4));
        t.add(new Place(4, 1, 5));
        t.add(new Place(5, 0, 6));
        t.add(new Place(6, 0, 7));
        t.add(new Place(7, 0, 8));
        t.add(new Place(8, 0, 9));
        d.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 0, 2));
        t.add(new Place(2, 1, 3));
        t.add(new Place(3, 1, 4));
        t.add(new Place(4, 0, 5));
        t.add(new Place(5, 0, 6));
        t.add(new Place(6, 0, 7));
        t.add(new Place(7, 0, 8));
        t.add(new Place(6, 1, 9));
        d.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 0, 2));
        t.add(new Place(2, 0, 3));
        t.add(new Place(3, 0, 4));
        t.add(new Place(4, 0, 5));
        t.add(new Place(5, 0, 6));
        t.add(new Place(6, 0, 7));
        t.add(new Place(7, 0, 8));
        t.add(new Place(9, 0, 9));
        d.add(t);

        t = new Trajectory();
        t.add(new Place(0, 1, 1));
        t.add(new Place(1, 1, 2));
        t.add(new Place(2, 1, 3));
        t.add(new Place(3, 0, 4));
        t.add(new Place(4, 0, 5));
        t.add(new Place(5, 0, 6));
        t.add(new Place(6, 0, 7));
        t.add(new Place(7, 0, 8));
        t.add(new Place(8, 0, 9));
        d.add(t);

        // club 2
        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 1, 2));
        t.add(new Place(2, 2, 3));
        t.add(new Place(3, 3, 4));
        t.add(new Place(4, 4, 5));
        t.add(new Place(5, 5, 6));
        t.add(new Place(6, 6, 7));
        t.add(new Place(7, 7, 8));
        t.add(new Place(8, 8, 9));
        d.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 1, 2));
        t.add(new Place(2, 3, 3));
        t.add(new Place(3, 4, 4));
        t.add(new Place(4, 5, 5));
        t.add(new Place(5, 6, 6));
        t.add(new Place(6, 7, 7));
        t.add(new Place(7, 8, 8));
        t.add(new Place(8, 8, 9));
        d.add(t);

        t = new Trajectory();
        t.add(new Place(0, 2, 1));
        t.add(new Place(1, 2, 2));
        t.add(new Place(2, 2, 3));
        t.add(new Place(3, 3, 4));
        t.add(new Place(4, 4, 5));
        t.add(new Place(5, 5, 6));
        t.add(new Place(6, 6, 7));
        t.add(new Place(7, 7, 8));
        t.add(new Place(8, 8, 9));
        d.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 1, 2));
        t.add(new Place(2, 2, 3));
        t.add(new Place(3, 3, 4));
        t.add(new Place(4, 4, 5));
        t.add(new Place(5, 5, 6));
        t.add(new Place(6, 7, 7));
        t.add(new Place(8, 8, 8));
        t.add(new Place(9, 9, 9));
        d.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(1, 1, 2));
        t.add(new Place(1, 2, 3));
        t.add(new Place(2, 3, 4));
        t.add(new Place(4, 4, 5));
        t.add(new Place(5, 5, 6));
        t.add(new Place(6, 6, 7));
        t.add(new Place(7, 7, 8));
        t.add(new Place(8, 8, 9));
        d.add(t);

        // club 3

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(0, 1, 2));
        t.add(new Place(0, 2, 3));
        t.add(new Place(0, 3, 4));
        t.add(new Place(0, 4, 5));
        t.add(new Place(0, 5, 6));
        t.add(new Place(0, 6, 7));
        t.add(new Place(0, 7, 8));
        t.add(new Place(0, 8, 9));
        d.add(t);

        t = new Trajectory();
        t.add(new Place(1, 0, 1));
        t.add(new Place(1, 1, 2));
        t.add(new Place(0, 2, 3));
        t.add(new Place(0, 3, 4));
        t.add(new Place(0, 4, 5));
        t.add(new Place(0, 5, 6));
        t.add(new Place(0, 6, 7));
        t.add(new Place(0, 7, 8));
        t.add(new Place(1, 8, 9));
        d.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(0, 1, 2));
        t.add(new Place(0, 2, 3));
        t.add(new Place(0, 3, 4));
        t.add(new Place(1, 4, 5));
        t.add(new Place(1, 5, 6));
        t.add(new Place(2, 6, 7));
        t.add(new Place(1, 7, 8));
        t.add(new Place(0, 8, 9));
        d.add(t);

        t = new Trajectory();
        t.add(new Place(0, 2, 1));
        t.add(new Place(0, 2, 2));
        t.add(new Place(0, 2, 3));
        t.add(new Place(0, 3, 4));
        t.add(new Place(0, 4, 5));
        t.add(new Place(0, 5, 6));
        t.add(new Place(0, 6, 7));
        t.add(new Place(0, 7, 8));
        t.add(new Place(0, 8, 9));
        d.add(t);

        t = new Trajectory();
        t.add(new Place(0, 0, 1));
        t.add(new Place(0, 1, 2));
        t.add(new Place(0, 2, 3));
        t.add(new Place(0, 3, 4));
        t.add(new Place(0, 4, 5));
        t.add(new Place(0, 5, 6));
        t.add(new Place(0, 7, 7));
        t.add(new Place(0, 8, 8));
        t.add(new Place(0, 9, 9));
        d.add(t);

        return d;
    }

}