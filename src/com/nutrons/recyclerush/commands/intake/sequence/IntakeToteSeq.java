package com.nutrons.recyclerush.commands.intake.sequence;

import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.SpinIntakeWheelsCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeToteSeq extends CommandGroup {
    
    public  IntakeToteSeq() {
        addSequential(new SpinIntakeWheelsCmd());
        addParallel(new IntakeCloseCmd());
    }
}
