package com.nutrons.recyclerush;

import com.nutrons.lib.Utils;
import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;
import com.nutrons.recyclerush.commands.intake.SpinWintakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.StopWintakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.sequence.HumanPlayerFeedSeq;
import com.nutrons.recyclerush.commands.intake.sequence.IntakeContainerSeq;
import com.nutrons.recyclerush.commands.intake.sequence.IntakeToteSeq;
import com.nutrons.recyclerush.commands.intake.sequence.SpitIntakeSeq;
import com.nutrons.recyclerush.commands.intake.sequence.StopIntakeContainerSeq;
import com.nutrons.recyclerush.commands.intake.sequence.StopIntakeToteSeq;
import com.nutrons.recyclerush.commands.intake.PushStackCmd;
import com.nutrons.recyclerush.commands.intake.RetractStackPusherCmd;



import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * 
 * @author John Zhang, Camilo Gonzalez
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
	private Button raiseElevatorButton = new JoystickButton(operatorPad, 8);
	private Button lowerElevatorButton = new JoystickButton(operatorPad, 6);
	private Button intakeToteButton = new JoystickButton(operatorPad, 7);
	private Button spitIntakeButton = new JoystickButton(operatorPad, 5);
	private Button cancelWintake = new JoystickButton(operatorPad, 10);
	
	public OI() {
		intakeContainerButton.whenActive(new IntakeContainerSeq());
		intakeContainerButton.whenReleased(new StopIntakeContainerSeq());
		
		intakeOpenButton.whenPressed(new IntakeOpenCmd());
		intakeOpenButton.whenReleased(new IntakeCloseCmd());
		
		raiseElevatorButton.whenPressed(new ElevatorRaiseCmd());
		lowerElevatorButton.whenPressed(new ElevatorLowerCmd());
		
		intakeToteButton.whenActive(new IntakeToteSeq());
		intakeToteButton.whenReleased(new StopIntakeToteSeq());
		
		spitIntakeButton.whenActive(new SpitIntakeSeq());
		spitIntakeButton.whenReleased(new StopIntakeToteSeq());
		
		humanPlayerIntakeButton.whenActive(new HumanPlayerFeedSeq());
		
		cancelWintake.whenActive(new StopIntakeToteSeq());
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
		return -Utils.deadband(-driverPad.getRawAxis(0), 0.1, 0);
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
	
	public boolean getCancelWintake() {
		return cancelWintake.get();
	}
	
}

