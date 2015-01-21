package com.nutrons.recyclerush;

import com.nutrons.lib.MovingAverage;
import com.nutrons.lib.Utils;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;


/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class OI {
    
	private final Joystick driverPad = new Joystick(RobotMap.DRIVE_PAD);
	private Button headingButton = new JoystickButton(driverPad, 5);
	private Button fieldCentricButton = new JoystickButton(driverPad, 6);
	private Button resetGyroButton = new JoystickButton(driverPad, 7);
	
	public int getPOVDirection() {
		return driverPad.getPOV();
	}
	
	public double getJoystickX() {
		return Utils.deadband(driverPad.getRawAxis(0), 0.1, 0);
	}
	
	public double getJoystickY() {
		return Utils.deadband(driverPad.getRawAxis(1), 0.1, 0);
	}
	
	public double getJoystickSpin() {
		return Utils.deadband(driverPad.getRawAxis(4), 0.05, 0);
	}
	
	public boolean isHoldHeading() {
		return headingButton.get();
	}
	
	public boolean isFieldCentric() {
		return fieldCentricButton.get();
	}
	
	public boolean isResetGyroButton() {
		return resetGyroButton.get();
	}
	
}

