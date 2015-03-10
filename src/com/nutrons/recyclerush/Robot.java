
package com.nutrons.recyclerush;

import com.nutrons.lib.DataLogger;
import com.nutrons.recyclerush.commands.auto.AutoDoNothing;
import com.nutrons.recyclerush.commands.auto.AutoDriveDistanceCmd;
import com.nutrons.recyclerush.commands.auto.AutoDriveForward;
import com.nutrons.recyclerush.commands.auto.AutoDriveTurn;
import com.nutrons.recyclerush.commands.auto.AutoKnockTote;
import com.nutrons.recyclerush.commands.auto.AutoKnockToteAndTurn;
import com.nutrons.recyclerush.commands.auto.AutoThreeTotes;
import com.nutrons.recyclerush.commands.auto.AutoTurnAngleCmd;
import com.nutrons.recyclerush.commands.drivetrain.DriveTurnCmd;
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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	SendableChooser autoChooser = new SendableChooser();
	SendableChooser wintakeSpeedChooser = new SendableChooser();
	
	// commands
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	intake = new Intake();
		dt = new DriveTrain();
		elevator = new Elevator();
		autonomousCommand = new AutoDoNothing();
		
		comp = new Compressor();
    	oi = new OI();
		SmartDashboard.putNumber("dt_kP_straight", 20);
		SmartDashboard.putNumber("dt_kP_quickturn", 1.1);
		SmartDashboard.putNumber("dt_kP_distance", 1);

		SmartDashboard.putNumber("dt_kI_straight", 0);
		SmartDashboard.putNumber("dt_kI_quickturn", 0);
		SmartDashboard.putNumber("dt_kI_distance", 0);

		SmartDashboard.putNumber("dt_kD_straight", 0);
		SmartDashboard.putNumber("dt_kD_quickturn", 0);
		SmartDashboard.putNumber("dt_kD_distance", 0);
		
        SmartDashboard.putNumber("Gyro_Constant", Robot.dt.GYRO_CONSTANT);
        SmartDashboard.putNumber("Encoder_Constant", Robot.dt.ENCODER_CONSTANT);
        Robot.dt.resetEncoders();
        SmartDashboard.putData(Robot.dt);
        
        autoChooser.addDefault("Do Nothing", (Command) new AutoDoNothing());
        autoChooser.addObject("Three Tote", (Command) new AutoThreeTotes());
        autoChooser.addObject("Drive Forward", (Command) new AutoDriveForward());
        autoChooser.addObject("Knock Tote", (Command) new AutoKnockTote());
        autoChooser.addObject("Turn Right", (Command) new AutoTurnAngleCmd(45));
        autoChooser.addObject("Knock Tote and Turn", (Command) new AutoKnockToteAndTurn());
        autoChooser.addObject("Drive and Turn", (Command) new AutoDriveTurn());
        
        wintakeSpeedChooser.addDefault("0.6", 0.6);
        wintakeSpeedChooser.addDefault("0.75", 0.75);
        wintakeSpeedChooser.addDefault("0.9", 0.9);
        SmartDashboard.putData("Auto Chooser", autoChooser);
        Robot.intake.openIntakeWheel();
    }
	
    /**
     * This method is run when the robot is in disabled mode.
     */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
        updateSmartDashboard();
    	autonomousCommand = (Command) autoChooser.getSelected();
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
        updateSmartDashboard();
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
        updateSmartDashboard();
    }
    
    public void updateSmartDashboard() {
    	// set
    	Robot.dt.setGyroConstant(SmartDashboard.getNumber("Gyro_Constant"));
        Robot.dt.setEncoderConstant(SmartDashboard.getNumber("Encoder_Constant"));
        Robot.dt.setQuickturnPIDGains(SmartDashboard.getNumber("dt_kP_quickturn"), SmartDashboard.getNumber("dt_kI_quickturn"), SmartDashboard.getNumber("dt_kD_quickturn"));
        Robot.dt.setDistancePIDGains(SmartDashboard.getNumber("dt_kP_distance"), SmartDashboard.getNumber("dt_kI_distance"), SmartDashboard.getNumber("dt_kD_distance"));
        Robot.dt.setHoldHeadingPIDGains(SmartDashboard.getNumber("dt_kP_straight"), SmartDashboard.getNumber("dt_kI_straight"), SmartDashboard.getNumber("dt_kD_straight"));
        
        // get
        SmartDashboard.putNumber("Intake_ultrasonic", intake.getUltrasonicDistance());
        SmartDashboard.putBoolean("fieldCentric", oi.isFieldCentric());
        SmartDashboard.putNumber("dt_kP_straight", Robot.dt.kP_straight);
        SmartDashboard.putNumber("dt_kP_quickturn", Robot.dt.kP_quickturn);
        SmartDashboard.putNumber("dt_kP_distance", Robot.dt.kP_distance);
        SmartDashboard.putNumber("dt_kI_straight", Robot.dt.kI_straight);
        SmartDashboard.putNumber("dt_kI_quickturn", Robot.dt.kI_quickturn);
        SmartDashboard.putNumber("dt_kI_distance", Robot.dt.kI_distance);
        SmartDashboard.putNumber("dt_kD_straight", Robot.dt.kD_straight);
        SmartDashboard.putNumber("dt_kD_quickturn", Robot.dt.kD_quickturn);
        SmartDashboard.putNumber("dt_kD_distance", Robot.dt.kD_distance);
        SmartDashboard.putNumber("Gyro_Constant", Robot.dt.GYRO_CONSTANT);
        SmartDashboard.putNumber("Encoder_Constant", Robot.dt.ENCODER_CONSTANT);
        SmartDashboard.putNumber("Gyro Rate", Robot.dt.getGyroRate());
        SmartDashboard.putNumber("Left Motor", Robot.dt.getMotorLeftSpeed());
        SmartDashboard.putNumber("Right Motor", Robot.dt.getMotorRightSpeed());
        SmartDashboard.putNumber("Center Motor", Robot.dt.getMotorCenterSpeed());
        SmartDashboard.putNumber("Gyro Angle", Robot.dt.getGyroAngle());
    	SmartDashboard.putNumber("Offset Value: ", Robot.dt.offset);
    	SmartDashboard.putBoolean("isAtMinElevator: ", elevator.isAtMinHeight());
    	SmartDashboard.putBoolean("isAtMaxElevator: ", elevator.isAtMaxHeight());
    	SmartDashboard.putBoolean("is Stackable", intake.readyToStack);
    	SmartDashboard.putNumber("Left Encoder Distance", Robot.dt.getLeftDistance());
    	SmartDashboard.putNumber("RightEncoder Distance", Robot.dt.getRightDistance());
    	
    	// log
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
