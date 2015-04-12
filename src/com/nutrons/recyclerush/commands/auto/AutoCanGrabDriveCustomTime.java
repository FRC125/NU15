package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.Robot;
import com.nutrons.recyclerush.commands.intake.DeployCanGrabberCmd;
import com.nutrons.recyclerush.commands.intake.RetractCanGrabberCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoCanGrabDriveCustomTime extends CommandGroup {
    
	
    public  AutoCanGrabDriveCustomTime(double time) {
    	addSequential(new DeployCanGrabberCmd());
    	addSequential(new WaitCommand(time));
    	addSequential(new AutoDriveDistanceCmd(140, 1.0));
    	addSequential(new RetractCanGrabberCmd());
    }
}
