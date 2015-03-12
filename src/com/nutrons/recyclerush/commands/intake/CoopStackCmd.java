package com.nutrons.recyclerush.commands.intake;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author John Zhang
 */
public class CoopStackCmd extends Command {
    
    public  CoopStackCmd() {
    	requires(Robot.intake);
    	requires(Robot.elevator);
    }

	protected void initialize() {
		Robot.intake.closeIntakeWheel();
		Robot.intake.setIntakeMotorPower(-1.0);
		Robot.elevator.setElevatorPower(-1.0);
	}

	protected void execute() {
		
	}

	protected boolean isFinished() {
		return Robot.elevator.isAtMinHeight();
	}

	protected void end() {
		Robot.intake.stopIntakeMotor();
		Robot.intake.openIntakeWheel();
	}

	protected void interrupted() {
		end();
	}
}
