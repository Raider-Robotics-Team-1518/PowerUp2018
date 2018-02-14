package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
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
			auto.closeClaw();
			auto.rotateOut();
			auto.liftUp(19);
			auto.driveforward(140);
			auto.turnleft(90);
			auto.driveforward(17.05);
			auto.openClaw();
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
