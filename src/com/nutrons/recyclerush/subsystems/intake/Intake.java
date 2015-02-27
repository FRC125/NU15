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
	
	public boolean readyToStack = false;
	
	DebouncedBoolean isStackable = new DebouncedBoolean(5);
	
	Talon floorIntakeMotor = new Talon(RobotMap.INTAKE_MOTOR);
	Talon wintakeMotor = new Talon(RobotMap.WINTAKE_MOTOR);

	DoubleSolenoid IntakeWheelPiston = new DoubleSolenoid(RobotMap.LEFT_INTAKE_WHEEL_PISTON, RobotMap.RIGHT_INTAKE_WHEEL_PISTON);

	DoubleSolenoid canHolderPiston = new DoubleSolenoid(RobotMap.DOUBLE_STACK_HOLDER_A, RobotMap.DOUBLE_STACK_HOLDER_B);
	DoubleSolenoid canPusherPiston = new DoubleSolenoid(RobotMap.DOUBLE_PUSHER_A, RobotMap.DOUBLE_PUSHER_B);
	
	DigitalInput isStackableButton = new DigitalInput(RobotMap.STACKABLE_BUTTON);
	
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
    
    public double getUltrasonicDistance() {
    	return ultrasonicReadings.getAverage(ultra.getDistance());
    }
    public boolean canceled = false;
    public boolean getStopButton() {
    	return canceled;
    }
}
