package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.RobotMap;
import org.usfirst.frc1518.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto1 extends Command {
	Autonomous auto = new Autonomous();
	boolean taskDone = false;
	String fmscode = DriverStation.getInstance().getGameSpecificMessage();

	public Auto1() {
		
	}
	
	protected void execute() {
		System.out.println("Starting Test Drive");
		taskDone = false;
		taskDone = false;
		auto.rotateOut();
		auto.driveForward(12);
		Timer.delay(.5);
		fmscode = DriverStation.getInstance().getGameSpecificMessage().toString();
		if (fmscode.length() > 0) {
			if(fmscode.charAt(0) == 'L') {
				//left side code
				auto.strafeRight(70);
			}
		
			else {
				//right side code
				auto.strafeLeft(70);
			}
		}
		else {
			auto.strafeLeft(70);
		}
	
		Timer.delay(.5);
		auto.driveForward(60);
		Timer.delay(.5);
		end();
		
	}
	
	protected void end(){
		System.out.println("Test Drive Completed");
		stop();
	}

	protected void interrupted() {
		stop();
		System.out.println("Test Drive Interrupted");
	}

    public void stop() {
		System.out.println("Test Drive Stopped");
    	Robot.m_drive.driveCartesian(0, 0, 0);
    	taskDone = true;
    	
    }
    
	

	@Override
	protected boolean isFinished() {
		System.out.println("Test Drive isFinished");
		return taskDone;
		
	}

}
