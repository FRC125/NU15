package com.nutrons.recyclerush.subsystems.drivetrain;

import com.nutrons.lib.MovingAverage;
import com.nutrons.lib.PIDControl;
import com.nutrons.lib.Ultrasonic;
import com.nutrons.recyclerush.Robot;
import com.nutrons.recyclerush.RobotMap;
import com.nutrons.recyclerush.commands.DriveTurnCmd;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/***
 * 
 * @author John, Michael
 *
 */
public class DriveTrain extends Subsystem {
	
	// Constants
	public double GYRO_CONSTANT = 1.0/360.0; // a value that adjusts our 
	public double offset = 0;
	
	// Motors
	Talon motorL = new Talon(RobotMap.DRIVE_LEFT);
	Talon motorC = new Talon(RobotMap.DRIVE_CENTER);
	Talon motorR = new Talon(RobotMap.DRIVE_RIGHT);
	
	// Sensors
	Gyro gyro = new Gyro(RobotMap.GYROSCOPE);
	Ultrasonic ultrasonic = new Ultrasonic(RobotMap.ULTRASONIC_AN, RobotMap.ULTRASONIC_RX);
	
	// Moving averages (currently just the value)
	MovingAverage gyroRateAverage = new MovingAverage(1);
	MovingAverage gyroAngleAverage = new MovingAverage(1);
	
	// PIDs
	public PIDControl quickTurnPID = new PIDControl(2.5, 0, 0);
	public PIDControl holdHeadingPID = new PIDControl(7.5, 0, 0);
	
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveTurnCmd());
    }
	
	/**
	 * Sets motors left, center, and right to motorSpeeds.
	 * @param motorSpeeds
	 */
    public void driveLCR(double[] motorSpeeds) {
    	motorL.set(motorSpeeds[0]);
    	motorC.set(motorSpeeds[1]);
    	motorR.set(motorSpeeds[2]);
    }
	
	/**
	 * Stops all the motors on the drive train.
	 */
	public void stop() {
    	driveLCR(new double[] {0.0, 0.0, 0.0});
    }
	
	/**
	 * Drives the motors using throttle and wheel control.
	 * Can use wheel to turn in place without throttle.
	 * @param throttle
	 * @param wheel
	 */
	public void driveTW(double throttle, double wheel) {
		driveLR(throttle-wheel, -(throttle+wheel));	
	}
	
	/**
	 * Sets the left and right motors to speeds.
	 * @param left
	 * @param right
	 */
	public void driveLR(double left, double right) {
		motorL.set(left);
		motorR.set(right);
	}
	
	/**
	 * Resets the gyro to angle zero.
	 */
	public void zeroGyro() {
		gyro.reset();
	}
	
	/**
	 * @return moving average value of gyro rate in degrees per second.
	 */
	public double getGyroRate() {
		return gyroRateAverage.getAverage(gyro.getRate());
	}
	
	/**
	 * @return moving average value of gyro angle in degrees.
	 */
	public double getGyroAngle() {
		return gyroAngleAverage.getAverage(gyro.getAngle());
	}
	
	/**
	 * Sets the scaling factor for adjusting motor speeds using the PID output.
	 * @param constant
	 */
	public void setGyroConstant(double constant) {
		GYRO_CONSTANT = constant;
	}
	
	/**
	 *  
	 * @param x
	 * @param y
	 * @param rot
	 */
	public void drivePID(double x, double y, double rot) {
		if(Robot.oi.isResetGyroButton()) {
			gyro.reset();
			offset = 0;
		}
		
		/*
		 * makes x and y relative to the field instead of relative to the robot 
		 * makes it so
		 */
		if(Robot.oi.isFieldCentric()) {
			
			double theta = Math.toRadians(Robot.dt.offset); //converts offset to radians
			double temp = x * Math.cos(theta) + y * Math.sin(theta);// we need to use the initial x value.		
			y = -x * Math.sin(theta) + y * Math.cos(theta); 
			x = temp;
		}
		/*
		 * makes sure the robot keeps to the target angle.
		 * prevents rotation.
		 * makes the robot turn the other cheek
		 */
		if(Robot.oi.isHoldHeading())
		{
			holdHeadingPID.setTarget(0);
			driveLCR(getMotorOutput(x, y, -holdHeadingPID.getAdjust(getGyroAngle()*GYRO_CONSTANT)));// can't translate left or right
		}
		else
		{
			offset += gyro.getAngle();
			offset = offset % 360.0; // sets offset to value between 0-360
			gyro.reset();//sets gyro to 0
			driveLCR(getMotorOutput(x, y, rot));
		}
	}
	
	/**
	 * turn robot to specific angle relative to position at start of game
	 * @param targetAngle
	 */
	public void quickTurn(double targetAngle) 
	{
		quickTurnPID.setTarget(targetAngle * GYRO_CONSTANT);
		driveTW(0, -quickTurnPID.getAdjust(getGyroAngle() * GYRO_CONSTANT));
	}
	
	public double getMotorLeftSpeed() {
		return motorL.get();
	}
	
	public double getMotorRightSpeed() {
		return motorR.get();
	}
	
	public double getMotorCenterSpeed() {
		return motorC.get();
	}

	public double getUltrasonicDistance() {
		return ultrasonic.getDistance();
	}
	
	public boolean inDanger(double distance) {
		return getUltrasonicDistance() < distance;
	}
	
	/**
	 * regular H drive
	 * @param x
	 * @param y
	 * @param rot
	 * @return
	 */
	public double[] getMotorOutput(double x, double y, double rot) {
    	int wheels = 3;
    	double spinScale = 0.5;
    	double angles[] = {0, 1.507, 3.14};
    	double maxOutput = 1.0;
    	
    	// left, center, right
    	double outputs[] = {0, 0, 0};
    	
    	double maxValue = 0.0;
    	// Compute the array of inputs
    	for(int i = 0; i < wheels; i++)
    	{
    		outputs[i] = -(x)*Math.sin(angles[i]) + y*Math.cos(angles[i]) - (spinScale*rot);
    		if(Math.abs(outputs[i]) > maxValue)
    		{
    			maxValue = Math.abs(outputs[i]);
    		}
    	}
    	// If we've given any output a value greater than max
    	// then scale it down.
    	if(maxValue > maxOutput)
    	{
    		double scale = maxOutput / maxValue;
    		for(int i = 0; i < wheels; i++)
    		{
    			outputs[i] = outputs[i] * scale;
    		}
    	}
    	
    	return outputs;
    }
    /**
     * 
     * @return angle you want to hold
     */
    public double getTargetAngle(){
    	return holdHeadingPID.getTarget();
    }
}
