package com.nutrons.recyclerush.subsystems.intake;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.recyclerush.Robot;
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
    
    DigitalInput stackableSensor = new DigitalInput(RobotMap.STACKABLE_SENSOR);
    Solenoid modeSwitcher = new Solenoid(RobotMap.modeSwitcher);
    Talon humanIntake = new Talon(RobotMap.HUMAN_INTAKE);
    Talon floorIntake = new Talon(RobotMap.FLOOR_INTAKE);
    
    DebouncedBoolean stackableDebounced = new DebouncedBoolean(5);
    
    public enum IntakeMode {
    	Tote, RC
    }
    
    IntakeMode currentMode;
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	currentMode = IntakeMode.RC;
    }
    
    public boolean isStackable() {
    	stackableDebounced.feed(stackableSensor.get());
    	return stackableDebounced.get();
    }
    
    public void setHumanIntakePower(double pow) {
    	humanIntake.set(pow);
    }
    
    public void setFloorIntakePower(double pow) {
    	floorIntake.set(pow);
    }
    
    public void changeIntakeMode(IntakeMode mode) {
    	switch(mode) {
    	case Tote:
    		modeSwitcher.set(true);
    		break;
    	case RC:
    		modeSwitcher.set(false);
    		break;
    	default:
    		
    	}
    	this.currentMode = mode;
    }
    
}

