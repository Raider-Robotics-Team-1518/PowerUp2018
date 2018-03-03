package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
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
		auto.rotateOut();
		auto.driveForward(12);
		Timer.delay(.5);
		fmscode = DriverStation.getInstance().getGameSpecificMessage().toString();
		if (fmscode.length() > 0) {
			if(fmscode.charAt(0) == 'L') {
				//left side code
				auto.strafeLeft(70);
			}
		
			else {
				//right side code
				auto.strafeRight(70);
			}
		}
		else {
			auto.strafeLeft(70);
		}
	
		Timer.delay(.5);
<<<<<<< HEAD
		auto.driveForward(61.25);
		Timer.delay(.5);
=======
		auto.driveforward(60);
		Timer.delay(1.5);
>>>>>>> master
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
