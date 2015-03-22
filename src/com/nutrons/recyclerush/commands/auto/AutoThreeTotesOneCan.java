package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseCmd;
import com.nutrons.recyclerush.commands.intake.SpinIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.sequence.IntakeTapToteSeq;
import com.nutrons.recyclerush.commands.intake.sequence.IntakeToteSeq;
import com.nutrons.recyclerush.commands.intake.sequence.SpitIntakeSeq;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoThreeTotesOneCan extends CommandGroup {
    
    public  AutoThreeTotesOneCan() {
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new ElevatorRaiseCmd());
    	addSequential(new AutoDriveDistanceAndIntakeCmd(35, 10, 35, 1));
    	addSequential(new WaitCommand(0.2));
    	addSequential(new AutoTurnAngleCmd(45));
    	addSequential(new SpitIntakeSeq());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoTurnAngleCmd(-45));
    	addSequential(new AutoDriveUntilToteCmd());
    	addSequential(new IntakeToteSeq());
    	addSequential(new IntakeTapToteSeq());
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new ElevatorRaiseCmd());
    	
    	addSequential(new AutoDriveUntilToteCmd());
    	addSequential(new IntakeToteSeq());
    	addSequential(new IntakeTapToteSeq());    	
    	
    	addSequential(new AutoTurnAngleCmd(90));
    	addSequential(new StopIntakeWheelsCmd());
    	
    	addSequential(new AutoDriveDistanceLowerElevatorCmd(70));
    	addSequential(new AutoSpitAndDriveBackCmd(-50));
    }
}
