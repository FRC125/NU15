package com.nutrons.lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import com.nutrons.recyclerush.Robot;

/**
 * 
 * @author John Petryk, Toby Holtzman
 *
 */

public class DataLogger {
	
	private String property;
	private double frequency;
	
	public DataLogger(String property, double frequency) {
		this.property = property;
		this.frequency = frequency;
	}
	
	public void write(String filePath, String[] data) {
		try {
			File file = new File(filePath);
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			for (String datum : data) {
				writer.write(datum);
				writer.write(" , ");
				((BufferedWriter) writer).newLine();
			}
			writer.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public void log(double value, double time) {
		if(shouldWrite(this.frequency, 135.0, time)) {
			write("Log.csv", new String[] {"Property: " + property, "Value: " + value, "time: " + time});
		}
	}
	
	public boolean shouldWrite(double frequency, double totalTime, double currentTime) {
		double interval = totalTime / frequency;
		
		return currentTime % interval == 0;
	}
	
	public HashMap<String, Integer> getAllPorts() {
		HashMap<String, Integer> allHashes = new HashMap<String, Integer>();
		allHashes.putAll(Robot.dt.getLogInfo());
		return allHashes;
	}
}
