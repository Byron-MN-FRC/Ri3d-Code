/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4859.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4859.robot.commands.Driveforward;
import org.usfirst.frc.team4859.robot.subsystems.Aquisition;
import org.usfirst.frc.team4859.robot.subsystems.Arm;
import org.usfirst.frc.team4859.robot.subsystems.Chassis;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final Chassis chassis = new Chassis();
	public static final Aquisition aquisition = new Aquisition();
	public static final Arm arm = new Arm();
	public static OI oi;
	Accelerometer accel;
	double accelX;
	double accelY;
	AnalogGyro gyro;
	public static double accelAngle = 0;
	public static UsbCamera cameraBackward = CameraServer.getInstance().startAutomaticCapture("Backward", 1);
	public static UsbCamera cameraForward = CameraServer.getInstance().startAutomaticCapture("Forward", 0);

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		accel = new BuiltInAccelerometer(); 
		accel = new BuiltInAccelerometer(Accelerometer.Range.k4G); 
		//gyro = new AnalogGyro(1);
	
/*
		arm.tiltBackward();
		arm.armUp();
		arm.startingPosition();
*/
		cameraBackward.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 10);
		cameraBackward.setExposureManual(70);
		cameraForward.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 10);
		cameraForward.setExposureManual(70);
		m_chooser.addDefault("Default Auto", new Driveforward(0.5,3));
		m_chooser.addObject("My Auto", new Driveforward(0.5,3));
		SmartDashboard.putData("Auto mode", m_chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
	/*	arm.tiltBackward();
		arm.armDown();
		arm.robotReady();
		*/
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		accelX = accel.getX();
		accelY = accel.getY();
		SmartDashboard.putNumber("Accelerometer X axis", accelX);
		SmartDashboard.putNumber("Accelerometer X axis", accelY);
		boolean isYneg = accelY < 0;
		boolean isXneg = accelX < 0;
		boolean isY0 = accelY == 0;
		boolean isX0 = accelX == 0;
		
		//moving quadrants
		if(isY0 && isX0 && !isYneg && !isXneg) {
			accelAngle = 0;
		}else if(isY0 && !isX0 && !isYneg && isXneg) {
			accelAngle = 180;
		}else if(isY0 && !isX0 && !isYneg && !isXneg) {
			accelAngle = 360;
		}else if(!isY0 && isX0 && !isYneg && !isXneg) {
			accelAngle = 90;
		}else if(!isY0 && isX0 && isYneg && !isXneg) {
			accelAngle = 270;
		}else if(!isY0 && !isX0 && !isYneg && !isXneg) {
			accelAngle = Math.atan(accelY/accelX);
		}else if(!isY0 && !isX0 && isYneg && isXneg) {
			accelAngle = Math.atan(accelY/accelX);
			accelAngle += 180;
		}else if(!isY0 && !isX0 && isYneg && !isXneg) {
			accelAngle = Math.atan(accelY/accelX);
			accelAngle += 360;
		}else if(!isY0 && !isX0 && !isYneg && isXneg) {
			accelAngle = Math.atan(accelY/accelX);
			accelAngle += 180;
		}else {
			accelAngle = 0;
			SmartDashboard.putString("Error", "Error, invalid accelerometer output");
		}
		SmartDashboard.putNumber("Accelerometer Angle", accelAngle);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
