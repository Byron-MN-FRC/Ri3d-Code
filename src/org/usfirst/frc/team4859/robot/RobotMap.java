/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4859.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Motors
	public static int talonIDChassisFrontLeft = 1; 
	public static int talonIDChassisFrontRight = 2;
	public static int talonIDChassisBackLeft = 3;
	public static int talonIDChassisBackRight = 4;
	public static int aquisitonmotor1 = 5;
	public static int aquisitionmotor2 = 6;
	
	//Pneumatics
	public static int armUp = 0;
	public static int armDown = 1;
	public static int armForward = 2;
	public static int armBackward = 3;
	public static int armStart = 4;
	public static int armReady = 5;
	
	//Robot management
	public static double xAxisScale = 1;
	public static double yAxisScale = 1;
	public static double twistScale = 1;
	public static boolean pMode = false;
	public static boolean fMode = false; 
	
	//Arm Position
	public static boolean isTiltedForward = false;
	public static boolean isArmUp = false;
	public static boolean isArmReady = false;

	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
