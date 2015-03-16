package com.nutrons.recyclerush;

import com.nutrons.lib.Utils;
import com.nutrons.recyclerush.commands.auto.AutoDriveDistanceCmd;
import com.nutrons.recyclerush.commands.auto.AutoDriveUntilToteCmd;
import com.nutrons.recyclerush.commands.auto.AutoTimeDriveCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseCmd;
import com.nutrons.recyclerush.commands.intake.CoopStackCmd;
import com.nutrons.recyclerush.commands.intake.HoldCanCmd;
import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;
import com.nutrons.recyclerush.commands.intake.RetractCanHolderCmd;
import com.nutrons.recyclerush.commands.intake.SpinWintakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.StopWintakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.sequence.HumanPlayerFeedSeq;
import com.nutrons.recyclerush.commands.intake.sequence.IntakeContainerSeq;
import com.nutrons.recyclerush.commands.intake.sequence.IntakeToteSeq;
import com.nutrons.recyclerush.commands.intake.sequence.SpitIntakeSeq;
import com.nutrons.recyclerush.commands.intake.sequence.StopHumanPlayerFeedSeq;
import com.nutrons.recyclerush.commands.intake.sequence.StopIntakeContainerSeq;
import com.nutrons.recyclerush.commands.intake.sequence.StopIntakeToteSeq;
import com.nutrons.recyclerush.commands.intake.PushCanCmd;
import com.nutrons.recyclerush.commands.intake.RetractCanPusherCmd;










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
	private Button headingButton = new JoystickButton(driverPad, 3);
	private Button fieldCentricButton = new JoystickButton(driverPad, 2);
	private Button resetGyroButton = new JoystickButton(driverPad, 9);
	private Button slowDownButton = new JoystickButton(driverPad, 5);
	private Button knockCan = new JoystickButton(driverPad, 7);
	private Button holdCan = new JoystickButton(driverPad, 6);
	private Button releaseCan = new JoystickButton(driverPad, 8);
	
	// command buttons
	private Button intakeContainerButton = new JoystickButton(operatorPad, 1);
	private Button humanPlayerIntakeButton = new JoystickButton(operatorPad, 2);
	private Button intakeCloseButton = new JoystickButton(operatorPad, 4);
	private Button raiseElevatorButton = new JoystickButton(operatorPad, 6);
	private Button lowerElevatorButton = new JoystickButton(operatorPad, 8);
	private Button intakeToteButton = new JoystickButton(operatorPad, 5);
	private Button spitIntakeButton = new JoystickButton(operatorPad, 7);
	private Button cancelWintake = new JoystickButton(operatorPad, 10);
	private Button coopButton = new JoystickButton(operatorPad, 9);
	
	public OI() {
		intakeContainerButton.whenActive(new IntakeContainerSeq());
		intakeContainerButton.whenReleased(new StopIntakeContainerSeq());
		
		intakeCloseButton.whenPressed(new IntakeCloseCmd());
		intakeCloseButton.whenReleased(new IntakeOpenCmd());
		
		raiseElevatorButton.whenPressed(new ElevatorRaiseCmd());
		lowerElevatorButton.whenPressed(new ElevatorLowerCmd());
		
		intakeToteButton.whenActive(new IntakeToteSeq());
		intakeToteButton.whenReleased(new StopIntakeToteSeq());
		
		spitIntakeButton.whenActive(new SpitIntakeSeq());
		spitIntakeButton.whenReleased(new StopIntakeToteSeq());
		
		humanPlayerIntakeButton.whenPressed(new HumanPlayerFeedSeq());
		
		cancelWintake.whenPressed(new StopHumanPlayerFeedSeq());
		
		knockCan.whenPressed(new PushCanCmd());
		knockCan.whenReleased(new RetractCanPusherCmd());
		
		holdCan.whenPressed(new HoldCanCmd());
		releaseCan.whenPressed(new RetractCanHolderCmd());
		
		coopButton.whenPressed(new CoopStackCmd());
	}
	
	/**
	 * Slow down button pressed?
	 * @return
	 */
	public boolean slowedDown()
	{
		return slowDownButton.get();
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
	public double getJoystickX(double deadband) {
		return -Utils.deadband(-driverPad.getRawAxis(0), deadband, 0);
	}
	
	/**
	 * Gets value on the y axis joystick (side to side)
	 * @return y axis value
	 */
	public double getJoystickY(double deadband) {
		return Utils.deadband(-driverPad.getRawAxis(1), deadband, 0);
	}
	
	/**
	 * gets value of x axis joystick (rotate)
	 * @return x axis value
	 */
	public double getJoystickSpin(double deadband) {
		return Utils.deadband(-driverPad.getRawAxis(2), deadband/2.0, 0);
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

