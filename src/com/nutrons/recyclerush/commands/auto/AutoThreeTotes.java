package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseCmd;
import com.nutrons.recyclerush.commands.intake.SpinIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.WaitForStackableCmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoThreeTotes extends CommandGroup {
    
    public  AutoThreeTotes() {
    	addSequential(new AutoDriveUntilToteCmd()); // first tote
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new WaitForStackableCmd());
    	addSequential(new StopIntakeWheelsCmd());
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new ElevatorRaiseCmd());
    	addSequential(new AutoDriveUntilToteCmd()); // second tote
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new WaitForStackableCmd());
    	addSequential(new StopIntakeWheelsCmd());
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new ElevatorRaiseCmd());
    	addSequential(new AutoDriveUntilToteCmd());
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new WaitForStackableCmd());
    	addSequential(new StopIntakeWheelsCmd());
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new AutoTurnAngleCmd(90)); // Turn right 90 degrees
    	addSequential(new AutoDriveDistanceCmd(2)); // Must figure this distance
    	addSequential(new AutoDriveDistanceCmd(-1));
    }
}
