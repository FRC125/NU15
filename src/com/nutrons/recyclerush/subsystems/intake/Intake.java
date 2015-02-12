package com.nutrons.recyclerush.subsystems.intake;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Intake extends Subsystem {
    
	DebouncedBoolean isStackable = new DebouncedBoolean(5);
	
	Talon floorIntakeMotor = new Talon(RobotMap.INTAKE_MOTOR);
	Talon wintakeMotor = new Talon(RobotMap.WINTAKE_MOTOR);
	Solenoid leftIntakeWheelPiston = new Solenoid(RobotMap.LEFT_INTAKE_WHEEL_PISTON);
	Solenoid rightIntakeWheelPiston = new Solenoid(RobotMap.RIGHT_INTAKE_WHEEL_PISTON);
	
	DigitalInput isStackableButton = new DigitalInput(RobotMap.STACKABLE_BUTTON);
	
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
    
    public void stopIntakeMotor() {
    	floorIntakeMotor.set(0);
    }
    
    public void stopWintakeMotor() {
    	wintakeMotor.set(0);
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
    
    public boolean isStackable() {
    	isStackable.feed(isStackableButton.get());
    	return isStackable.get();
    }
}