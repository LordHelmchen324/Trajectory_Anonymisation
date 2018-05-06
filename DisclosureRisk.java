class DisclosureRisk {

    public static double computeViaRecordLinkage(Dataset o, Dataset p) {
        Trajectory[][] lp = DisclosureRisk.linkTrajectories(o, p);

        DistanceMeasure eu = new EuclideanDistance();
        DistanceMeasure sts = new ShortTimeSeriesDistance();

        double sum = 0.0;
        for (Trajectory[] pair : lp) {
            sum += Math.max(eu.computeDistance(pair[0], pair[1]), sts.computeDistance(pair[0], pair[1]));
        }

        return sum / o.size();         // TODO: I literally just guessed that this is how it works :/
    }

    private static Trajectory[][] linkTrajectories(Dataset o, Dataset p) {
        Trajectory[][] lp = new Trajectory[o.size()][2];

        DistanceMeasure eu = new EuclideanDistance();
        DistanceMeasure sts = new ShortTimeSeriesDistance();

        int i = 0;
        for (Trajectory ro : o.getTrajectories()) {
            Trajectory closest = null;
            double closestDistance = Double.POSITIVE_INFINITY;
            for (Trajectory rp : p.getTrajectories()) {
                double distance = Math.max(eu.computeDistance(ro, rp), sts.computeDistance(ro, rp));

                if (closest == null || distance < closestDistance) {
                    closest = rp;
                    closestDistance = distance;
                }
            }

            lp[i][0] = ro;
            lp[i][1] = closest;
        }

        return lp;
    }

}