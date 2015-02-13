package com.nutrons.recyclerush.commands.intake.sequence;

import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.ReverseIntakeWheelsCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SpitIntakeSeq extends CommandGroup {
    
    public  SpitIntakeSeq() {
    	addSequential(new ReverseIntakeWheelsCmd());
    	addParallel(new IntakeCloseCmd());
    }
}
