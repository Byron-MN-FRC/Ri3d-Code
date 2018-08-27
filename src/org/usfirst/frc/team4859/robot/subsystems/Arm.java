package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	
	DoubleSolenoid arm = new DoubleSolenoid(RobotMap.armDown, RobotMap.armUp);
	DoubleSolenoid tilt = new DoubleSolenoid(RobotMap.armForward, RobotMap.armBackward);
	DoubleSolenoid arm2= new DoubleSolenoid(RobotMap.armStart, RobotMap.armReady);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void armUp() {
    	arm.set(Value.kReverse);
    	RobotMap.isArmUp = true;
    	
    }
    public void armDown() {
    	if(RobotMap.isArmReady) {
    		arm.set(Value.kForward);
    		RobotMap.isArmUp = false;
    	}
    }
  	public void tiltForward() {
  		tilt.set(Value.kForward);
  		RobotMap.isTiltedForward = true;
  	}
  	public void tiltBackward() {
  		tilt.set(Value.kReverse);
  		RobotMap.isTiltedForward = false;
  	}
  	public void startingPosition() {
  		if(RobotMap.isArmUp) {
  			arm2.set(Value.kReverse);
  			RobotMap.isArmReady = false;
  		}
  	}
  	public void robotReady() {
  		arm2.set(Value.kForward);
  		RobotMap.isArmReady = true;
  	}
}

