package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.intake.DeployCanGrabberCmd;
import com.nutrons.recyclerush.commands.intake.DeployCanGrabberHandleCmd;
import com.nutrons.recyclerush.commands.intake.RetractCanGrabberCmd;
import com.nutrons.recyclerush.commands.intake.RetractCanGrabberHandleCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class AutoCanGrabAndDrive extends CommandGroup {
    
    public  AutoCanGrabAndDrive() {
    	addSequential(new AutoDriveDistanceCmd(-6, 0.5));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new DeployCanGrabberCmd());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoDriveDistanceCmd(140, 1.0));
    	addSequential(new RetractCanGrabberCmd());
    }
}
