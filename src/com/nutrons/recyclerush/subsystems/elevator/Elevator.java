package com.nutrons.recyclerush.subsystems.elevator;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.lib.TrajectoryFollower;
import com.nutrons.lib.TrajectoryFollowingPositionController;
import com.nutrons.recyclerush.RobotMap;
import com.nutrons.recyclerush.commands.elevator.ManualControlElevatorCmd;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Elevator extends Subsystem {
	
	public double kP_elevator = 1;
	public double kI_elevator = 0;
	public double kD_elevator = 0;
	public double kV_elevator = 1;
	public double kA_elevator = 0.5;
	public double epsilon = 1;
	
	DebouncedBoolean maxHeightPos = new DebouncedBoolean(5);
	DebouncedBoolean minHeightPos = new DebouncedBoolean(5);
	
	SpeedController elevatorMotor1 = new Talon(RobotMap.ELEVATOR_MOTOR);
	
	DigitalInput isMaxHeight = new DigitalInput(RobotMap.ELEVATOR_MAX_BUTTON);
	DigitalInput isMinHeight = new DigitalInput(RobotMap.ELEVATOR_MIN_BUTTON);
	
	public TrajectoryFollower follower = new TrajectoryFollower();
	public TrajectoryFollowingPositionController elevatorController = 
			new TrajectoryFollowingPositionController(kP_elevator, kI_elevator, kD_elevator,
			kV_elevator, kA_elevator, epsilon, follower.getConfig());
	
	public Elevator() {
		elevatorController.setGoal(follower.getCurrentSetpoint(), 1);
	}
	
    public void initDefaultCommand() {
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
    	elevatorMotor1.set(motorVal);
    }
    
    public void stop() {
    	elevatorMotor1.set(0);
    }
}

