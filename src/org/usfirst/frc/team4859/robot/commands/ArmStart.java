package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmStart extends Command {
	public double time;
    public ArmStart(double inputtime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	time = inputtime;
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.startingPosition();
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.startingPosition();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();   
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.startingPosition();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.arm.startingPosition();
    }
}
