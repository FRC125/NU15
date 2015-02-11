package com.nutrons.lib;

public class MovingAverage {
	private double[] readings;
	private int sampleSize;
	private int index = 0;
	
	public MovingAverage(int sampleSize) {
		readings = new double[sampleSize];
		this.sampleSize = sampleSize;
	}
	
	private void update(double reading) {
		readings[index % sampleSize] = reading;
		index++;
	}
	
	public double getAverage(double reading) {
		update(reading);
		double sum = 0.0;
		for (double value : readings) {
			sum += value;
		}
		return sum / sampleSize;
	}
}
