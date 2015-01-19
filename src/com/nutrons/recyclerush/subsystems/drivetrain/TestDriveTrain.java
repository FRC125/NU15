package com.nutrons.recyclerush.subsystems.drivetrain;

import com.nutrons.lib.MovingAverage;
import com.nutrons.lib.PIDControl;
import com.nutrons.lib.Ultrasonic;
import com.nutrons.recyclerush.Robot;
import com.nutrons.recyclerush.RobotMap;
import com.nutrons.recyclerush.commands.DriveHPIDCmd;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
/***
 * 
 * @author John, Michael
 *
 */
public class TestDriveTrain extends AbstractDriveTrain {
	
	// Constants
	public double kP = 7.5;
	public double kI = 0.0;
	public double kD = 0.0;
	public double GYRO_CONSTANT = 1.0/350.0;
	public double offset = 0;
	
	// Motors
	Talon motorL = new Talon(RobotMap.DRIVE_LEFT);
	Talon motorC = new Talon(RobotMap.DRIVE_CENTER);
	Talon motorR = new Talon(RobotMap.DRIVE_RIGHT);
	
	// Sensors
	public Gyro gyro = new Gyro(RobotMap.GYROSCOPE);
	Ultrasonic ultrasonic = new Ultrasonic(RobotMap.ULTRASONIC_AN, RobotMap.ULTRASONIC_RX);
	MovingAverage gyroRateAverage = new MovingAverage(1);
	MovingAverage gyroAngleAverage = new MovingAverage(1);
	
	// PIDs
	public PIDControl quickTurnPID = new PIDControl(2.5, 0, 0);
	public PIDControl driveStraightPID = new PIDControl(7.5, 0, 0);
	
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveHPIDCmd());
    }
	
	public void stop() {
    	driveLCR(new double[] {0.0, 0.0, 0.0});
    }
	
	public void driveTW(double throttle, double wheel) {
		driveLR(throttle-wheel, -(throttle+wheel));	
	}
	
	public void driveLR(double left, double right) {
		motorL.set(left);
		motorR.set(right);
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
	
	public void drivePID(double x, double y, double rot) {
		if(Robot.oi.isResetGyroButton()) {
			gyro.reset();
			offset = 0;
		}
		
		if(Robot.oi.isFieldCentric()) {
			double theta = Math.toRadians(Robot.dt.offset);
			double temp = x * Math.cos(theta) + y * Math.sin(theta);
			y = -x * Math.sin(theta) + y * Math.cos(theta);
			x = temp;
		}
		
		if(Robot.oi.isHoldHeading()) {
			driveStraightPID.setTarget(0);
			driveLCR(getOutput(0, y, -driveStraightPID.getAdjust(getGyroAngle()*GYRO_CONSTANT)));
		}else{
			offset += gyro.getAngle();
			offset = offset % 360.0;
			gyro.reset();
			driveLCR(getOutput(x, y, rot));
		}
	}
	
	public void quickTurn(double targetAngle) {
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
	
	 public double[] getOutput(double x, double y, double rot) {
    	int wheels = 3;
    	double spinScale = 0.5;
    	double angles[] = {0, 1.507, 3.14};
    	double maxOutput = 1.0;
    	
    	// left, center, right
    	double outputs[] = {0, 0, 0};
    	
    	double maxValue = 0.0;
    	// Compute the array of inputs
    	for(int i = 0; i < wheels; i++){
    		outputs[i] = -(x)*Math.sin(angles[i])+y*Math.cos(angles[i])+(-spinScale*rot);
    		if(Math.abs(outputs[i]) > maxValue) {
    			maxValue = Math.abs(outputs[i]);
    		}
    	}
    	// If we've given any output a value greater than max
    	// then scale it down.
    	if(maxValue > maxOutput) {
    		double scale = maxOutput/maxValue;
    		for(int i = 0; i < wheels; i++) {
    			outputs[i] = outputs[i] * scale;
    		}
    	}
    	
    	return outputs;
    }
	    
    public void driveLCR(double[] outputSpeeds) {
    	motorL.set(outputSpeeds[0]);
    	motorC.set(outputSpeeds[1]);
    	motorR.set(outputSpeeds[2]);
    }
    
    public double getTargetAngle() {
    	return driveStraightPID.getTarget();
    }
}
