package com.nutrons.recyclerush.subsystems.vision;

import com.nutrons.recyclerush.commands.vision.VisionCmd;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.AxisCamera.ExposureControl;
import edu.wpi.first.wpilibj.vision.AxisCamera.WhiteBalance;

public class Camera extends Subsystem {

    ColorImage frame;
    AxisCamera camera;
	
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
				frame = camera.getImage();
				Timer.delay(0.05);
			}catch(Exception ex) {
				ex.printStackTrace(System.out);
			}
		}
	}

}
