package com.nutrons.recyclerush.subsystems.intake;

import com.nutrons.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    
	Talon floorIntakeMotor = new Talon(RobotMap.INTAKE_MOTOR);
	Talon wintakeMotor = new Talon(RobotMap.WINTAKE_MOTOR);
	Solenoid leftIntakeWheelPiston = new Solenoid(RobotMap.LEFT_INTAKE_WHEEL_PISTON);
	Solenoid rightIntakeWheelPiston = new Solenoid(RobotMap.RIGHT_INTAKE_WHEEL_PISTON);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setIntakeMotorPower(double pow) {
    	floorIntakeMotor.set(pow);
    }
    
    public void setWintakeMotorPower(double pow) {
    	wintakeMotor.set(pow);
    }
    
    public void openLeftIntakeWheel() {
    	leftIntakeWheelPiston.set(true);
    }
    
    public void openRightIntakeWheel() {
    	rightIntakeWheelPiston.set(true);
    }
    
    public void closeLeftIntakeWheel() {
    	leftIntakeWheelPiston.set(false);
    }

    public void closeRightIntakeWheel() {
    	rightIntakeWheelPiston.set(false);
    }
}