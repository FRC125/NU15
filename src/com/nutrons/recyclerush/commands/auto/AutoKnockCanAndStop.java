package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.PushCanCmd;
import com.nutrons.recyclerush.commands.intake.RetractCanPusherCmd;
import com.nutrons.recyclerush.commands.intake.SpinIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoKnockCanAndStop extends CommandGroup {
    
    public  AutoKnockCanAndStop() {
    	addSequential(new ElevatorLowerCmd());
        addSequential(new PushCanCmd());
        addSequential(new WaitCommand(0.5));
        addSequential(new AutoDriveDistanceAndIntakeCmd(50, 10, 50, 0.5));
        addSequential(new SpinIntakeWheelsCmd());
        addSequential(new IntakeCloseCmd());
        addSequential(new WaitCommand(1));
        addSequential(new StopIntakeWheelsCmd());
        addSequential(new RetractCanPusherCmd());
    }
}
