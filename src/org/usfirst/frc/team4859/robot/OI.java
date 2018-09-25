/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4859.robot;

import org.usfirst.frc.team4859.robot.commands.ArmAssemblyDown;
import org.usfirst.frc.team4859.robot.commands.ArmAssemblyUp;
import org.usfirst.frc.team4859.robot.commands.ArmBackward;
import org.usfirst.frc.team4859.robot.commands.ArmDown;
import org.usfirst.frc.team4859.robot.commands.ArmForward;
import org.usfirst.frc.team4859.robot.commands.ArmReady;
import org.usfirst.frc.team4859.robot.commands.ArmStart;
import org.usfirst.frc.team4859.robot.commands.ArmUp;
import org.usfirst.frc.team4859.robot.commands.EjectCube;
import org.usfirst.frc.team4859.robot.commands.Ejector;
import org.usfirst.frc.team4859.robot.commands.FlipMode;
import org.usfirst.frc.team4859.robot.commands.IntakeCube;
import org.usfirst.frc.team4859.robot.commands.Intaker;
import org.usfirst.frc.team4859.robot.commands.PrecisionMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick joystick = new Joystick(0);
	
	//Buttons
	Button intake = new JoystickButton(joystick, 2);
	Button eject = new JoystickButton(joystick, 3);
	Button flipMode = new JoystickButton(joystick, 1);
	Button precisionMode = new JoystickButton(joystick, 5);
	Button liftUp = new JoystickButton(joystick, 6);
	Button liftDown = new JoystickButton(joystick, 4);
	
	//temp testing buttons
	Button armUptest = new JoystickButton(joystick,10);
	Button armDowntest = new JoystickButton(joystick, 9);
	Button armForwardtest = new JoystickButton(joystick, 12);
	Button armBackwardtest = new JoystickButton(joystick, 11);
	Button armStartingtest= new JoystickButton(joystick, 8);
	Button armReadytest = new JoystickButton (joystick, 7);
	
	public OI() {
	
		//Buttons
		eject.whileHeld(new Ejector());
		intake.whenPressed(new ArmForward(0));
		intake.whileHeld(new Intaker());
		intake.whenReleased(new ArmBackward(0));
		precisionMode.toggleWhenPressed(new PrecisionMode());
		flipMode.toggleWhenPressed(new FlipMode());
		//liftUp.whenPressed(new ArmAssemblyUp(0));
		//liftDown.whenPressed(new ArmAssemblyDown(0));
		//liftUp.whenPressed(new ArmBackward(0));
		liftUp.whenPressed(new ArmUp(0));
		if(RobotMap.isArmReady == true) {
			liftDown.whenReleased(new ArmReady(0));
		}
		//liftDown.whenPressed(new ArmBackward(0));
		liftDown.whenPressed(new ArmDown(0));
		
		//temp testing
		armUptest.whenPressed(new ArmUp(0));
		armDowntest.whenPressed(new ArmDown(0));
		armForwardtest.whenPressed(new ArmForward(0));
		armBackwardtest.whenPressed(new ArmBackward(0));
		armStartingtest.whenPressed(new ArmStart(0));
		armReadytest.whenPressed(new ArmReady(0));

	}
	
	public Joystick getJoystick() {
		return joystick;
	}
	

	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
