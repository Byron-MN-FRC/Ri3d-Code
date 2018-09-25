/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import org.usfirst.frc.team4859.robot.ThrottleLookup.ThrottleLookup;
import org.usfirst.frc.team4859.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Chassis extends Subsystem 
{
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public static WPI_TalonSRX motorChassisFrontLeft = new WPI_TalonSRX(RobotMap.talonIDChassisFrontLeft);
	public static WPI_TalonSRX motorChassisFrontRight = new WPI_TalonSRX(RobotMap.talonIDChassisFrontRight);
	
	public static WPI_TalonSRX motorChassisBackLeft = new WPI_TalonSRX(RobotMap.talonIDChassisBackLeft);
	public static WPI_TalonSRX motorChassisBackRight = new WPI_TalonSRX(RobotMap.talonIDChassisBackRight);

	public static RobotDrive chassisDrive = new RobotDrive(motorChassisFrontLeft, motorChassisBackLeft, motorChassisFrontRight, motorChassisBackRight);
	
	public static double joyAngle = 0;
	public static double error = 0;
	public static double errorPercent = 0;
	
	public Chassis() 
	{	
		chassisDrive.setSafetyEnabled(true);
		chassisDrive.setExpiration(3);
	}
	public void initDefaultCommand () 
	{
		setDefaultCommand(new DriveWithJoystick());
	}
	public void driveWithJoystick(Joystick joystickP0) {
		double y = joystickP0.getY();
		double x = joystickP0.getX();
		double twist = joystickP0.getTwist();
		
		// Apply translations to the values from the controller
		y = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowY", y) : ThrottleLookup.calcJoystickCorrection("NormY", y);
		x = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowX", x) : ThrottleLookup.calcJoystickCorrection("NormX", x);
		twist = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowT", twist) : ThrottleLookup.calcJoystickCorrection("NormT", twist);
		
		// Apply flip if the flip button is toggled
		if (RobotMap.fMode) {
			double inty = y;
			double intx = x;
			y = intx;
			x = inty;
		}
		
		// Final joystick value adjustments
		x *= RobotMap.xAxisScale;
		y *= RobotMap.yAxisScale;
		twist *= RobotMap.twistScale;
	/*	
		// X variable correction
		boolean isYneg = y < 0;
		boolean isXneg = x < 0;
		boolean isY0 = y == 0;
		boolean isX0 = x == 0;
		
		//moving quadrants
		if(isY0 && isX0 && !isYneg && !isXneg) {
			joyAngle = 0;
		}else if(isY0 && !isX0 && !isYneg && isXneg) {
			joyAngle = 180;
		}else if(isY0 && !isX0 && !isYneg && !isXneg) {
			joyAngle = 360;
		}else if(!isY0 && isX0 && !isYneg && !isXneg) {
			joyAngle = 90;
		}else if(!isY0 && isX0 && isYneg && !isXneg) {
			joyAngle = 270;
		}else if(!isY0 && !isX0 && !isYneg && !isXneg) {
			joyAngle = Math.atan(y/x);
		}else if(!isY0 && !isX0 && isYneg && isXneg) {
			joyAngle = Math.atan(y/x);
			joyAngle += 180;
		}else if(!isY0 && !isX0 && isYneg && !isXneg) {
			joyAngle = Math.atan(y/x);
			joyAngle += 360;
		}else if(!isY0 && !isX0 && !isYneg && isXneg) {
			joyAngle = Math.atan(y/x);
			joyAngle += 180;
		}else {
			joyAngle = 0;
			SmartDashboard.putString("Error", "Error, invalid joystick output");
		}
		SmartDashboard.putNumber("Joystick Angle", joyAngle);
		
		error = joyAngle-Robot.accelAngle;
		errorPercent = error/joyAngle;
	
		x -= x*(0.5*errorPercent); 
		y += y*(0.5*errorPercent);
		*/
		SmartDashboard.putString("Robot Mode", (RobotMap.pMode) ? "Slow" : "Normal");	
		
		chassisDrive.mecanumDrive_Cartesian(x, y, twist, 0);
	}
	public void driveForward (double speed) {
		motorChassisFrontRight.set(speed);
		motorChassisFrontLeft.set(speed);
		motorChassisBackRight.set(speed);
		motorChassisBackLeft.set(speed);
	}
	public void stop () {
		motorChassisFrontRight.set(0);
		motorChassisFrontLeft.set(0);
		motorChassisBackRight.set(0);
		motorChassisBackLeft.set(0);
	}
}