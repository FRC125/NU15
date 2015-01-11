package com.nutrons.recyclerush.commands;

import com.nutrons.recyclerush.OI;
import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TestDriveCmd extends Command{

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() 
	{
		// TODO Auto-generated method stub
        Robot.dt.DriveLR(Robot.oi.getDriveLeft()*0.8, Robot.oi.getDriveRight()*0.8);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
