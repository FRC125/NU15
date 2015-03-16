package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseCmd;
import com.nutrons.recyclerush.commands.intake.sequence.SpitIntakeSeq;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoThreeTotes extends CommandGroup {
    
    public  AutoThreeTotes() {
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new ElevatorRaiseCmd());
    	addSequential(new WaitCommand(1));
    	addSequential(new AutoDriveDistanceCmd(30));
    	addSequential(new WaitCommand(1));
    	addSequential(new AutoTurnAngleCmd(90));
    	addSequential(new SpitIntakeSeq());
    	addSequential(new AutoTurnAngleCmd(-90));
    	addSequential(new AutoDriveUntilToteCmd());
    	
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new ElevatorRaiseCmd());
    	addSequential(new WaitCommand(1));
    	addSequential(new AutoDriveDistanceCmd(30));
    	addSequential(new WaitCommand(1));
    	addSequential(new AutoTurnAngleCmd(90));
    	addSequential(new SpitIntakeSeq());
    	addSequential(new AutoTurnAngleCmd(-90));
    	
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new ElevatorRaiseCmd());
    	addSequential(new WaitCommand(1));
    	addSequential(new AutoDriveDistanceCmd(30));
    	addSequential(new WaitCommand(1));
    	addSequential(new AutoTurnAngleCmd(90));
    	addSequential(new SpitIntakeSeq());
    	
    	addSequential(new AutoDriveDistanceCmd(150));
    	addSequential(new ElevatorLowerCmd());
    }
}
