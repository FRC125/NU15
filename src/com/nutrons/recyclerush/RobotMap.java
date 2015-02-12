package com.nutrons.recyclerush;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Motors
	public static final int DRIVE_LEFT = 4;
	public static final int DRIVE_CENTER = 2;
	public static final int DRIVE_RIGHT = 3;
	public static final int ELEVATOR_MOTOR = 5;
	public static final int INTAKE_MOTOR = 0;
	public static final int WINTAKE_MOTOR = 1;
	
	// Digital Input
	public static final int ELEVATOR_MAX_BUTTON = 2;
	public static final int ELEVATOR_MIN_BUTTON = 1;
	public static final int STACKABLE_BUTTON = 3;
	public static final int ELEVATOR_REST_BUTTON = 4;
	
	// Pneumatics
	public static final int LEFT_INTAKE_WHEEL_PISTON = 0;
	public static final int RIGHT_INTAKE_WHEEL_PISTON = 7;
	
	// Analog Input
	public static final int GYROSCOPE = 0;
	public static final int ULTRASONIC_AN = 1;
	public static final int ULTRASONIC_RX = 0;
	
	// User Input
	public static final int DRIVE_PAD = 0;
	public static final int OPERATOR_PAD = 1;
}
