package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.elevator.ElevatorRaiseSlowerCmd;
import com.nutrons.recyclerush.commands.elevator.LowerElevatorIfStackableCmd;
import com.nutrons.recyclerush.commands.intake.IntakeOpenCmd;
import com.nutrons.recyclerush.commands.intake.IntakeUntilStackableCmd;
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
    	addSequential(new ElevatorRaiseSlowerCmd());
    	addSequential(new IntakeOpenCmd());
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new AutoDriveUntilToteCmd());
    	addSequential(new AutoTimeDriveCmd(0.2));
    	addSequential(new WaitCommand(0.2));
    	addSequential(new AutoTurnAngleCmd(45));
    	addSequential(new SpitIntakeSeq());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoTurnAngleCmd(-45));
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new AutoDriveUntilToteCmd());
    	addSequential(new IntakeToteSeq());
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new ElevatorRaiseSlowerCmd());
    	
    	addSequential(new SpinIntakeWheelsCmd());
    	addSequential(new AutoDriveUntilToteCmd());
    	addSequential(new IntakeUntilStackableCmd());    	
    	
    	addSequential(new AutoTurnAngleCmd(90));
    	addSequential(new StopIntakeWheelsCmd());
    	
    	addSequential(new AutoDriveDistanceLowerElevatorCmd(70));
    	addSequential(new AutoSpitAndDriveBackCmd(-50));
    }
}
