package com.nutrons.lib;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Camilo Gonzalez
 */
public class MultiMotor implements SpeedController, PIDOutput {

    private SpeedController speedControllers[];
    private double output = 0.0;
    
    public MultiMotor(int ports[]) {
        speedControllers = new SpeedController[ports.length];
        for (int i = 0; i < ports.length; i++) {
            speedControllers[i] = new Talon(ports[i]);
        }
    }
    
    public void pidWrite(double output) {
        this.output = output;
         for (int i = 0; i < speedControllers.length; i++) {
            speedControllers[i].pidWrite(output);
        } 
      
    }

    public double get() {
        return output;
    }

    public void set(double speed, byte syncGroup) {
       for (int i = 0; i < speedControllers.length; i++) {
            speedControllers[i].set(output, syncGroup);
        }   
    }

    public void set(double speed) {
         for (int i = 0; i < speedControllers.length; i++) {
            speedControllers[i].set(output);
        } 
    }

    public void disable() {
         for (int i = 0; i < speedControllers.length; i++) {
            speedControllers[i].disable();
        } 
    }

	@Override
	public void setInverted(boolean isInverted) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getInverted() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
