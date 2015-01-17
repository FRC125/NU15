package com.nutrons.recyclerush.subsystems;

import lib.DebouncedBoolean;

import com.nutrons.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
    
	private Talon elevatorMotor1 = new Talon(RobotMap.ELEVATOR_1);
	private Talon elevatorMotor2 = new Talon(RobotMap.ELEVATOR_2);
    private DigitalInput isDown = new DigitalInput(RobotMap.ELEVATOR_DOWN);
    private DigitalInput isUp = new DigitalInput(RobotMap.ELEVATOR_UP);
	
    private DebouncedBoolean elevatorDown = new DebouncedBoolean(5);
    private DebouncedBoolean elevatorUp = new DebouncedBoolean(5);
    
    private static double driveUpPower = 0.5;
    private static double driveDownPower = -0.5;
    
    public void initDefaultCommand() {
    	
    }
    
    public boolean isElevatorDown() {
    	elevatorDown.feed(isDown.get());
    	return elevatorDown.get();
    }
    
    public boolean isElevatorUp() {
    	elevatorUp.feed(isUp.get());
    	return elevatorUp.get();
    }
    
    public void driveElevatorUp() {
    	elevatorMotor1.set(driveUpPower);
    	elevatorMotor2.set(driveUpPower);
    }
    
    public void driveElevatorDown() {
    	elevatorMotor1.set(driveDownPower);
    	elevatorMotor2.set(driveDownPower);
    }
    
    public void stopElevator() {
    	elevatorMotor1.set(0);
    	elevatorMotor2.set(0);
    }
    
}

