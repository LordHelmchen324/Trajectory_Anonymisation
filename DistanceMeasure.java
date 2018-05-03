public interface DistanceMeasure {

    void prepareDataset(Dataset d);

    double computeDistance(final Trajectory r, final Trajectory s);

}