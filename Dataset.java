import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class Dataset {

    private List<Trajectory> trajectories = new LinkedList<Trajectory>();

    public Dataset() { }

    public Dataset(Dataset original) {
        for (Trajectory r : original.trajectories) this.add(new Trajectory(r));
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

    public List<Trajectory> getTrajectories() {
        return this.trajectories;
    }

    // MDAV

    public Trajectory closestTrajectoryTo(Trajectory r, DistanceMeasure dM) {
        if (this.trajectories.isEmpty()) {
            System.err.println("Cannot return clostest Trajectory to t = " + r + " from within empty Dataset!");
            System.exit(1);
        }

        System.out.print("      > Finding closest trajectory ... ");

        double minDistance = Double.MAX_VALUE;
        Trajectory closest = null;
        for (Trajectory s : this.trajectories) {
            double distance = dM.computeDistance(r, s);
            if (distance < minDistance || closest == null) {
                minDistance = distance;
                closest = s;
            }
        }

        System.out.print("done!\n");

        return closest;
    }

    public Trajectory furthestTrajectoryTo(Trajectory r, DistanceMeasure dM) {
        if (this.trajectories.isEmpty()) {
            System.err.println("Cannot return furthest Trajectory to t = " + r + " from within empty Dataset!");
            System.exit(1);
        }

        System.out.print("      > Finding furthest trajectory ... ");

        double maxDistance = 0.0;
        Trajectory furthest = null;
        for (Trajectory s : this.trajectories) {
            double distance = dM.computeDistance(r, s);
            if (distance > maxDistance || furthest == null) {
                maxDistance = distance;
                furthest = s;
            }
        }

        System.out.print("done!\n");

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

        System.out.println("    > Made cluster of size " + size);

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

    public Dataset protectedByMDAV(int k, DistanceMeasure dM, MedianStrategy mS) {
        System.out.print(" -> Creating a temporary copy of the dataset ... ");

        Dataset temp = new Dataset(this);

        System.out.print("done!\n");

        List<List<Trajectory>> clusters = new LinkedList<List<Trajectory>>();

        System.out.println(" -> Clustering ...");

        Random rand = new Random();
        while (temp.size() > k) {
            //Trajectory avrg = mS.computeMedian(temp.trajectories);
            Trajectory avrg = temp.getTrajectories().get(rand.nextInt(temp.size()));
            Trajectory furthest = temp.furthestTrajectoryTo(avrg, dM);
            clusters.add(temp.removeClusterAround(furthest, k, dM));

            System.out.println("    > " + temp.size() + " trajecotires remaining");

            if (temp.size() > k) {
                Trajectory furthest2 = temp.furthestTrajectoryTo(avrg, dM);
                clusters.add(temp.removeClusterAround(furthest2, k, dM));

                System.out.println("    > " + temp.size() + " trajecotires remaining");
            }
        }

        List<Trajectory> lastCluster = clusters.get(clusters.size() - 1);
        Iterator<Trajectory> it = temp.trajectories.iterator();
        while (it.hasNext()) {
            Trajectory r = it.next();
            lastCluster.add(r);
            it.remove();
        }

        System.out.println(" -> Setting clusters to their median ...");

        Dataset result = new Dataset();
        for (List<Trajectory> c : clusters) {
            Trajectory clusterMedian = mS.computeMedian(c);
            for (Trajectory ro : c) {
                Trajectory rp = new Trajectory(clusterMedian);
                rp.id = ro.id;
                result.add(rp);
            }
        }

        return result;
    }

}