package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmUp extends Command {
	
	private double time;
	
    public ArmUp(double inputTime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
        time = inputTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.armUp();
    	setTimeout(time);
    	RobotMap.isArmUp = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.armUp();
    	RobotMap.isArmUp = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();    
    	}

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.armUp();
    	RobotMap.isArmUp = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.arm.armUp();
    	RobotMap.isArmUp = true;
    }
}
