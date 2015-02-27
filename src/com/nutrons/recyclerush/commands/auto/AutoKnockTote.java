package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseCmd;
import com.nutrons.recyclerush.commands.elevator.LowerElevatorIfStackableCmd;
import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;
import com.nutrons.recyclerush.commands.intake.SpinIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.WaitForStackableCmd;
import com.nutrons.recyclerush.commands.intake.WaitForToteCmd;
import com.nutrons.recyclerush.commands.intake.sequence.SpitIntakeSeq;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoKnockTote extends CommandGroup {
    
    public  AutoKnockTote() {
    	addSequential(new IntakeOpenCmd());
    	addSequential(new WaitForToteCmd());
    	addSequential(new IntakeCloseCmd());
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new WaitForStackableCmd());
    	addSequential(new LowerElevatorIfStackableCmd());
    	addSequential(new ElevatorRaiseCmd());
    	addSequential(new AutoDriveDistanceCmd(20));
    }
}
