package com.nutrons.recyclerush.subsystems.vision;

import com.ni.vision.NIVision.Image;
import com.nutrons.recyclerush.commands.VisionCmd;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.image.RGBImage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.AxisCamera.ExposureControl;
import edu.wpi.first.wpilibj.vision.AxisCamera.WhiteBalance;

public class Camera extends Subsystem {

    RGBImage frame;
    AxisCamera camera;
    ParticleAnalysisReport report;
	
    // Constants.
    private final int camBrightness = 10;
    private final int camColor = 100;
    public static final int IMAGE_WIDTH = 320;
    public static final int IMAGE_HEIGHT = 240;
    private final WhiteBalance camWhiteBalance = WhiteBalance.kAutomatic;
    private final ExposureControl camExposure = ExposureControl.kAutomatic;
    
	protected void initDefaultCommand() {
		setDefaultCommand(new VisionCmd());
	}
	
	public Camera() {
        camera = new AxisCamera("10.1.25.11");
        camera.writeResolution(AxisCamera.Resolution.k320x240);
        camera.writeBrightness(camBrightness);
        camera.writeColorLevel(camColor);
        camera.writeWhiteBalance(camWhiteBalance);
        camera.writeExposureControl(camExposure);
	}
	
	public void getFeed() {
		if(camera.isFreshImage()) {
			try{
				frame = new RGBImage();
				camera.getImage(frame);
				BinaryImage image = frame.thresholdRGB(0, 40, 25, 255, 0, 40);
				report = image.getParticleAnalysisReport(1);
				SmartDashboard.putNumber("Area of Pixels: ", report.particleArea);
			}catch(Exception ex) {
				ex.printStackTrace(System.out);
			}
		}
	}

}
