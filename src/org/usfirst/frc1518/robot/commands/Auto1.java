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
	Autonomous autobot = new Autonomous();
	boolean taskDone = false;
	
	public Auto1() {
		
	}
	
	protected void execute() {
		System.out.println("Starting Test Drive");
		taskDone = false;
		autobot.driveforward(24);
		Timer.delay(1);
		autobot.turnright(90);
		Timer.delay(1);
		autobot.driveforward(24);
		Timer.delay(1);
		autobot.turnright(90);
		Timer.delay(1);
		autobot.driveforward(24);
		Timer.delay(1);
		autobot.turnright(90);
		Timer.delay(1);
		autobot.driveforward(24);
		Timer.delay(1);
		autobot.turnright(90);


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
