package com.nutrons.recyclerush.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoDriveTurn extends CommandGroup {
    
    public  AutoDriveTurn() {
        addSequential(new AutoDriveDistanceCmd(30));
        addSequential(new WaitCommand(1));
        addSequential(new AutoTurnAngleCmd(90));
        addSequential(new WaitCommand(1));
        addSequential(new AutoDriveDistanceCmd(230, 0.5));
    }
}
