package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveUntilToteAndElevatorMoveCmd extends Command {
	
	double power = 0.75;
	int toteCount = 0;
	
	Timer timer = new Timer();
	
    public AutoDriveUntilToteAndElevatorMoveCmd() {
    	requires(Robot.dt);
    	requires(Robot.intake);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	Robot.dt.headingHoldPID.enable();
    	Robot.dt.headingHoldPID.setSetpoint(Robot.dt.getGyroAngle());
    	Robot.elevator.setElevatorPower(-1.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dt.driveLCR(new double[] {power, 0, -power});
    	if(Robot.elevator.isAtMinHeight()) {
    		Robot.elevator.setElevatorPower(1.0);
    	}else if(Robot.elevator.isAtMaxHeight() && timer.get() > 0.5) {
    		Robot.elevator.stop();
    	}
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
    	Robot.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
