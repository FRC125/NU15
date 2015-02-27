package com.nutrons.recyclerush.commands.elevator;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorTimeCmd extends Command {

	Timer timer = new Timer();
	
	double time = 0;
	double power = 1.0;
	
    public ElevatorTimeCmd(double time, int direction) {
        requires(Robot.elevator);
        this.time = time;
        this.power = power * direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.setElevatorPower(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return timer.get() > time || Robot.elevator.isAtMaxHeight() || Robot.elevator.isAtMinHeight();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.setElevatorPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
