package com.nutrons.recyclerush.subsystems.intake;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.lib.MovingAverage;
import com.nutrons.recyclerush.Robot;
import com.nutrons.recyclerush.RobotMap;
import com.nutrons.lib.Ultrasonic;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Intake extends Subsystem {
	
	public double autoGrabTime = 1.25;
	
	public boolean readyToStack = false;
	
	DebouncedBoolean isStackable = new DebouncedBoolean(2);
	
	Talon floorIntakeMotor = new Talon(RobotMap.INTAKE_MOTOR);
	Talon wintakeMotor = new Talon(RobotMap.WINTAKE_MOTOR); 
	Talon winchMotor = new Talon(RobotMap.WINCH_MOTOR);
	
	DoubleSolenoid IntakeWheelPiston = new DoubleSolenoid(RobotMap.LEFT_INTAKE_WHEEL_PISTON, RobotMap.RIGHT_INTAKE_WHEEL_PISTON);

	DoubleSolenoid canHolderPiston = new DoubleSolenoid(RobotMap.DOUBLE_STACK_HOLDER_A, RobotMap.DOUBLE_STACK_HOLDER_B);
	DoubleSolenoid canPusherPiston = new DoubleSolenoid(RobotMap.DOUBLE_PUSHER_A, RobotMap.DOUBLE_PUSHER_B);
	DoubleSolenoid canGrabberPiston = new DoubleSolenoid(RobotMap.CAN_GRABBER_A, RobotMap.CAN_GRABBER_B);
	//DoubleSolenoid canGrabberHandlePiston = new DoubleSolenoid(RobotMap.CAN_GRABBER_HANDLE_A, RobotMap.CAN_GRABBER_HANDLE_B);
	
	Solenoid canStopper = new Solenoid(RobotMap.CAN_STOPPER_PISTON);
	Solenoid wintakeStopper = new Solenoid(RobotMap.WINTAKE_STOPPER_PISTON);
	
	DigitalInput isStackableButton = new DigitalInput(RobotMap.STACKABLE_BUTTON);
	DigitalInput isToteThere = new DigitalInput(RobotMap.TOTE_BANNER);
	
	Ultrasonic ultra = new Ultrasonic(RobotMap.ULTRASONIC_AN, RobotMap.ULTRASONIC_RX);
	
	MovingAverage ultrasonicReadings = new MovingAverage(5);
	
    public void initDefaultCommand() {
    }
    
    public void setIntakeMotorPower(double pow) {
    	floorIntakeMotor.set(pow);
    }
    
    public void setWintakeMotorPower(double pow) {
    	wintakeMotor.set(pow);
    }
    
    public void stopIntakeMotor() {
    	floorIntakeMotor.set(0);
    }
    
    public void stopWintakeMotor() {
    	wintakeMotor.set(0);
    }
    
    public void openIntakeWheel() {
    	IntakeWheelPiston.set(DoubleSolenoid.Value.kForward);;
    }

    public void closeIntakeWheel() {
    	IntakeWheelPiston.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean isStackable() {
    	isStackable.feed(!isStackableButton.get());
    	return isStackable.get();
    }
    
    public void deployHolderPistons() {
    	canHolderPiston.set(Value.kForward);
    }
    
    public void retractHolderPistons() {
    	canHolderPiston.set(Value.kReverse);
    }
    
    public void deployPusherPiston() {
    	canPusherPiston.set(Value.kForward);
    }
    
    public void retractPusherPiston() {
    	canPusherPiston.set(Value.kReverse);
    }
    
    public void deployCanStopperPiston() {
    	canStopper.set(true);
    }
    
    public void retractCanStopperPiston() {
    	canStopper.set(false);
    }
    
    public void deployWintakeStopperPiston() {
    	wintakeStopper.set(true);
    }
    
    public void retractWintakeStopperPiston() {
    	wintakeStopper.set(false);
    }
    
    public void deployCanGrabberPiston() {
    	canGrabberPiston.set(Value.kForward);
    }
    
    public void retractCanGrabberPiston() {
    	canGrabberPiston.set(Value.kReverse);
    }
    
    public void deployCanGrabberHandlePiston() {
    	//canGrabberHandlePiston.set(Value.kForward);
    }
    
    public void retractCanGrabberhandlePiston() {
    	//canGrabberHandlePiston.set(Value.kReverse);
    }
    public double getUltrasonicDistance() {
    	return ultrasonicReadings.getAverage(ultra.getDistance());
    }
    public boolean canceled = false;
    public boolean getStopButton() {
    	return canceled;
    }
    
    public boolean isToteThere() {
    	return isToteThere.get();
    }
    
    public void setWinchMotorPower(double pow) {
    	winchMotor.set(pow);
    }
    
    public void stopWinchMotor() {
    	winchMotor.set(0);
    }
}
