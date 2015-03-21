package com.nutrons.recyclerush.commands.elevator;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author John Zhang
 */
public class ElevatorRaiseCmd extends Command {

	Timer timer = new Timer();
	
    public ElevatorRaiseCmd() {
        requires(Robot.elevator);
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.retractHolderPistons();
    	Robot.intake.retractCanStopperPiston();
    	Robot.intake.openIntakeWheel();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.setElevatorPower(1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevator.isAtMaxHeight() || timer.get() > 4;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
