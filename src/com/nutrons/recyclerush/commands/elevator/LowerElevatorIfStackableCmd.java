package com.nutrons.recyclerush.commands.elevator;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerElevatorIfStackableCmd extends Command {
	
	private boolean isStackable = false;
	private double speed = -1;
    public LowerElevatorIfStackableCmd(double speed) {
    	requires(Robot.elevator);
    	requires(Robot.intake);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.intake.retractHolderPistons();
    	Robot.intake.closeIntakeWheel();
    	isStackable = Robot.intake.isStackable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isStackable) {
    		Robot.elevator.setElevatorPower(this.speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevator.isAtMinHeight() || !isStackable;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevator.stop();
    }
}
