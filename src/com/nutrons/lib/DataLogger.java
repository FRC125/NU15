package com.nutrons.lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * 
 * @author John Petryk
 *
 */

public class DataLogger {
	
	public void write(String filePath, String[] data) {
		try {
			File file = new File(filePath);
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			for (String datum : data) {
				writer.write(datum);
				writer.write(" , ");
			}
			writer.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
}
