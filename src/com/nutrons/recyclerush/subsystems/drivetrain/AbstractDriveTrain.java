package com.nutrons.recyclerush.subsystems.drivetrain;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.nutrons.recyclerush.commands.DTArcadeCmd;
import com.nutrons.recyclerush.Robot;

/**
 * @author John Zhang
 */
public abstract class AbstractDriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public abstract void DriveTW(double throttle, double wheel);

    
}

