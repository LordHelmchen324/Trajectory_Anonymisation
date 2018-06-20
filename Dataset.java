import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.gson.Gson;

class Dataset {

    private List<Trajectory> trajectories = new LinkedList<Trajectory>();

    public Dataset() { }

    public Dataset(Dataset original) {
        for (Trajectory r : original.trajectories) this.add(new Trajectory(r));
    }

    public static Dataset fromJSON(String jsonFilePath) {
        File datasetFile = new File(jsonFilePath);
        try (BufferedReader r = new BufferedReader(new FileReader(datasetFile))) {
            System.out.print("Reading data set from JSON file ... ");

            Gson gson = new Gson();
            Dataset d = gson.fromJson(r, Dataset.class);

            System.out.print("\rData set was read from JSON file: "+ d.size() + " trajectories\n");

            return d;
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file at path \"" + datasetFile.getName() + "\".");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("An I/O exception occured: " + e.getLocalizedMessage());
            System.exit(1);
        }
        return null;
    }

    public static void toJSON(Dataset d, String jsonFilePath) {
        File outputFile = new File(jsonFilePath);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(outputFile))) {
            System.out.print("\rWriting to JSON file: \'" + outputFile.getName() + "\' ... ");

            Gson gson = new Gson();
            gson.toJson(d, w);

            System.out.print("\rWrote to JSON file: \'" + outputFile.getName() + "\'            \n");
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file at path \"" + outputFile.getName() + "\".");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("An I/O exception occured: " + e.getLocalizedMessage());
            System.exit(1);
        }
    }

    public static void toCSV(Dataset d, String csvFilePath) {
        File outputFile = new File(csvFilePath);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(outputFile))) {
            System.out.print("\rWriting to CSV file: \'" + outputFile.getName() + "\' ... ");

            for (Trajectory r : d.getTrajectories()) {
                List<Long> ts = new ArrayList<Long>(r.getTimestamps());
                Collections.sort(ts);
                for (long t : ts) {
                    Place p = r.getPlaceAtTime(t);
                    w.write(r.id + "," + t + "," + p.getX() + "," + p.getY() + "\n");
                }
            }

            System.out.print("\rWrote to CSV file: \'" + outputFile.getName() + "\'            \n");
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file at path \"" + outputFile.getName() + "\".");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("An I/O exception occured: " + e.getLocalizedMessage());
            System.exit(1);
        }
    }

    public static Dataset geoLife() {
        return Dataset.fromJSON("../Geolife Trajectories 1.3/translated.json");
    }

    public static Dataset randomPerfect(int size, long timespan, long maxX, long maxY) {
        Random rand = new Random();

        Dataset d = new Dataset();
        
        for (int i = 0; i < size; i++) {
            Trajectory r = new Trajectory();
            for (long t = 0; t < timespan; t++) {
                long randomX = Math.abs(rand.nextLong() % maxX);
                long randomY = Math.abs(rand.nextLong() % maxY);
                r.add(t, new Place(randomX, randomY));
            }
            d.add(r);
        }

        return d;
    }

    public static Dataset randomPerfectOnGrid(int size, long timespan, long maxX, long maxY) {
        Random rand = new Random();
        final long gridStep = 50;

        Dataset d = new Dataset();

        long xGridLines = maxX / gridStep;
        long yGridLines = maxY / gridStep;

        for (int i = 0; i < size; i++) {
            Trajectory r = new Trajectory();
            r.id = i;

            long startX = Math.abs((rand.nextLong() % xGridLines) * gridStep);
            long startY = Math.abs((rand.nextLong() % yGridLines) * gridStep);
            Place prev = new Place(startX, startY);
            r.add(0, prev);

            for (long t = 1; t < timespan; t++) {
                int direction = (rand.nextInt() % 2);
                int step = (rand.nextInt() % 7) - 3;

                Place p;
                if (direction == 0) {
                    p = new Place(Math.abs(Math.min(prev.getX() + step * gridStep, 2000)), prev.getY());
                } else {
                    p = new Place(prev.getX(), Math.abs(Math.min(prev.getY() + step * gridStep, 2000)));
                }

                r.add(t, p);
                prev = p;
            }

            d.add(r);
        }

        return d;
    }

    public static Dataset smallDummy() {
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

    public static Dataset largeDummy() {
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

    @Override
    public String toString() {
        final String lineSeperator = "--------------";
        String s = "" + lineSeperator;
        for (Trajectory r : this.trajectories) s += "\n" + r.toString();
        s += "\n" + lineSeperator;
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Dataset) {
            Dataset d = (Dataset)o;
            if (this.size() != d.size()) return false;

            List<Trajectory> trajectoriesCopy = new LinkedList<Trajectory>(this.trajectories);
            for (int i = 0; i < d.size(); i++) {
                Trajectory r = d.getTrajectories().get(i);
                boolean found = false;
                for (int j = 0; j < trajectoriesCopy.size(); j++) {
                    if (trajectoriesCopy.get(j).equals(r)) {
                        trajectoriesCopy.remove(j);
                        found = true;
                        break;
                    }
                }
                if (!found) return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public void add(Trajectory r) {
        this.trajectories.add(r);
    }

    public void remove(Trajectory r) {
        this.trajectories.remove(r);
    }

    public int size() {
        return this.trajectories.size();
    }

    public int numberOfRecords() {
        int sum = 0;
        for (Trajectory r : this.trajectories) sum += r.length();
        return sum; 
    }

    public int numberOfRecordedTimestamps() {
        Set<Long> ts = new HashSet<Long>();
        for (Trajectory r : this.trajectories) ts.addAll(r.getTimestamps());
        return ts.size();
    }

    public Set<Long> recordedTimestamps() {
        Set<Long> ts = new HashSet<Long>();
        for (Trajectory r : this.trajectories) ts.addAll(r.getTimestamps());
        return ts;
    }

    public Trajectory getTrajectoryById(int id) {
        for (Trajectory r : this.trajectories) {
            if (r.id == id) return r;
        }
        return null;
    }

    public List<Trajectory> getTrajectories() {
        return this.trajectories;
    }

    // MDAV

    public Trajectory closestTrajectoryTo(Trajectory r, DistanceMeasure dM) {
        if (this.trajectories.isEmpty()) {
            System.err.println("Cannot return clostest Trajectory to t = " + r + " from within empty Dataset!");
            System.exit(1);
        }

        double minDistance = Double.MAX_VALUE;
        Trajectory closest = null;
        for (Trajectory s : this.trajectories) {
            double distance = dM.computeDistance(r, s);
            if (distance < minDistance || closest == null) {
                minDistance = distance;
                closest = s;
            }
        }

        return closest;
    }

    public Trajectory furthestTrajectoryTo(Trajectory r, DistanceMeasure dM) {
        if (this.trajectories.isEmpty()) {
            System.err.println("Cannot return furthest Trajectory to t = " + r + " from within empty Dataset!");
            System.exit(1);
        }

        double maxDistance = 0.0;
        Trajectory furthest = null;
        for (Trajectory s : this.trajectories) {
            double distance = dM.computeDistance(r, s);
            if (distance > maxDistance || furthest == null) {
                maxDistance = distance;
                furthest = s;
            }
        }

        return furthest;
    }

    public List<Trajectory> removeClusterAround(Trajectory r, int size, DistanceMeasure dM) {
        List<Trajectory> cluster = new LinkedList<Trajectory>();
        cluster.add(r);
        this.remove(r);

        for (int i = 0; i < size - 1; i++) {
            Trajectory closest = this.closestTrajectoryTo(r, dM);
            cluster.add(closest);
            this.remove(closest);
        }

        return cluster;
    }

    public void fillUpToEqualLength() {
        if (this.trajectories.isEmpty()) {
            System.err.println("Tried to fill up empty Dataset!");
            return;
        }

        // find longest
        Iterator<Trajectory> i = this.trajectories.iterator();
        Trajectory longest = i.next();
        while (i.hasNext()) {
            Trajectory r = i.next();
            if (r.length() > longest.length()) longest = r;
        }

        // fill up all the trajectories to the length of the longest
        for (Trajectory t : this.trajectories) t.lengthenToEqualLengthAs(longest);
    }

    public Dataset protectedByMDAV(int k, DistanceMeasure dM, MedianStrategy mS, long recordInterval) {
        System.out.print("\rProtecting data set for k = " + k + " ...");

        Dataset temp = new Dataset(this);

        List<List<Trajectory>> clusters = new LinkedList<List<Trajectory>>();

        while (temp.size() >= k) {
            System.out.print("\rProtecting data set for k = " + k + " : Trajectories remaining ... " + temp.size());

            //Trajectory avrg = mS.computeMedian(temp.trajectories);
            //Trajectory furthest = temp.furthestTrajectoryTo(avrg, dM);
            Trajectory furthest = temp.getTrajectories().get(0);    // Currently as alternative to median
            clusters.add(temp.removeClusterAround(furthest, k, dM));

            if (temp.size() >= k) {
                Trajectory furthest2 = temp.furthestTrajectoryTo(furthest, dM);
                clusters.add(temp.removeClusterAround(furthest2, k, dM));
            }
        }

        // Add remaining trajectories to the cluster with their closest neighbour
        for (Trajectory r : temp.getTrajectories()) {
            double closestDistance = Double.POSITIVE_INFINITY;
            List<Trajectory> chosenCluster = clusters.get(0);

            for (List<Trajectory> c : clusters) {
                for (Trajectory s : c) {
                    double distance = dM.computeDistance(r, s);
                    if (distance < closestDistance) {
                        chosenCluster = c;
                        closestDistance = distance;
                    }
                }
            }

            chosenCluster.add(r);
        }

        Dataset result = new Dataset();
        for (List<Trajectory> c : clusters) {
            Trajectory clusterMedian = mS.computeMedian(c);

            // Don't add if clusterMedian has interval longer than recordInterval
            List<Long> ts = new ArrayList<Long>(clusterMedian.getTimestamps());
            Collections.sort(ts);
            boolean lagging = false;
            for (int i = 0; i < ts.size() - 1; i++) {
                if (ts.get(i + 1) - ts.get(i) > recordInterval) {
                    lagging = true;
                    break;
                }
            }
            if (lagging) continue;

            for (Trajectory ro : c) {
                Trajectory rp = new Trajectory(clusterMedian);
                rp.id = ro.id;
                result.add(rp);
            }

            System.out.print("\rProtecting data set for k = " + k + " : Trajectories set to median ... " + result.size());
        }

        System.out.print("\rProtected data set for k = " + k + "                                                       \n");

        return result;
    }

}