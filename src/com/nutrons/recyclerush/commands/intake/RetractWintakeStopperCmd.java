package com.nutrons.recyclerush.commands.intake;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class RetractWintakeStopperCmd extends Command {

    public RetractWintakeStopperCmd() {
    	requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.retractWintakeStopperPiston();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
