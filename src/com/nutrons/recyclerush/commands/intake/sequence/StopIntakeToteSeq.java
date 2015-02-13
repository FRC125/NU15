package com.nutrons.recyclerush.commands.intake.sequence;

import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StopIntakeToteSeq extends CommandGroup {
    
    public  StopIntakeToteSeq() {
    	addSequential(new StopIntakeWheelsCmd());
    	addParallel(new IntakeCloseCmd());
    }
}
