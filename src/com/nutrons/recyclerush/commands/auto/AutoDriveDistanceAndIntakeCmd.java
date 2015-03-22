package com.nutrons.recyclerush.commands.auto;

import com.nutrons.recyclerush.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author John Zhang
 *
 */
public class AutoDriveDistanceAndIntakeCmd extends Command {

	Timer timer = new Timer();
	double epsilon = 1.0;
	double distance = 0;
	double speed = 1.0;
	double time;
	double maxSpeed = 11;
	double intakeStart;
	double intakeEnd;
	
    public AutoDriveDistanceAndIntakeCmd(double distance, double intakeDistanceStart, double intakeDistanceEnd) {
    	this.distance = distance;
    	requires(Robot.dt);
    	time = distance / (maxSpeed * speed * 12) + 1;
    	timer.start();
    	intakeStart = intakeDistanceStart;
    	intakeEnd = intakeDistanceEnd;
    }
    
    public AutoDriveDistanceAndIntakeCmd(double distance, double speed) {
    	this.distance = distance;
    	this.speed = Math.abs(speed);
    	requires(Robot.dt);
    	time = distance / (maxSpeed * speed * 12) + 1;
    	timer.start();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dt.resetEncoders();
    	Robot.dt.setEncoderConstant(1.0/distance);
    	Robot.dt.zeroGyro();
    	Robot.dt.driveStraightForDistance(distance);
    	Robot.dt.driveDistancePID.setOutputRange(-speed, speed);
    	Robot.dt.driveDistancePID.setAbsoluteTolerance(epsilon);
    	Robot.dt.headingHoldPID.enable();
    	Robot.dt.headingHoldPID.setSetpoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Distance Error", Robot.dt.driveDistancePID.getError());
    	SmartDashboard.putNumber("Distance PID Output", Robot.dt.driveDistancePID.get());

		SmartDashboard.putNumber("Error", Robot.dt.headingHoldPID.getError());
		SmartDashboard.putNumber("Correction", Robot.dt.headingHoldPID.get());
		if(Robot.dt.getLeftDistance() > intakeStart && Robot.dt.getLeftDistance() < intakeEnd) {
			Robot.intake.setIntakeMotorPower(1);
		} else {
			Robot.intake.stopIntakeMotor();
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.dt.driveDistancePID.onTarget();
    			//|| timer.get() > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.driveDistancePID.reset();
    	Robot.dt.driveDistancePID.disable();
    	Robot.dt.headingHoldPID.disable();
    	Robot.dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
