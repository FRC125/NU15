package com.nutrons.recyclerush.commands.drivetrain;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class DriveHPIDCmd extends Command {
	
    public DriveHPIDCmd() {
    	requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double scale = Robot.oi.slowedDown() ? 0.4 : 1.0;
    	double deadband = Robot.oi.slowedDown() ? 0.01 : 0.1;
    	Robot.dt.drivePID(-scale*Robot.oi.getJoystickX(deadband), scale*Robot.oi.getJoystickY(deadband), Robot.oi.getJoystickSpin(deadband));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
