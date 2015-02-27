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

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoThreeTotes extends CommandGroup {
    
    public  AutoThreeTotes() {
    	addSequential(new IntakeOpenCmd());
    	addSequential(new AutoDriveUntilToteCmd()); // first tote
    	addSequential(new WaitForToteCmd());
    	addSequential(new IntakeCloseCmd());
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new WaitForStackableCmd());
    	addSequential(new LowerElevatorIfStackableCmd());
    	addSequential(new ElevatorRaiseCmd());
    	addSequential(new IntakeOpenCmd());
    	addSequential(new WaitForToteCmd());
    	addSequential(new IntakeCloseCmd());
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new WaitForStackableCmd());
    	addSequential(new LowerElevatorIfStackableCmd());
    	addSequential(new ElevatorRaiseCmd());
    	addSequential(new IntakeOpenCmd());
    	addSequential(new WaitForToteCmd());
    	addSequential(new IntakeCloseCmd());
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new WaitForStackableCmd());
    	addSequential(new AutoStopDriveCmd());
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new StopIntakeWheelsCmd());
    	addSequential(new AutoTurnAngleCmd(90)); // Turn right 90 degrees
    	addSequential(new AutoDriveDistanceCmd(10)); // Must figure this distance
    	addSequential(new AutoDriveDistanceCmd(-5));
    }
}
