package com.nutrons.recyclerush.commands.intake.sequence;

import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;
import com.nutrons.recyclerush.commands.intake.SpinIntakeWheelsCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author John Zhang
 */
public class IntakeContainerSeq extends CommandGroup {
    
    public  IntakeContainerSeq() {
    	addSequential(new SpinIntakeWheelsCmd());
    	addParallel(new IntakeOpenCmd());
    }
}
