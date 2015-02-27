package com.nutrons.recyclerush.subsystems.drivetrain;

import java.util.HashMap;

import com.nutrons.lib.ILoggable;
import com.nutrons.lib.MovingAverage;
import com.nutrons.lib.Utils;
import com.nutrons.recyclerush.Robot;
import com.nutrons.recyclerush.RobotMap;
import com.nutrons.recyclerush.commands.drivetrain.DriveHPIDCmd;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/***
 * 
 * @author John Zhang, Michael
 *
 */
public class DriveTrain extends Subsystem implements ILoggable{
	
	// Constants
	public double GYRO_CONSTANT = 1.0/360.0; // a value that adjusts our 
	public double ENCODER_CONSTANT = 1/5000.0;
	public double offset = 0;
	public double kP_straight = 20;
	public double kI_straight = 0;
	public double kD_straight = 0;
	public double kP_distance = 1;
	public double kI_distance = 0;
	public double kD_distance = 0;
	public double kP_quickturn = 0;
	public double kI_quickturn = 0;
	public double kD_quickturn = 0;
	public double WHEEL_DIAM = 6;
	
	// Motors
	Talon motorL = new Talon(RobotMap.DRIVE_LEFT);
	Talon motorC = new Talon(RobotMap.DRIVE_CENTER);
	Talon motorR = new Talon(RobotMap.DRIVE_RIGHT);
	
	public DriveTrain() {
		headingHoldPID.setContinuous();
		headingHoldPID.setAbsoluteTolerance(1.0/360.0);
		quickTurnPID.setContinuous();
		quickTurnPID.disable();
		leftEncoder.setDistancePerPulse(WHEEL_DIAM*Math.PI/128.0);
		rightEncoder.setDistancePerPulse(WHEEL_DIAM*Math.PI/128.0);
	}
	
	//Inner Classes
	class DistancePIDSource implements PIDSource
	{
		public double pidGet() {
			return Math.max(leftEncoder.getDistance(), rightEncoder.getDistance());
		}
	}
	
	class HoldHeadingPID implements PIDOutput
	{
		public void pidWrite(double output) {
			double left = Utils.deadband(motorL.get() + output, 0.1, 0);
			double right = Utils.deadband(motorR.get() + output, 0.1, 0);
			driveLCR(new double[] {left, -motorC.get(), right});
		}
	}
	
	class DriveDistancePID implements PIDOutput
	{
		public void pidWrite(double output) {
			double left = Utils.deadband(output, 0.1, 0);
			double right = -Utils.deadband(output, 0.1, 0);
			driveLCR(new double[]{left, 0, right});
		}
	}
	
	class QuickTurnOutput implements PIDOutput {
		public void pidWrite(double output) {
			driveLR(output, output);
		}
		
	}
	class GyroWrapper implements PIDSource {
		public double pidGet() {
			return gyro.getAngle() * GYRO_CONSTANT;
		}
	}
	
	// Sensors
	Gyro gyro = new Gyro(RobotMap.GYROSCOPE);
	Encoder leftEncoder = new Encoder(RobotMap.ENCODER_LEFT_DRIVETRAIN_A, RobotMap.ENCODER_LEFT_DRIVETRAIN_B, false, Encoder.EncodingType.k4X);;
	Encoder rightEncoder = new Encoder(RobotMap.ENCODER_RIGHT_DRIVETRAIN_A, RobotMap.ENCODER_RIGHT_DRIVETRAIN_B, false, Encoder.EncodingType.k4X);;
	
	// Moving averages (currently just the value)
	MovingAverage gyroRateAverage = new MovingAverage(1);
	MovingAverage gyroAngleAverage = new MovingAverage(1);
	
	// PIDs
	public PIDController headingHoldPID = new PIDController(kP_straight, kI_straight, kD_straight, new GyroWrapper(), new HoldHeadingPID());
	public PIDController quickTurnPID = new PIDController(kP_quickturn, kI_quickturn, kD_quickturn, new GyroWrapper(), new QuickTurnOutput());
	public PIDController driveDistancePID = new PIDController(kP_distance, kI_distance, kD_distance, new DistancePIDSource(), new DriveDistancePID());

	public void initDefaultCommand() {
    	setDefaultCommand(new DriveHPIDCmd());
    }
	
	/**
	 * Sets motors left, center, and right to motorSpeeds.
	 * @param motorSpeeds
	 */
    public void driveLCR(double[] motorSpeeds) {
    	motorL.set(motorSpeeds[0]);
    	motorC.set(-motorSpeeds[1]);
    	motorR.set(motorSpeeds[2]);
    }
	
    public void setDistancePIDGains(double p, double i, double d) {
    	driveDistancePID.setPID(p, i, d);
    }

    public void setHoldHeadingPIDGains(double p, double i, double d) {
    	driveDistancePID.setPID(p, i, d);
    }
    
    public void setQuickturnPIDGains(double p, double i, double d) {
    	driveDistancePID.setPID(p, i, d);
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
	
	public void setEncoderConstant(double constant) {
		ENCODER_CONSTANT = constant;
	}
	
	public double mapJoystickToPowerOutput(double input) {
        if (Math.abs(input) < 0.05) {
            // Stop if joystick is near zero
            return 0.0;
        } else {
            double mapping;
            if (Math.abs(input) <= 0.75) {
                mapping = 0.95 * ((0.5 * Math.pow(Math.abs(input), 2.0)) + 0.2);
                mapping = (input >= 0) ? mapping : -mapping; // Change to negative if the input was negative
                return mapping;
            } else {
                mapping = 2.16 * Math.abs(input) - 1.16;
                mapping = (input >= 0) ? mapping : -mapping; // Change to negative if the input was negative
                return mapping;
            }
        }
    }
	
	/**
	 * @param x
	 * @param y
	 * @param rot
	 */
	public void drivePID(double x, double y, double rot) {
		if(Robot.oi.isResetGyroButton()) {
			gyro.reset();
			offset = 0;
		}
		x = mapJoystickToPowerOutput(x);
		y = mapJoystickToPowerOutput(y);
		rot = mapJoystickToPowerOutput(rot);
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
		} else if(quickTurnPID.isEnable()) {
			
		} else {
			headingHoldPID.disable();
			offset -= gyro.getAngle();
			offset = offset % 360.0; // sets offset to value between 0-360
			gyro.reset();//sets gyro to 0
			driveLCR(getMotorOutput(x, y, rot));
		}
	}
	
	public void driveStraightForDistance(double distance) {
		//headingHoldPID.enable();
		driveDistancePID.enable();
		driveDistancePID.setInputRange(0, 2*distance);
		driveDistancePID.setOutputRange(-1, 1);
		//headingHoldPID.setSetpoint(0);
		driveDistancePID.setSetpoint(distance);
	}
	
	public double getEncoderMax() {
		return Math.max(leftEncoder.getDistance(), rightEncoder.getDistance());
	}
	
	/**
	 * turn robot to specific angle relative to position at start of game
	 * @param targetAngle
	 */
	public void quickTurn(double targetAngle) {
		quickTurnPID.enable();
		quickTurnPID.setSetpoint(targetAngle * GYRO_CONSTANT);
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
    	for(int i = 0; i < wheels; i++) {
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
   		hashmap.put("motorC", RobotMap.DRIVE_LEFT);
   		hashmap.put("motorR", RobotMap.DRIVE_RIGHT);
    			
   		return hashmap;
   	}
    
    public double getLeftDistance() {
    	return leftEncoder.getDistance();
    }
    
    public double getRightDistance() {
    	return rightEncoder.getDistance();
    }
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
}
