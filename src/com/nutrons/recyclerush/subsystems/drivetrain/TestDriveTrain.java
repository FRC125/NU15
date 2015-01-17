package com.nutrons.recyclerush.subsystems.drivetrain;

import com.nutrons.lib.MovingAverage;
import com.nutrons.lib.PIDControl;
import com.nutrons.recyclerush.RobotMap;
import com.nutrons.recyclerush.commands.DriveStraightCmd;
import com.nutrons.recyclerush.commands.DriveTurnCmd;
import com.nutrons.lib.Ultrasonic;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
/***
 * 
 * @author John, Michael
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
	Ultrasonic ultrasonic = new Ultrasonic(RobotMap.ULTRASONIC_AN, RobotMap.ULTRASONIC_RX);
	MovingAverage gyroRateAverage = new MovingAverage(1);
	MovingAverage gyroAngleAverage = new MovingAverage(1);
	
	public PIDControl quickTurnPID = new PIDControl(2.5, 0, 0);
	public PIDControl driveStraightPID = new PIDControl(7.5, 0, 0);
	public double kP = 2.5;
	public double kI = 0.0;
	public double kD = 0.0;
	public double GYRO_CONSTANT = 1.0/350.0;
	
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveStraightCmd());
    	//ultrasonic.setAutomaticMode(true);
    }
	
	public void stop() {
		driveLR(0,0);
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
	
	public void zeroGyro() {
		gyro.reset();
	}
	
	public double getGyroRate() {
		return gyroRateAverage.getAverage(gyro.getRate());
	}
	
	public double getGyroAngle() {
		return gyroAngleAverage.getAverage(gyro.getAngle());
	}
	
	public void setGyroConstant(double constant) {
		GYRO_CONSTANT = constant;
	}
	
	public void setConstant(double constant) {
		kP = constant;
	}
	
	public void drivePID(double throttle, double wheel) {
		double error = wheel - getGyroRate() * GYRO_CONSTANT;
		double adjust = kP * error;
		driveTW(throttle, wheel - adjust);
	}
	
	public void driveStraightPID(double throttle, double targetAngle) {
		driveStraightPID.setTarget(targetAngle);
		driveTW(throttle, -driveStraightPID.getAdjust(getGyroAngle() * GYRO_CONSTANT));
		
	}
	
	public void quickTurn(double targetAngle) {
		quickTurnPID.setTarget(targetAngle * GYRO_CONSTANT);
		driveTW(0, -quickTurnPID.getAdjust(getGyroAngle() * GYRO_CONSTANT));
	}
	
	public double getMotorLeftSpeed() {
		return leftMotor1.get();
	}
	
	public double getMotorRightSpeed() {
		return rightMotor1.get();
	}

	public double getUltrasonicDistance() {
		return ultrasonic.getDistance();
	}
	
	public boolean inDanger(double distance) {
		return getUltrasonicDistance() < distance;
	}

}
