import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * 
 */

/**
 * @author mityakoval
 *
 */
public class PercolationStats {
    private int trials;
    private double[] thresholds;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        this.trials = trials;
        this.thresholds = new double[trials];
        int i = 0;
        for (; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            double threshold = (double) p.numberOfOpenSites() / (n * n);
            thresholds[i] = threshold;
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        double mean = mean(); 
        double stddev = stddev();
        return (mean - (1.96 * stddev / Math.sqrt(trials)));
    }

    public double confidenceHi() {
        double mean = mean(); 
        double stddev = stddev();
        return (mean + (1.96 * stddev / Math.sqrt(trials)));
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);

        System.out.println("mean\t\t\t= " + ps.mean());
        System.out.println("stddev\t\t\t= " + ps.stddev());
        System.out.println("95% confidence interval\t= [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }

}
