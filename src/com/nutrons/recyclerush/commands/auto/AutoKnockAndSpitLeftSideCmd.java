package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.sequence.SpitIntakeSeq;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/*
 *  Camilo Gonzalez
 */
public class AutoKnockAndSpitLeftSideCmd extends CommandGroup {
    
    public  AutoKnockAndSpitLeftSideCmd() {
    	addSequential(new AutoKnockTote());
    	addSequential(new AutoDriveDistanceCmd(-30));
    	addSequential(new AutoTurnAngleCmd(120));
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new WaitCommand(1));
    	addSequential(new SpitIntakeSeq());
    	addSequential(new WaitCommand(1));
    	addSequential(new StopIntakeWheelsCmd());
    	addSequential(new AutoTurnAngleCmd(-120));
    }
}
