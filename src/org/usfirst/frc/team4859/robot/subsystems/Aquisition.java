package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Aquisition extends Subsystem {
	public static WPI_TalonSRX leftmotor = new WPI_TalonSRX(RobotMap.aquisitonmotor1);
	public static WPI_TalonSRX rightmotor = new WPI_TalonSRX(RobotMap.aquisitionmotor2);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void eject (double speed) {
    
    	leftmotor.set(speed);
    	rightmotor.set(-speed);
    }
    public void intake (double speed) {
    
    	leftmotor.set(-speed);
    	rightmotor.set(speed);
    	
    }
    public void stop () {
    	leftmotor.set(0);
    	rightmotor.set(0);

    }
}

