package com.nutrons.recyclerush.commands.intake.sequence;

import com.nutrons.recyclerush.Robot;
import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StopHumanPlayerFeedSeq extends CommandGroup {
    
    public  StopHumanPlayerFeedSeq() {
        addSequential(new IntakeCloseCmd());
        addSequential(new StopIntakeWheelsCmd());
    }
}
