package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.intake.PushCanCmd;
import com.nutrons.recyclerush.commands.intake.RetractCanPusherCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoKnockCanAndTurn extends CommandGroup {
    
    public  AutoKnockCanAndTurn() {
    	addSequential(new ElevatorLowerCmd());
        addSequential(new PushCanCmd());
        addSequential(new WaitCommand(0.5));
        addSequential(new AutoDriveDistanceAndIntakeCmd(50, 10, 50, 0.5));
        addSequential(new WaitCommand(0.5));
        addSequential(new AutoTurnAngleCmd(90));
        addSequential(new AutoDriveDistanceCmd(175, 0.5));
        addSequential(new RetractCanPusherCmd());
    }
}
