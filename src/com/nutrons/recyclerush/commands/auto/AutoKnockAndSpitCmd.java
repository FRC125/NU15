package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.commands.elevator.ElevatorLowerCmd;
import com.nutrons.recyclerush.commands.intake.StopIntakeWheelsCmd;
import com.nutrons.recyclerush.commands.intake.sequence.SpitIntakeSeq;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoKnockAndSpitCmd extends CommandGroup {
	public AutoKnockAndSpitCmd()
	{
		//TODO: Knock RC, drive backwards, turn 45, lower elevator, spit tote, turn -45
		addSequential(new AutoKnockTote());
    	addSequential(new AutoDriveDistanceCmd(-30));
    	addSequential(new AutoTurnAngleCmd(45));
    	addSequential(new ElevatorLowerCmd());
    	addSequential(new WaitCommand(1));
    	addSequential(new SpitIntakeSeq());
    	addSequential(new WaitCommand(1));
    	addSequential(new StopIntakeWheelsCmd());
    	addSequential(new AutoTurnAngleCmd(-45));
	}

}
