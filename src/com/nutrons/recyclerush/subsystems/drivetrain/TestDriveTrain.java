package com.nutrons.recyclerush.subsystems.drivetrain;

import com.nutrons.lib.MovingAverage;
import com.nutrons.lib.PIDControl;
import com.nutrons.recyclerush.Robot;
import com.nutrons.recyclerush.RobotMap;
import com.nutrons.recyclerush.commands.DriveHPIDCmd;
import com.nutrons.recyclerush.commands.DriveStraightCmd;
import com.nutrons.recyclerush.commands.DriveTurnCmd;
import com.nutrons.lib.Ultrasonic;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;
/***
 * 
 * @author John, Michael
 *
 */
public class TestDriveTrain extends AbstractDriveTrain {
	
	public double kP = 7.5;
	public double kI = 0.0;
	public double kD = 0.0;
	public double GYRO_CONSTANT = 1.0/350.0;
	
	// Motors
	Talon leftMotor1= new Talon(RobotMap.DRIVE_LEFT_1); // motorL
	Talon motorC= new Talon(RobotMap.DRIVE_LEFT_2); // motorC
	Talon rightMotor1= new Talon(RobotMap.DRIVE_RIGHT_1); // motorR
	Talon rightMotor2= new Talon(RobotMap.DRIVE_RIGHT_2);
	
	// Sensors
	Gyro gyro = new Gyro(RobotMap.GYROSCOPE);
	Ultrasonic ultrasonic = new Ultrasonic(RobotMap.ULTRASONIC_AN, RobotMap.ULTRASONIC_RX);
	MovingAverage gyroRateAverage = new MovingAverage(1);
	MovingAverage gyroAngleAverage = new MovingAverage(1);
	
	public PIDControl quickTurnPID = new PIDControl(2.5, 0, 0);
	public PIDControl driveStraightPID = new PIDControl(7.5, 0, 0);
	
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveHPIDCmd());
    	//ultrasonic.setAutomaticMode(true);
    }
	
	public void stop() {
		driveLR(0,0);
	}
	
	public void stopHDrive() {
    	driveLCR(new double[] {0.0, 0.0, 0.0});
    }
	
	public void driveTW(double throttle, double wheel) {
		driveLR(throttle-wheel, -(throttle+wheel));	
	}
	
	public void driveLR(double left, double right) {
		leftMotor1.set(left);
		rightMotor1.set(right);
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
	
	public void driveStraightPID(double throttle, double targetAngle, double hValue) {
		if(Robot.oi.isHoldHeading()) {
			
//			targetAngle = this.getTargetAngle() - (0.08*targetAngle);
//			System.out.println(targetAngle);
			driveStraightPID.setTarget(0);
			driveTW(throttle, -driveStraightPID.getAdjust((getGyroAngle() - getGyroRate()/50.0)*GYRO_CONSTANT));
		}
		else{
			gyro.reset();
			driveTW(throttle, targetAngle);
		}
		motorC.set(hValue);
		
		
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
	    	leftMotor1.set(outputSpeeds[0]);
	    	motorC.set(outputSpeeds[1]);
	    	rightMotor1.set(outputSpeeds[2]);
	    }
	    
	    public double getTargetAngle() {
	    	return driveStraightPID.getTarget();
	    }
}
