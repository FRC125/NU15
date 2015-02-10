package com.nutrons.recyclerush.subsystems.elevator;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.recyclerush.RobotMap;
import com.nutrons.recyclerush.commands.elevator.ManualControlElevatorCmd;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Elevator extends Subsystem {
	
	DebouncedBoolean maxHeightPos = new DebouncedBoolean(5);
	
	Talon elevatorMotor1 = new Talon(RobotMap.ELEVATOR_1);
	Talon elevatorMotor2 = new Talon(RobotMap.ELEVAOTR_2);
	
	DigitalInput isMaxHeight = new DigitalInput(RobotMap.ELEVATOR_MAX_BUTTON);
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualControlElevatorCmd());
    }
    
    public boolean isAtMaxHeight() {
    	maxHeightPos.feed(isMaxHeight.get());
    	return maxHeightPos.get();
    }
    
    public void setElevatorPower(double pow) {
    	elevatorMotor1.set(pow);
    	elevatorMotor2.set(pow);
    }
    
    public void stop() {
    	elevatorMotor1.set(0);
    	elevatorMotor2.set(0);
    }
}

