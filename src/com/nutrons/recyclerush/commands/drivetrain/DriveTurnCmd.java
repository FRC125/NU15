package com.nutrons.recyclerush.commands.drivetrain;

import com.nutrons.lib.MovingAverage;
import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author John Zhang
 */
public class DriveTurnCmd extends Command {
	Timer timer = new Timer();
	double epsilon = 10.0;
	double targetAngle = Robot.dt.getGyroAngle();
	boolean spin = false;
	MovingAverage errors = new MovingAverage(10);
	double timeout = 10.0;
	boolean timedOut = false;
    public DriveTurnCmd() {
        requires(Robot.dt);
        Robot.dt.zeroGyro();
        timer.start();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getPOVDirection() != -1) {
    		targetAngle = Robot.oi.getPOVDirection();
    		spin = true;
    	}
    	if(spin) {
    		Robot.dt.quickTurn(targetAngle);
    	}
    	errors.getAverage(Math.abs(targetAngle - Robot.dt.getGyroAngle()));
    	timedOut = timer.get() > timeout;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return errors.getAverage(Math.abs(targetAngle - Robot.dt.getGyroAngle())) < epsilon;
    }

    // Called once after isFinished  returns true
    protected void end() {
    	Robot.dt.stop();
    	targetAngle = Robot.dt.getGyroAngle();
    	spin = false;
    	Robot.dt.quickTurnPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
