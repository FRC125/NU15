package com.nutrons.recyclerush.commands.intake.sequence;

import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;
import com.nutrons.recyclerush.commands.intake.SpinIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.SpinWintakeWheelsCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author John Zhang
 */
public class HumanPlayerFeedSeq extends CommandGroup {
    
    public  HumanPlayerFeedSeq() {
    	addParallel(new SpinIntakeWheelsCmd());
        addParallel(new IntakeOpenCmd());
        addSequential(new SpinWintakeWheelsCmd());
        
    }
}
