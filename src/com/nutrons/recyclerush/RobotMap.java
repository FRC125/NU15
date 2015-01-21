package com.nutrons.recyclerush;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Motors
	public static final int DRIVE_LEFT = 0;
	public static final int DRIVE_CENTER = 1;
	public static final int DRIVE_RIGHT = 2;
	public static final int DRIVE_RIGHT_2 = 3;
	
	// Analog Input
	public static final int GYROSCOPE = 0;
	public static final int ULTRASONIC_AN = 1;
	public static final int ULTRASONIC_RX = 0;
	
	// User Input
	public static final int DRIVE_PAD = 0;
}
