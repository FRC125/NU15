package com.nutrons.recyclerush.commands.auto;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author John Zhang
 *
 */
public class AutoTurnAngleCmd extends Command {
	
	Timer timer = new Timer();
	
	double epsilon = 4;
	double targetAngle = Robot.dt.getGyroAngle();
	double angle = 0;
	double time = 5;
	DebouncedBoolean onTarget = new DebouncedBoolean(50);
	
    public AutoTurnAngleCmd(double angle) {
    	this.targetAngle = angle;
    	requires(Robot.dt);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dt.quickTurnPID.disable();
    	Robot.dt.zeroGyro();
    	timer.start();
    	Robot.dt.quickTurnPID.setAbsoluteTolerance(0.01);
    	Robot.dt.quickTurn(targetAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putBoolean("On Target", onTarget.get());
    	onTarget.feed(Robot.dt.quickTurnPID.onTarget());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.dt.quickTurnPID.setAbsoluteTolerance(0.01);
    	return onTarget.get();
    	//return Math.abs(Robot.dt.getGyroAngle() - targetAngle) < epsilon || timer.get() > time;
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
