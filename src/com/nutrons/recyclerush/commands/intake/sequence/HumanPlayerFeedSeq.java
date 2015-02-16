package com.nutrons.recyclerush.commands.intake.sequence;

import com.nutrons.recyclerush.Robot;
import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeCloseCmd;
import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;
import com.nutrons.recyclerush.commands.intake.SpinIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.SpinWintakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.StopWintakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.WaitForToteCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * @author John Zhang
 */
public class HumanPlayerFeedSeq extends CommandGroup {
	
    public  HumanPlayerFeedSeq() {
    	addSequential(new ElevatorRaiseCmd());
    	addSequential(new IntakeOpenCmd());
    	addSequential(new SpinWintakeWheelsCmd());
    	addSequential(new WaitForToteCmd());

    		addSequential(new IntakeCloseCmd());
    		addSequential(new SpinIntakeWheelsCmd());
    		addSequential(new StopWintakeWheelsCmd());
    		addSequential(new WaitCommand(1));
    		addSequential(new StopIntakeWheelsCmd());
    		addSequential(new ElevatorLowerCmd());
    	
    	
    }
}
