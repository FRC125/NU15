package com.nutrons.recyclerush.commands.elevator;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class ElevatorRaiseRCCmd extends Command {

	private double power = 0.50;
	
    public ElevatorRaiseRCCmd() {
    	requires(Robot.elevator);
    	requires(Robot.intake);
    }

    protected void initialize() {
    	Robot.intake.openIntakeWheel();
    }

    protected void execute() {
    	Robot.elevator.setElevatorPower(power);
    }

    protected boolean isFinished() {
        return Robot.elevator.isAtMaxHeight();
    }

    protected void end() {
    	Robot.elevator.stop();
    }

    protected void interrupted() {
    }
}
