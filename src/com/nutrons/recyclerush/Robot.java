
package com.nutrons.recyclerush;

import com.nutrons.lib.DataLogger;
import com.nutrons.recyclerush.subsystems.drivetrain.DriveTrain;
import com.nutrons.recyclerush.subsystems.elevator.Elevator;
import com.nutrons.recyclerush.subsystems.intake.Intake;
import com.nutrons.recyclerush.subsystems.vision.Camera;

import edu.wpi.first.wpilibj.Compressor;
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
	public static DriveTrain dt;
	public static Elevator elevator;
	public static Camera camera;
	public static Intake intake;
	public static Compressor comp;
	
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
    	intake = new Intake();
		dt = new DriveTrain();
		camera = new Camera();
		elevator = new Elevator();
		
		comp = new Compressor();
    	oi = new OI();
		SmartDashboard.putNumber("dt_kP", 20);
        SmartDashboard.putNumber("Gyro_Constant", Robot.dt.GYRO_CONSTANT);
    }
	
    /**
     * This method is run when the robot is in disabled mode.
     */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	    SmartDashboard.putNumber("Intake_ultrasonic", intake.getUltrasonicDistance());
	    SmartDashboard.putBoolean("is Stackable: ", intake.isStackable());
	}

	/**
	 * This method is run when the robot enters autonomous mode.
	 */
    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        comp.start();
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
        comp.setClosedLoopControl(true);;
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	comp.stop();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        Robot.dt.setGyroConstant(SmartDashboard.getNumber("Gyro_Constant"));
        Robot.dt.kP = SmartDashboard.getNumber("dt_kP");
        Robot.dt.kP_quickturn = SmartDashboard.getNumber("dt_kP");
        SmartDashboard.putNumber("Intake_ultrasonic", intake.getUltrasonicDistance());
        SmartDashboard.putBoolean("fieldCentric", oi.isFieldCentric());
        SmartDashboard.putNumber("dt_kP", Robot.dt.kP);
        SmartDashboard.putNumber("dt_kP_quickturn", Robot.dt.kP_quickturn);
        SmartDashboard.putNumber("Gyro_Constant", Robot.dt.GYRO_CONSTANT);
        SmartDashboard.putNumber("Gyro Rate", Robot.dt.getGyroRate());
        SmartDashboard.putNumber("Left Motor", Robot.dt.getMotorLeftSpeed());
        SmartDashboard.putNumber("Right Motor", Robot.dt.getMotorRightSpeed());
        SmartDashboard.putNumber("Center Motor", Robot.dt.getMotorCenterSpeed());
        SmartDashboard.putNumber("Gyro Angle", Robot.dt.getGyroAngle());
    	SmartDashboard.putNumber("Offset Value: ", Robot.dt.offset);
    	totalCurrentLogger.log(pdp.getTotalCurrent(), timer.getMatchTime());
    	leftMotorCurrentLogger.log(pdp.getCurrent(leftMotorCurrentLogger.getAllPorts().get("motorL")), timer.getMatchTime());
    	SmartDashboard.putBoolean("isAtMinElevator: ", elevator.isAtMinHeight());
    	SmartDashboard.putBoolean("isAtMaxElevator: ", elevator.isAtMaxHeight());
    	SmartDashboard.putBoolean("is Stackable (ready to stack): ", intake.readyToStack);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
