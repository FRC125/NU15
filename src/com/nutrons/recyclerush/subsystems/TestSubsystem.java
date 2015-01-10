package com.nutrons.recyclerush.subsystems;

import com.nutrons.recyclerush.RobotMap;
import com.nutrons.recyclerush.commands.TestCommand;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TestSubsystem extends Subsystem {
    
	private Victor motor = new Victor(RobotMap.MOTOR);
	
    public void initDefaultCommand() {
        setDefaultCommand(new TestCommand());
    }
    
    public void setSpeed(double speed) {
    	motor.set(speed);
    }
    
}

