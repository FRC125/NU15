
package com.nutrons.recyclerush;

import com.nutrons.lib.DataLogger;
import com.nutrons.recyclerush.subsystems.drivetrain.DriveTrain;
import com.nutrons.recyclerush.subsystems.intake.Intake;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	
	/**
	 *  Subsystems
	 */
	public static DriveTrain dt = new DriveTrain();
	public static Intake intake = new Intake();
	
	//logging objects
	public static DataLogger totalCurrentLogger = new DataLogger("Total Current", 100);
	public static DataLogger leftMotorCurrentLogger = new DataLogger("Left Motor Current", 100);
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static Timer timer = new Timer();
	
	// commands
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		SmartDashboard.putNumber("dt_kP", 20);
        SmartDashboard.putNumber("Gyro_Constant", Robot.dt.GYRO_CONSTANT);
		SmartDashboard.putNumber("Error", 0);
		SmartDashboard.putNumber("Target", 0);
		SmartDashboard.putNumber("Adjust", 0);
        SmartDashboard.putNumber("Ultrasonic Distance", Robot.dt.getUltrasonicDistance());
    }
	
    /**
     * This method is run when the robot is in disabled mode.
     */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This method is run when the robot enters autonomous mode.
	 */
    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This method is called when the robot enters teleoperated mode.
     */
    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
        Robot.dt.zeroGyro();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        Robot.dt.setGyroConstant(SmartDashboard.getNumber("Gyro_Constant"));
        //Robot.dt.quickTurnPID.updateValues();
        Robot.dt.kP = SmartDashboard.getNumber("dt_kP");
        Robot.dt.kP_quickturn = SmartDashboard.getNumber("dt_kP");
        SmartDashboard.putBoolean("fieldCentric", oi.isFieldCentric());
        SmartDashboard.putNumber("dt_kP", Robot.dt.kP);
        SmartDashboard.putNumber("dt_kP_quickturn", Robot.dt.kP_quickturn);
        SmartDashboard.putNumber("Gyro_Constant", Robot.dt.GYRO_CONSTANT);
        SmartDashboard.putNumber("Gyro Rate", Robot.dt.getGyroRate());
        SmartDashboard.putNumber("Left Motor", Robot.dt.getMotorLeftSpeed());
        SmartDashboard.putNumber("Right Motor", Robot.dt.getMotorRightSpeed());
        SmartDashboard.putNumber("Center Motor", Robot.dt.getMotorCenterSpeed());
        SmartDashboard.putNumber("Gyro Angle", Robot.dt.getGyroAngle());
        SmartDashboard.putNumber("Ultrasonic Distance", Robot.dt.getUltrasonicDistance());
    	SmartDashboard.putNumber("Target value PID: ", Robot.dt.getTargetAngle());
    	SmartDashboard.putNumber("Offset Value: ", Robot.dt.offset);
    	SmartDashboard.putNumber("POV", Robot.oi.getPOVDirection());
    	totalCurrentLogger.log(pdp.getTotalCurrent(), timer.getMatchTime());
    	leftMotorCurrentLogger.log(pdp.getCurrent(leftMotorCurrentLogger.getAllPorts().get("motorL")), timer.getMatchTime());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
