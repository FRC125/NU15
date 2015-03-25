package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveUntilToteCmd extends Command {
	
	double power = 0.5;
	int toteCount = 0;
	double delay = 0;
	double time = -1;
	
	Timer timer = new Timer();
	
    public AutoDriveUntilToteCmd() {
    	requires(Robot.dt);
    	requires(Robot.intake);
    }
    
    public AutoDriveUntilToteCmd(double delay) {
    	requires(Robot.dt);
    	requires(Robot.intake);
    	this.delay = delay;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	Robot.dt.headingHoldPID.enable();
    	Robot.dt.headingHoldPID.setSetpoint(Robot.dt.getGyroAngle());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dt.driveLCR(new double[] {power, 0, -power});
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.intake.getUltrasonicDistance() < 10.0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.headingHoldPID.reset();
    	Robot.dt.headingHoldPID.disable();
    	Robot.dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
