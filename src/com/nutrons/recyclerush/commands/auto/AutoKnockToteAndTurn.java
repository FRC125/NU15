package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.SpinIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.sequence.SpitIntakeSeq;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoKnockToteAndTurn extends CommandGroup {
    
    public  AutoKnockToteAndTurn() {
        addSequential(new AutoKnockTote());
        addSequential(new IntakeCloseCmd());
        addSequential(new SpinIntakeWheelsCmd());
        addSequential(new WaitCommand(0.5));
        addSequential(new AutoTurnAngleCmd(90));
        addSequential(new AutoDriveDistanceCmd(175,0.5));
    }
}
