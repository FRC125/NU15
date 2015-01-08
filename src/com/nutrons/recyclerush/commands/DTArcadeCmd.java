package com.nutrons.recyclerush.commands;

import com.nutrons.recyclerush.Robot;
import com.nutrons.recyclerush.subsystems.DriveTrain;


import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DTArcadeCmd extends Command {

    public DTArcadeCmd() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dt.DriveTW(Robot.oi.throttle.getRawAxis(0), Robot.oi.wheel.getRawAxis(0));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
