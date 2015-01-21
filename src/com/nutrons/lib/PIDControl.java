package com.nutrons.lib;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author John Petryk
 *
 */
public class PIDControl {
	private double kP;
	private double kI;
	private double kD;
	
	private double error = 0.0;
	private double lastError = 0.0;
	private double sumError = 0.0;
	private MovingAverage sumErrorAverage = new MovingAverage(10);
	
	private double target  = 0.0;
	private double adjust = 0.0;
	
	public PIDControl(double p, double i, double d) {
		kP = p;
		kI = i;
		kD = d;
	}
	
	public void setTarget(double target) {
		this.target = target;
	}
	
	public double getAdjust(double input) {
		lastError = error;
		error = target - input;
		sumError += error;
		adjust = error * kP + sumErrorAverage.getAverage(error) * 10.0 * kI + (error - lastError) * kD;
		return adjust;
	}
	
	public void updateValues() {
		kP = SmartDashboard.getNumber("kP");
		kI = SmartDashboard.getNumber("kI");
		kD = SmartDashboard.getNumber("kD");
		SmartDashboard.putNumber("kP", kP);
		SmartDashboard.putNumber("kI", kI);
		SmartDashboard.putNumber("kD", kD);
		SmartDashboard.putNumber("Error", error);
		SmartDashboard.putNumber("Target", target);
		SmartDashboard.putNumber("Adjust", adjust);
	}
	
	public double getTarget() {
		return this.target;
	}
}
