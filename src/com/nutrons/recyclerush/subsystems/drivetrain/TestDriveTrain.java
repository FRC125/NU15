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
	
	Talon leftMotor1= new Talon(RobotMap.DRIVE_LEFT_1);
	Talon leftMotor2= new Talon(RobotMap.DRIVE_LEFT_2);
	Talon rightMotor1= new Talon(RobotMap.DRIVE_RIGHT_1);
	Talon rightMotor2= new Talon(RobotMap.DRIVE_RIGHT_2);
	
	public void initDefaultCommand() {
    	setDefaultCommand(new TestDriveCmd());
    }
	
	@Override
	public void driveTW(double throttle, double wheel) {
		driveLR(throttle-wheel, throttle+wheel);
		
	}
	
	public void driveLR (double left, double right) {
		leftMotor1.set(left);
		leftMotor2.set(left);
		rightMotor1.set(right);
		rightMotor2.set(right);
		
	}

}
