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
	public static final int WINCH_MOTOR = 6;
	
	// Digital Input
	public static final int ELEVATOR_MAX_BUTTON = 2;
	public static final int ELEVATOR_MIN_BUTTON = 1;
	public static final int STACKABLE_BUTTON = 0;
	public static final int ENCODER_LEFT_DRIVETRAIN_A = 5;
	public static final int ENCODER_LEFT_DRIVETRAIN_B = 6;
	public static final int ENCODER_RIGHT_DRIVETRAIN_A = 4;
	public static final int ENCODER_RIGHT_DRIVETRAIN_B = 3;
	public static final int TOTE_BANNER = 8; // TODO: CHANGE THIS TO SOMETHING REALISTIC
	
	// Pneumatics
	public static final int LEFT_INTAKE_WHEEL_PISTON = 7;
	public static final int RIGHT_INTAKE_WHEEL_PISTON = 0;
	public static final int DOUBLE_PUSHER_A = 1;
	public static final int DOUBLE_PUSHER_B = 5;
	public static final int DOUBLE_STACK_HOLDER_A = 2;
	public static final int DOUBLE_STACK_HOLDER_B = 6;
	public static final int CAN_STOPPER_PISTON = 1;
	public static final int WINTAKE_STOPPER_PISTON = 3;
	public static final int CAN_GRABBER_A = 5;
	public static final int CAN_GRABBER_B = 4;
	public static final int CAN_GRABBER_HANDLE_A = 0;
	public static final int CAN_GRABBER_HANDLE_B = 0;
	
	// Analog Input
	public static final int GYROSCOPE = 0;
	public static final int ULTRASONIC_AN = 2;
	public static final int ULTRASONIC_RX = 7;
	
	// User Input
	public static final int DRIVE_PAD = 0;
	public static final int OPERATOR_PAD = 1;
	
}
