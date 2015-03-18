package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseCmd;
import com.nutrons.recyclerush.commands.intake.SpinIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.sequence.IntakeToteSeq;
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
    	addSequential(new WaitCommand(0.3));
    	addSequential(new AutoDriveDistanceCmd(30));
    	addSequential(new WaitCommand(0.3));
    	addSequential(new AutoTurnAngleCmd(45));
    	addSequential(new SpitIntakeSeq());
    	addSequential(new WaitCommand(0.3));
    	addSequential(new AutoTurnAngleCmd(-45));
    	addSequential(new AutoDriveUntilToteCmd());
    	addSequential(new IntakeToteSeq());
    	addSequential(new WaitCommand(0.3));
    	
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new ElevatorRaiseCmd());
    	addSequential(new WaitCommand(0.3));
    	addSequential(new AutoDriveDistanceCmd(50));
    	addSequential(new WaitCommand(0.3));
    	addSequential(new AutoTurnAngleCmd(45));
    	addSequential(new SpitIntakeSeq());
    	addSequential(new WaitCommand(0.3));
    	addSequential(new AutoTurnAngleCmd(-45));
    	addSequential(new AutoDriveUntilToteCmd());
    	addSequential(new IntakeToteSeq());
    	addSequential(new WaitCommand(0.3));
    	
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new WaitCommand(0.3));
    	addSequential(new AutoTurnAngleCmd(90));
    	addSequential(new StopIntakeWheelsCmd());
    	
    	addSequential(new AutoDriveDistanceCmd(50));
    	addSequential(new SpitIntakeSeq());
    	addSequential(new WaitCommand(1));
    }
}
