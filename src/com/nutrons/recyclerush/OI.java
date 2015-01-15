package com.nutrons.recyclerush;

import com.nutrons.lib.Utils;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	private final Joystick driverPad = new Joystick(RobotMap.DRIVE_PAD);
	private final int DRIVE_THROTTLE_AXIS = 1;
	private final int DRIVE_WHEEL_AXIS = 4;
	
	public double getDriveThrottle() {
		return Utils.deadband(driverPad.getRawAxis(DRIVE_THROTTLE_AXIS), 0.1, 0);
	}
	
	public double getDriveWheel() {
		return Utils.deadband(-driverPad.getRawAxis(DRIVE_WHEEL_AXIS), 0.1, 0);
	}
	
	
	public int getPOVDirection() {
		return driverPad.getPOV();
	}
}

