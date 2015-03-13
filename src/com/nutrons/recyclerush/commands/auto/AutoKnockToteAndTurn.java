package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.intake.sequence.SpitIntakeSeq;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoKnockToteAndTurn extends CommandGroup {
    
    public  AutoKnockToteAndTurn() {
        addSequential(new AutoKnockTote());
        addSequential(new AutoTurnAngleCmd(90));
        addSequential(new AutoDriveDistanceCmd(150,0.5));
    }
}
