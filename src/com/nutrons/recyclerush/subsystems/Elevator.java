package com.nutrons.recyclerush.subsystems;

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
	
    public void initDefaultCommand() {
    	
    }
    
    
    
}

