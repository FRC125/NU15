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
public class AutoCanGrabAndDrive extends CommandGroup {
    
    public  AutoCanGrabAndDrive() {
    	addSequential(new DeployCanGrabberCmd());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoDriveDistanceCmd(120, 1.0));
    	addSequential(new RetractCanGrabberCmd());
    }
}
