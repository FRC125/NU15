package com.nutrons.recyclerush.commands.auto;

import com.nutrons.lib.MovingAverage;
import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class AutoTurnAngleCmd extends Command {
	
	double epsilon = 10.0;
	double targetAngle = Robot.dt.getGyroAngle();
	double angle = 0;
	
	MovingAverage error = new MovingAverage(10);
	
	
    public AutoTurnAngleCmd(double angle) {
    	this.angle = angle;
    	requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	targetAngle = angle;
    	Robot.dt.quickTurn(targetAngle);
    	error.getAverage(Math.abs(targetAngle - Robot.dt.getGyroAngle()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return error.getAverage(Math.abs(targetAngle - Robot.dt.getGyroAngle())) < epsilon;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.stop();
    	targetAngle = Robot.dt.getGyroAngle();
    	Robot.dt.quickTurnPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
