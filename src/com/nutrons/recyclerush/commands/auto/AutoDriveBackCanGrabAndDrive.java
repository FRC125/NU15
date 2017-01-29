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
public class AutoDriveBackCanGrabAndDrive extends CommandGroup {
    
    public  AutoDriveBackCanGrabAndDrive() {
    	addSequential(new AutoDriveDistanceCmd(-6, 0.5));
    	addSequential(new WaitCommand(0.2));
    	addSequential(new DeployCanGrabberCmd());
    	addSequential(new WaitCommand(1.25));
    	addSequential(new AutoDriveDistanceCmd(140, 1.0));
    	addSequential(new RetractCanGrabberCmd());
    }
}
