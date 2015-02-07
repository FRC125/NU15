package com.nutrons.recyclerush.subsystems.drivetrain;

import java.util.HashMap;

import com.nutrons.lib.ILoggable;
import com.nutrons.lib.MovingAverage;
import com.nutrons.lib.Ultrasonic;
import com.nutrons.recyclerush.Robot;
import com.nutrons.recyclerush.RobotMap;
import com.nutrons.recyclerush.commands.DriveHPIDCmd;
import com.nutrons.recyclerush.commands.DriveTurnCmd;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/***
 * 
 * @author John Zhang, Michael
 *
 */
public class DriveTrain extends Subsystem implements ILoggable{
	
	// Constants
	public double GYRO_CONSTANT = 1.0/360.0; // a value that adjusts our 
	public double offset = 0;
	public double kP = 20;
	public double kI = 0;
	public double kD = 0;
	public double kP_quickturn = 0;
	public double kI_quickturn = 0;
	public double kD_quickturn = 0;
	// Motors
	Talon motorL1 = new Talon(RobotMap.DRIVE_LEFT_1);
	Talon motorL2 = new Talon(RobotMap.DRIVE_LEFT_2);
	Talon motorC = new Talon(RobotMap.DRIVE_CENTER);
	Talon motorR1 = new Talon(RobotMap.DRIVE_RIGHT_1);
	Talon motorR2 = new Talon(RobotMap.DRIVE_RIGHT_2);
	
	//Inner Classes
	class HoldHeadingPID implements PIDOutput
	{

		@Override
		public void pidWrite(double output) {
			// TODO Auto-generated method stub
			driveLR(motorL1.get() + output, motorR1.get() + output);
		}
		
	}
	
	class QuickTurnOutput implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			// TODO Auto-generated method stub
			driveLR(output, output);
		}
		
	}
	class GyroWrapper implements PIDSource {

		@Override
		public double pidGet() {
			// TODO Auto-generated method stub
			return gyro.getAngle() * GYRO_CONSTANT;
		}
	}
	
	// Sensors
	Gyro gyro = new Gyro(RobotMap.GYROSCOPE);
	Ultrasonic ultrasonic = new Ultrasonic(RobotMap.ULTRASONIC_AN, RobotMap.ULTRASONIC_RX);
	
	// Moving averages (currently just the value)
	MovingAverage gyroRateAverage = new MovingAverage(1);
	MovingAverage gyroAngleAverage = new MovingAverage(1);
	
	// PIDs
	public PIDController headingHoldPID = new PIDController(kP, kI, kD, new GyroWrapper(), new HoldHeadingPID());
	public PIDController quickTurnPID = new PIDController(kP_quickturn, kI_quickturn, kD_quickturn, this.gyro, new QuickTurnOutput());
	
	public void initDefaultCommand() {
		headingHoldPID.setContinuous();
		headingHoldPID.setAbsoluteTolerance(1.0/360.0);
		quickTurnPID.setContinuous();
		quickTurnPID.disable();
    	setDefaultCommand(new DriveHPIDCmd());
    }
	
	/**
	 * Sets motors left, center, and right to motorSpeeds.
	 * @param motorSpeeds
	 */
    public void driveLCR(double[] motorSpeeds) {
    	motorL1.set(motorSpeeds[0]);
    	motorL2.set(motorSpeeds[0]);
    	motorC.set(motorSpeeds[1]);
    	motorR1.set(motorSpeeds[2]);
    	motorR2.set(motorSpeeds[2]);
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
		motorL1.set(left);
		motorL2.set(left);
		motorR1.set(right);
		motorR2.set(right);
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
			y = -(x * Math.sin(theta) - y * Math.cos(theta)); 
			x = temp;
		}
		/*
		 * makes sure the robot keeps to the target angle.
		 * prevents rotation.
		 * makes the robot turn the other cheek
		 */
		if(Robot.oi.isHoldHeading()) {	
			headingHoldPID.enable();
			headingHoldPID.setSetpoint(0);
			driveLCR(getMotorOutput(x, y, 0.0));
		}
		else {
			headingHoldPID.disable();
			offset -= gyro.getAngle();
			offset = offset % 360.0; // sets offset to value between 0-360
			gyro.reset();//sets gyro to 0
			driveLCR(getMotorOutput(x, y, rot));
		}
	}
	
	/**
	 * turn robot to specific angle relative to position at start of game
	 * @param targetAngle
	 */
	public void quickTurn(double targetAngle) {
		quickTurnPID.enable();
		quickTurnPID.setSetpoint(targetAngle);
	}
	
	public double getMotorLeftSpeed() {
		return motorL1.get();
	}
	
	public double getMotorRightSpeed() {
		return motorR1.get();
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
    	return headingHoldPID.getSetpoint();
    }
    
    /**
     * @return HashMap of all motors and their respective ports
     */
    public HashMap<String, Integer> getLogInfo() {
    	HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
   		
   		hashmap.put("motorL", RobotMap.DRIVE_CENTER);
   		hashmap.put("motorC", RobotMap.DRIVE_LEFT_1);
   		hashmap.put("motorR", RobotMap.DRIVE_RIGHT_1);
    			
   		return hashmap;
   	}
}
