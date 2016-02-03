package com.nutrons.recyclerush.commands.intake.sequence;

import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseSlowerCmd;
import com.nutrons.recyclerush.commands.elevator.LowerElevatorIfStackableCmd;
import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;
import com.nutrons.recyclerush.commands.intake.RetractWintakeStopperCmd;
import com.nutrons.recyclerush.commands.intake.SpinIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.SpinWintakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.StopWintakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.WaitForStackableCmd;
import com.nutrons.recyclerush.commands.intake.WaitForToteCmd;
import com.nutrons.recyclerush.commands.intake.WintakeStopperCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HumanPlayerFeedSlowDownSeq extends CommandGroup {
    
    public  HumanPlayerFeedSlowDownSeq() {
    	addSequential(new IntakeOpenCmd());
    	addSequential(new ElevatorRaiseSlowerCmd());
    	addSequential(new RetractWintakeStopperCmd());
    	addSequential(new SpinWintakeWheelsCmd());
    	addSequential(new WaitForToteCmd());
    	addSequential(new WintakeStopperCmd());
    	addSequential(new IntakeCloseCmd());
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new StopWintakeWheelsCmd());
    	addSequential(new WaitForStackableCmd());
    	addSequential(new LowerElevatorIfStackableCmd(-0.5));
    	addSequential(new StopIntakeWheelsCmd());
    }
}
