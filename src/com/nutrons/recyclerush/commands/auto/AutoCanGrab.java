package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.intake.DeployCanGrabberCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class AutoCanGrab extends CommandGroup {
    
    public  AutoCanGrab() {
    	addSequential(new DeployCanGrabberCmd());
    	addSequential(new WaitCommand(1.25));
    }
}
