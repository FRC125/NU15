package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class AutoDriveDistanceCmd extends Command {
	
	final double kp = 2.0;
	double epsilon = 1.0;
	double distance = 0;
	double targetLeftDistance;
	double targetRightDistance;
	double initLeftDistance;
	double initRightDistance;
	
    public AutoDriveDistanceCmd(double distance) {
    	this.distance = distance;
    	initLeftDistance = Robot.dt.getLeftDistance();
    	initRightDistance = Robot.dt.getRightDistance();
    	targetLeftDistance = initLeftDistance + distance;
    	targetRightDistance = initRightDistance + distance;
    	Robot.dt.resetEncoders();
    	requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftPower = (targetLeftDistance  - (Robot.dt.getLeftDistance() / distance  * kp));
    	double rightPower = (targetRightDistance - (Robot.dt.getRightDistance() / distance * kp));
    	Robot.dt.driveLCR(new double[] {leftPower, 0, rightPower});
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(targetRightDistance - Robot.dt.getRightDistance()) < epsilon && Math.abs(targetLeftDistance - Robot.dt.getLeftDistance()) < epsilon;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
