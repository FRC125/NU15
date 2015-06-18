package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.intake.DeployCanGrabberCmd;
import com.nutrons.recyclerush.commands.intake.RetractCanGrabberCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class AutoGrabCanAndTroll extends CommandGroup {
    
    public  AutoGrabCanAndTroll() {
    	addSequential(new DeployCanGrabberCmd());
    	addSequential(new WaitCommand(13));
    	addSequential(new AutoDriveDistanceCmd(160, 1.0));
    	addSequential(new RetractCanGrabberCmd());
    }
}
