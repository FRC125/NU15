package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class AutoDriveDistanceCmd extends Command {

	double epsilon = 1.0;
	double distance = 0;
	
    public AutoDriveDistanceCmd(double distance) {
    	this.distance = distance;
    	requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dt.resetEncoders();
    	Robot.dt.setEncoderConstant(1.0/distance);
    	Robot.dt.driveStraightForDistance(distance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Distance Error", Robot.dt.driveDistancePID.getError());
    	SmartDashboard.putNumber("Distance PID Output", Robot.dt.driveDistancePID.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(distance - Robot.dt.getEncoderMax()) < epsilon;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.driveDistancePID.disable();
    	Robot.dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}