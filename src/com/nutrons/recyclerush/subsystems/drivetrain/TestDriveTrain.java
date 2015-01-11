package com.nutrons.recyclerush.subsystems.drivetrain;

import com.nutrons.recyclerush.RobotMap;
import com.nutrons.recyclerush.commands.TestDriveCmd;

import edu.wpi.first.wpilibj.Talon;
/***
 *  test kit drive train
 * @author Michael
 *
 */
public class TestDriveTrain extends AbstractDriveTrain {
	Talon left1= new Talon(RobotMap.DRIVELEFT1);
	Talon left2= new Talon(RobotMap.DRIVELEFT2);
	Talon right1= new Talon(RobotMap.DRIVERIGHT1);
	Talon right2= new Talon(RobotMap.DRIVERIGHT2);
	@Override
	public void DriveTW(double throttle, double wheel) {
		// TODO Auto-generated method stub
		DriveLR(throttle-wheel, throttle+wheel);
		
	}
	/***
	 * drive based on left and right command
	 * @param left speed of left side
	 * @param right speed of right side
	 */
	public void DriveLR (double left, double right)
	{
		left1.set(left);
		left2.set(left);
		right1.set(right);
		right2.set(right);
		
	}
	public void initDefaultCommand() {
    	setDefaultCommand(new TestDriveCmd());
    }

}
