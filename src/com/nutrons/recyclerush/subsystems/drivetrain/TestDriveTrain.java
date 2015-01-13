package com.nutrons.recyclerush.subsystems.drivetrain;

import com.nutrons.recyclerush.RobotMap;
import com.nutrons.recyclerush.commands.DriveStraightCmd;
import com.nutrons.recyclerush.commands.TestDriveCmd;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
/***
 *  test kit drive train
 * @author Michael
 *
 */
public class TestDriveTrain extends AbstractDriveTrain {
	
	// Motors
	Talon leftMotor1= new Talon(RobotMap.DRIVE_LEFT_1);
	Talon leftMotor2= new Talon(RobotMap.DRIVE_LEFT_2);
	Talon rightMotor1= new Talon(RobotMap.DRIVE_RIGHT_1);
	Talon rightMotor2= new Talon(RobotMap.DRIVE_RIGHT_2);
	
	// Sensors
	Gyro gyro = new Gyro(RobotMap.GYROSCOPE);
	
	public double kp = 0.2;
	public double gyroConstant = 1;
	public double[] gyroAverage;
	public int indexReadings = 0;

	public void initDefaultCommand() {
    	setDefaultCommand(new DriveStraightCmd());
    }
	
	public void driveTW(double throttle, double wheel) {
		driveLR(throttle-wheel, -(throttle+wheel));	
	}
	
	public void driveLR(double left, double right) {
		leftMotor1.set(left);
		leftMotor2.set(left);
		rightMotor1.set(right);
		rightMotor2.set(right);
	}
	
	public void driveStraightGyro(double throttle, double gyro) {
		double wheel = gyro * kp;
		driveTW(throttle, wheel);
	}
	
	public void zeroGyro() {
		gyro.reset();
	}
	
	public double getGyro() {
		return gyro.getRate();
	}
	
	public double getGyroWithCap() {
		double rate = gyro.getRate();
		
		if(rate > -10 && rate < 10) {
			rate = 0;
		}
		
		return rate;
	}
	
	public double getGyroAverage(int numReadings,double cons) {
		double gyroSum = 0;
		gyroAverage = new double[numReadings];
		
		gyroAverage[indexReadings] = getGyro();
		indexReadings++;
		indexReadings = indexReadings % numReadings;
		
		for(int i = 0; i < numReadings; i++) {
			gyroSum += gyroAverage[i];
		}
		return gyroSum / numReadings;
		
	}
	
	public void setConstant(double val) {
		kp = val;
	}
	
	public void setGyroConstant(double val) {
		gyroConstant = val;
	}

}
