package com.nutrons.recyclerush.commands;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurnCmd extends Command {

	double epsilon = .5;
	double targetAngle = Robot.dt.getGyroAngle();
	
    public DriveTurnCmd() {
        requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dt.zeroGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getPOVDirection() != 0) {
    		targetAngle = Robot.oi.getPOVDirection() * 45;
    	}
    	Robot.dt.quickTurn(targetAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.dt.getGyroAngle() - targetAngle) < epsilon;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
