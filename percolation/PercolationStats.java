

import edu.princeton.cs.algs4.StdRandom;

import edu.princeton.cs.algs4.StdStats;

import java.lang.Math.*;

public class PercolationStats {




	private double[] results;

	private int reps = -1;



   public PercolationStats(int n, int trials) throws IllegalArgumentException{


   		if(n <= 0 || trials <= 0){
   			throw new IllegalArgumentException();
   		}

   		reps = trials;

   		results = new double[trials];


   		for(int i=0; i<trials; i++){


   			Percolation p = new Percolation(n);

   			while(p.percolates() == false){

   				int row = StdRandom.uniform(1, n+1);

   				int col = StdRandom.uniform(1, n+1);

   				p.open(row, col);
   			}

   			//System.out.println(p.numberOfOpenSites() + "\n");

   			Double units = (double)n * n;

   			results[i] = ((double)p.numberOfOpenSites()) / units;

   		}

   }    // perform trials independent experiments on an n-by-n grid


   public double mean(){

   		return StdStats.mean(results);
   }                          // sample mean of percolation threshold
   


   public double stddev(){

   	return StdStats.stddev(results);



   }                        // sample standard deviation of percolation threshold
   

   public double confidenceLo(){

   	return mean() - 1.96 * stddev() / Math.sqrt(reps);

   }                  // low  endpoint of 95% confidence interval
   


   public double confidenceHi(){

   	return mean() + 1.96 * stddev() / Math.sqrt(reps);

   }                  // high endpoint of 95% confidence interval




   public static void main(String[] args){


   		if(args.length < 2){
   			System.out.println("Error: not enough args.");
   			System.exit(1);
   		}

   		int first = Integer.parseInt(args[0]);
   		int second = Integer.parseInt(args[1]);

   		PercolationStats ps = new PercolationStats(first, second);


   		System.out.println("mean = " + ps.mean());
   		System.out.println("stddev = " + ps.stddev());
   		System.out.println("95% confidence interval = [ "  
   			+ ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
   		//System.out.println("hi: " + ps.confidenceHi() + "\n");



   }        // test client (described below)


}