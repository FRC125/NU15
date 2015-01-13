package com.nutrons.recyclerush;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	private final Joystick driverPad = new Joystick(RobotMap.DRIVE_PAD);
	public final Joystick operatorPad = new Joystick(0);
	private final int DRIVE_LEFT_AXIS= 1;
	private final int DRIVE_WHEEL_AXIS = 2;
	
	public double getDriveThrottle() {
		return driverPad.getRawAxis(DRIVE_LEFT_AXIS);
	}
	
	public double getDriveWheel() {
		return -driverPad.getRawAxis(DRIVE_WHEEL_AXIS);
	}
	
}

