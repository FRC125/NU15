package com.nutrons.recyclerush.commands.intake.sequence;

import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class IntakeTapToteSeq extends CommandGroup {
    
    public  IntakeTapToteSeq() {
        addSequential(new IntakeCloseCmd());
        addSequential(new WaitCommand(0.3));
        addSequential(new IntakeOpenCmd());
        addSequential(new WaitCommand(0.2));
        addSequential(new IntakeCloseCmd());
    }
}
