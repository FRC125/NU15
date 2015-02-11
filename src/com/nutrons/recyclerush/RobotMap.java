package com.nutrons.recyclerush;


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
	public static final int DRIVE_RIGHT = 2;;
	public static final int ELEVATOR_MOTOR = 5;
	
	// Digital Input
	public static final int ELEVATOR_MAX_BUTTON = 2;
	public static final int ELEVATOR_MIN_BUTTON = 1;

	
	// Analog Input
	public static final int GYROSCOPE = 0;
	public static final int ULTRASONIC_AN = 1;
	public static final int ULTRASONIC_RX = 0;
	
	// User Input
	public static final int DRIVE_PAD = 0;
	public static final int OPERATOR_PAD = 1;
	
}
