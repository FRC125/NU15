package com.nutrons.recyclerush.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoDoNothing extends CommandGroup {
    
    public  AutoDoNothing() {
    	addSequential(new WaitCommand(1));
        // nothing to see here
    }
}
