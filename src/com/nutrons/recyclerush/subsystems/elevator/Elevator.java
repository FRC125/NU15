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
	DebouncedBoolean minHeightPos = new DebouncedBoolean(5);
	
	Talon elevatorMotor1 = new Talon(RobotMap.ELEVATOR_MOTOR);
	
	DigitalInput isMaxHeight = new DigitalInput(RobotMap.ELEVATOR_MAX_BUTTON);
	DigitalInput isMinHeight = new DigitalInput(RobotMap.ELEVATOR_MIN_BUTTON);
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualControlElevatorCmd());
    }
    
    public boolean isAtMaxHeight() {
    	maxHeightPos.feed(isMaxHeight.get());
    	return !maxHeightPos.get();
    }
    
    public boolean isAtMinHeight() {
    	minHeightPos.feed(isMinHeight.get());
    	return !minHeightPos.get();
    }
    
    public void setElevatorPower(double pow) {
    	double motorVal = pow;
    	
    	if(isAtMinHeight()) {
    		if(pow > 0) {
    			motorVal = pow;
    		}else {
    			motorVal = 0;
    		}
    	}
    	
    	if(isAtMaxHeight()) {
    		if(pow < 0) {
    			motorVal = pow;
    		}else {
    			motorVal = 0;
    		}
    	}
    	
    	/**
    	if(isAtMaxHeight() || isAtMinHeight()) {
    		
    		pow = 0;
    	}
    	**/
    	elevatorMotor1.set(motorVal);
    }
    
    public void stop() {
    	elevatorMotor1.set(0);
    }
}

