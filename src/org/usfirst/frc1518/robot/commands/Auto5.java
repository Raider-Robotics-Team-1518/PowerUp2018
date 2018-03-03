package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto5 extends Command{
	Autonomous auto = new Autonomous();
	boolean taskDone = false;

		//FMS Code
	String fmscode = DriverStation.getInstance().getGameSpecificMessage();
	
	
	public Auto5() {
		// TODO Auto-generated constructor stub
	}
	protected void execute() {
		System.out.println("Starting Auto 5");
		taskDone = false;
		fmscode = DriverStation.getInstance().getGameSpecificMessage().toString();

		if (fmscode.charAt(1) == 'L') {
			auto.closeClaw();
			auto.rotateOut();
			auto.driveforward(40);
			auto.strafeleft(99.7);
			auto.driveforward(209.25);
			auto.liftUp(72);
			auto.straferight(18.9);
			auto.driveforward(11.6);
			Timer.delay(1);
			auto.openClaw();
		}
		
		else {
			auto.driveforward(132);
		}
		
		end();
		
	}
	
	protected void end(){
		System.out.println("Auto Mode 5 Completed");
		stop();
	}

	protected void interrupted() {
		stop();
		System.out.println("Auto Mode 5 Interrupted");
	}

    public void stop() {
		System.out.println("Auto Mode 5 Stopped");
    	Robot.m_drive.driveCartesian(0, 0, 0);
    	taskDone = true;
    	
    }
    
	@Override
	protected boolean isFinished() {
		System.out.println("Auto Mode 5 isFinished");
		return taskDone;
	}

}
