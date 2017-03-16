import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

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
	private Double mean = null;
	private Double stddev = null;
	
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
		this.trials = trials;
		this.thresholds = new double[trials];
		Stopwatch stopwatch = new Stopwatch();
		int i = 0;
		for(; i < trials ; i++) {
			Percolation p = new Percolation(n);
			while(!p.percolates()) {
				int row = StdRandom.uniform(1, n+1);
				int col = StdRandom.uniform(1, n+1);
				p.open(row, col);
			}
			double threshold = (double) p.numberOfOpenSites() / (n*n);
			thresholds[i] = threshold;
		}
		System.out.println("trials " + i);
		System.out.println("time\t\t\t\t= " + stopwatch.elapsedTime() + "s");
	}
	
	public double mean() {
		mean = StdStats.mean(thresholds);
		return mean;
	}
	
	public double stddev() {
		stddev = StdStats.stddev(thresholds);
		return stddev;
	}
	
	public double confidenceLo() {
		if (mean == null) mean();
		if (stddev == null) stddev();
		return (mean - (1.96 * Math.sqrt(stddev) / Math.sqrt(trials)));
	}
	
	public double confidenceHi() {
		if (mean == null) mean();
		if (stddev == null) stddev();
		return (mean + (1.96 * Math.sqrt(stddev) / Math.sqrt(trials)));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(n, trials);
		
		System.out.println("mean\t\t\t\t= " + ps.mean());
		System.out.println("stddev\t\t\t\t= " + ps.stddev());
		System.out.println("95% confidence interval\t\t= [" + ps.confidenceLo() + 
						   ", " + ps.confidenceHi() + "]");
	}

}
