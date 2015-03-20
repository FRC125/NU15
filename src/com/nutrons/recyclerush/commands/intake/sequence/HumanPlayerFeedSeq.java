package com.nutrons.recyclerush.commands.intake.sequence;

import com.nutrons.recyclerush.Robot;
import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseCmd;
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
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author John Zhang
 */
public class HumanPlayerFeedSeq extends CommandGroup {
	
    public  HumanPlayerFeedSeq() {
    	addSequential(new IntakeOpenCmd());
    	addSequential(new ElevatorRaiseCmd());
    	addSequential(new RetractWintakeStopperCmd());
    	addSequential(new SpinWintakeWheelsCmd());
    	addSequential(new WaitForToteCmd());
    	addSequential(new WintakeStopperCmd());
    	addSequential(new IntakeCloseCmd());
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new StopWintakeWheelsCmd());
    	addSequential(new WaitForStackableCmd());
    	addSequential(new LowerElevatorIfStackableCmd());
    	addSequential(new StopIntakeWheelsCmd());
    }
}
