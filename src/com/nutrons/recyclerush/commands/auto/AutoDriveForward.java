package com.nutrons.recyclerush.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveForward extends CommandGroup {
    public  AutoDriveForward() {
        addSequential(new AutoDriveDistanceCmd(150));
    }
}
