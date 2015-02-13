package com.nutrons.recyclerush;

import com.nutrons.lib.MovingAverage;
import com.nutrons.lib.Utils;
import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;
import com.nutrons.recyclerush.commands.intake.sequence.HumanPlayerFeedSeq;
import com.nutrons.recyclerush.commands.intake.sequence.IntakeContainerSeq;
import com.nutrons.recyclerush.commands.intake.sequence.StopHumanPlayerFeedSeq;
import com.nutrons.recyclerush.commands.intake.sequence.StopIntakeContainerSeq;

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
	private final Joystick operatorPad = new Joystick(RobotMap.OPERATOR_PAD);
	private Button headingButton = new JoystickButton(driverPad, 1);
	private Button fieldCentricButton = new JoystickButton(driverPad, 2);
	private Button resetGyroButton = new JoystickButton(driverPad, 7);
	
	// command buttons
	private Button intakeContainerButton = new JoystickButton(operatorPad, 1);
	private Button humanPlayerIntakeButton = new JoystickButton(operatorPad, 2);
	private Button stackToteButton = new JoystickButton(operatorPad, 3);
	private Button intakeOpenButton = new JoystickButton(operatorPad, 4);
	
	public OI() {
		intakeContainerButton.whenActive(new IntakeContainerSeq());
		intakeContainerButton.whenInactive(new StopIntakeContainerSeq());
		humanPlayerIntakeButton.whenPressed(new HumanPlayerFeedSeq());
		stackToteButton.whenPressed(new StopHumanPlayerFeedSeq());
		intakeOpenButton.whenPressed(new IntakeOpenCmd());
		intakeOpenButton.whenReleased(new IntakeCloseCmd());
	}
	
	/**
	 * Gets dPad button
	 * @return direction of dpad
	 */
	public int getPOVDirection() {
		return driverPad.getPOV();
	}

	/**
	 * Gets value on the x axis joystick (throttle)
	 * @return x axis value
	 */
	public double getJoystickX() {
		return Utils.deadband(-driverPad.getRawAxis(0), 0.1, 0);
	}
	
	/**
	 * Gets value on the y axis joystick (side to side)
	 * @return y axis value
	 */
	public double getJoystickY() {
		return Utils.deadband(-driverPad.getRawAxis(1), 0.1, 0);
	}
	
	/**
	 * gets value of x axis joystick (rotate)
	 * @return x axis value
	 */
	public double getJoystickSpin() {
		return Utils.deadband(-driverPad.getRawAxis(4), 0.05, 0);
	}
	
	/**
	 * Gets state on button for heading
	 * @return is heading pressed
	 */
	public boolean isHoldHeading() {
		return headingButton.get();
	}
	
	/**
	 * Gets state of fieldcentric button 
	 * @return is filedCentric pressed
	 */
	public boolean isFieldCentric() {
		return fieldCentricButton.get();
	}
	
	/**
	 * Gets state of button for zeroing the gyro
	 * @return is resetGyro pressed
	 */
	public boolean isResetGyroButton() {
		return resetGyroButton.get();
	}
	
	public double getOperatorJoystickY() {
		return -Utils.deadband(operatorPad.getRawAxis(1), 0.1, 0);
	}
	
}

