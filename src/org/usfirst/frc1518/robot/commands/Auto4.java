package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto4 extends Command{
	Autonomous auto = new Autonomous();
	boolean taskDone = false;

		//FMS Code
	String fmscode = DriverStation.getInstance().getGameSpecificMessage();
	
	
	public Auto4() {
		// TODO Auto-generated constructor stub
	}
	protected void execute() {
		System.out.println("Starting Auto 4");
		taskDone = false;
		if (fmscode.charAt(0) == 'R') {
			auto.rotateOut();
			auto.driveforward(140);
			Timer.delay(.25);
			auto.turnleft(90);
			Timer.delay(.25);
			auto.driveforward(17.05);
			Timer.delay(.25);
			auto.openClaw();
			Timer.delay(.25);
			auto.drivebackward(6);
			Timer.delay(.25);
			auto.straferight(30);
			Timer.delay(.25);
			auto.turnleft(90);
			Timer.delay(.25);
			auto.strafeleft(24);
		}
		
		else {
			auto.driveforward(132);
		}
		
		end();
		
	}
	
	protected void end(){
		System.out.println("Auto Mode 4 Completed");
		stop();
	}

	protected void interrupted() {
		stop();
		System.out.println("Auto Mode 4 Interrupted");
	}

    public void stop() {
		System.out.println("Auto Mode 4 Stopped");
    	Robot.m_drive.driveCartesian(0, 0, 0);
    	taskDone = true;
    	
    }
    
    @Override
	protected boolean isFinished() {
		System.out.println("Auto Mode 4 isFinished");
		return taskDone;
	}

}
