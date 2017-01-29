package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.intake.DeployCanGrabberCmd;
import com.nutrons.recyclerush.commands.intake.RetractCanGrabberCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoCanGrabAndDriveFromBump extends CommandGroup {
    
    public  AutoCanGrabAndDriveFromBump() {
    	addSequential(new DeployCanGrabberCmd());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoDriveDistanceCmd(180, 0.8));
    	addSequential(new RetractCanGrabberCmd());
    }
}
