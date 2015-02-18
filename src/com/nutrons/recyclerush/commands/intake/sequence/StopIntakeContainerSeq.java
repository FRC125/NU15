package com.nutrons.recyclerush.commands.intake.sequence;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author John Zhang
 */
public class StopIntakeContainerSeq extends CommandGroup {
    
    public  StopIntakeContainerSeq() {
        addSequential(new StopIntakeWheelsCmd());
        addParallel(new IntakeCloseCmd());
    }
}
