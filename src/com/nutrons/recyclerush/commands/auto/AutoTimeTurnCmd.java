package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTimeTurnCmd extends Command {

	Timer timer = new Timer();
	
	double time = 0;
	double power = 0.5;
	
    public AutoTimeTurnCmd(double time) {
    	requires(Robot.dt);
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dt.driveLR(power, power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > time;
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
