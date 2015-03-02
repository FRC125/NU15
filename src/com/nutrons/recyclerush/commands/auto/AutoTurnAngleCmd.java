package com.nutrons.recyclerush.commands.auto;

import com.nutrons.lib.MovingAverage;
import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class AutoTurnAngleCmd extends Command {
	
	Timer timer = new Timer();
	
	double epsilon = 4;
	double targetAngle = Robot.dt.getGyroAngle();
	double angle = 0;
	double time = 5;
	
	MovingAverage error = new MovingAverage(5);
	
    public AutoTurnAngleCmd(double angle) {
    	this.targetAngle = angle;
    	requires(Robot.dt);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dt.zeroGyro();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dt.quickTurn(targetAngle);
    	error.getAverage(Math.abs(targetAngle - Robot.dt.getGyroAngle()));
    	Robot.intake.setIntakeMotorPower(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return error.getAverage(Math.abs(targetAngle - Robot.dt.getGyroAngle())) < epsilon || timer.get() > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.stop();
    	targetAngle = Robot.dt.getGyroAngle();
    	Robot.dt.quickTurnPID.disable();
    	Robot.intake.stopIntakeMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
