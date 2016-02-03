package com.nutrons.recyclerush.commands.intake.sequence;

import com.nutrons.recyclerush.Robot;
import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseSlowerCmd;
import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.StopWintakeWheelsCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StopHumanPlayerFeedSeq extends CommandGroup {
    
    public  StopHumanPlayerFeedSeq() {
        addSequential(new IntakeCloseCmd());
        addSequential(new StopIntakeWheelsCmd());
        addSequential(new StopWintakeWheelsCmd());
        addSequential(new IntakeOpenCmd());
    }
}
