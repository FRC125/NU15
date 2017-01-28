package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseSlowerCmd;
import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;
import com.nutrons.recyclerush.commands.intake.SpinIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.WaitForStackableCmd;
import com.nutrons.recyclerush.commands.intake.WaitForToteCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGrabTote extends CommandGroup {
    
    public  AutoGrabTote() {
    	addSequential(new IntakeOpenCmd());
    	addSequential(new WaitForToteCmd());
    	addSequential(new IntakeCloseCmd());
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new WaitForStackableCmd());
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new ElevatorRaiseSlowerCmd());
    	addSequential(new IntakeOpenCmd());
    	addSequential(new WaitForToteCmd());
    	addSequential(new IntakeCloseCmd());
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new WaitForStackableCmd());
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new ElevatorRaiseSlowerCmd());
    	addSequential(new IntakeOpenCmd());
    	addSequential(new WaitForToteCmd());
    	addSequential(new IntakeCloseCmd());
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new WaitForStackableCmd());
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new ElevatorRaiseSlowerCmd());
    }
}
