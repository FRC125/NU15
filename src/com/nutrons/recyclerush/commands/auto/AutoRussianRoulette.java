package com.nutrons.recyclerush.commands.auto;

import java.util.Random;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRussianRoulette extends CommandGroup {
    
	Random player = new Random();
	
    public  AutoRussianRoulette() {
    	
    	switch((player.nextInt(5))+1) {
		case 1:
			addSequential(new AutoGrabCanAndTroll());
		break;
		case 2:
			addSequential(new AutoKnockCanAndTurnLeftSide());
		break;
		case 3:
			addSequential(new AutoTurnAngleCmd(720));
		break;
		case 4:
			addSequential(new AutoThreeTotes());
		break;
		case 5:
			addSequential(new AutoDriveForward());
		break;
		default:
			addSequential(new AutoDoNothing());
		break;
    	}
    }
}
