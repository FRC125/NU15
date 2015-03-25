package com.nutrons.recyclerush.commands.intake;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeUntilStackableCmd extends Command {
	
    public IntakeUntilStackableCmd() {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.setIntakeMotorPower(1.0);
    	Robot.intake.closeIntakeWheel();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.intake.isStackable();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.stopIntakeMotor();
    	Robot.intake.openIntakeWheel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
