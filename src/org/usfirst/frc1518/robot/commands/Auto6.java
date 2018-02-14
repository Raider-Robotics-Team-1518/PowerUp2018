package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto6 extends Command{
	Autonomous auto = new Autonomous();
	boolean taskDone = false;

		//FMS Code
	String fmscode = DriverStation.getInstance().getGameSpecificMessage();
	
	
	public Auto6() {
		// TODO Auto-generated constructor stub
	}
	protected void execute() {
		System.out.println("Starting Auto 6");
		taskDone = false;
		if (fmscode.charAt(1) == 'L') {
			auto.closeClaw();
			auto.rotateOut();
			auto.driveforward(40);
			auto.strafeleft(99.7);
			auto.driveforward(209.25);
			auto.liftUp(72);
			auto.straferight(18.9);
			auto.driveforward(11.6);
			auto.openClaw();
		}
		
		else {
			auto.driveforward(132);
		}
		
		end();
		
	}
	
	protected void end(){
		System.out.println("Auto Mode 6 Completed");
		stop();
	}

	protected void interrupted() {
		stop();
		System.out.println("Auto Mode 6 Interrupted");
	}

    public void stop() {
		System.out.println("Auto Mode 6 Stopped");
    	Robot.m_drive.driveCartesian(0, 0, 0);
    	taskDone = true;
    	
    }
    
@Override
	protected boolean isFinished() {
		System.out.println("Auto Mode 6 isFinished");
		return taskDone;
	}

}
