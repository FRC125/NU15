package com.nutrons.recyclerush.subsystems.intake;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

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

	DoubleSolenoid IntakeWheelPiston = new DoubleSolenoid(RobotMap.LEFT_INTAKE_WHEEL_PISTON, RobotMap.RIGHT_INTAKE_WHEEL_PISTON);

	DoubleSolenoid stackHolderPiston = new DoubleSolenoid(RobotMap.DOUBLE_STACK_HOLDER_A, RobotMap.DOUBLE_STACK_HOLDER_B);
	DoubleSolenoid stackPusherPiston = new DoubleSolenoid(RobotMap.DOUBLE_PUSHER_A, RobotMap.DOUBLE_PUSHER_B);

	
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
    
    public void openIntakeWheel() {
    	IntakeWheelPiston.set(DoubleSolenoid.Value.kForward);;
    }

    public void closeIntakeWheel() {
    	IntakeWheelPiston.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean isStackable() {
    	isStackable.feed(isStackableButton.get());
    	return isStackable.get();
    }
    
    public void deployHolderPistons() {
    	stackHolderPiston.set(Value.kForward);
    }
    
    public void retractHolderPistons() {
    	stackHolderPiston.set(Value.kReverse);
    }
    
    public void deployPusherPiston() {
    	stackPusherPiston.set(Value.kForward);
    }
    
    public void retractPusherPiston() {
    	stackPusherPiston.set(Value.kReverse);
    }
}
