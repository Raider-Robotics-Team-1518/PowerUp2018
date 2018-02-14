package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class Auto2 extends Command{
	Autonomous auto = new Autonomous();
	boolean taskDone = false;

		//FMS Code
	String fmscode = DriverStation.getInstance().getGameSpecificMessage();
	
	
	public Auto2() {
		// TODO Auto-generated constructor stub
	}
	protected void execute() {
		System.out.println("Starting Auto 2");
		taskDone = false;
		auto.closeClaw();
		auto.rotateOut();
		auto.driveforward(40);
		if(fmscode.charAt(0) == 'L') {
			//left side code
			auto.strafeleft(70);
		}
		
		else {
			//right side code
			auto.straferight(70);
		}

		auto.liftUp(19);
		auto.driveforward(61.25);
		auto.openClaw();
		end();
		
	}
	
	protected void end(){
		System.out.println("Auto Mode 2 Completed");
		stop();
	}

	protected void interrupted() {
		stop();
		System.out.println("Auto Mode 2 Interrupted");
	}

    public void stop() {
		System.out.println("Auto Mode 2 Stopped");
    	Robot.m_drive.driveCartesian(0, 0, 0);
    	taskDone = true;
    	
    }
    

	@Override
	protected boolean isFinished() {
		System.out.println("Auto Mode 2 isFinished");
		return taskDone;
	}

}
