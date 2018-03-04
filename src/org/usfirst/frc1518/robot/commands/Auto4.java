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
		System.out.println("FMS code " + fmscode);
		taskDone = false;
		auto.rotateOut();
		fmscode = DriverStation.getInstance().getGameSpecificMessage().toString();
		if (fmscode.charAt(0) == 'R') {
			System.out.println("FMS code " + fmscode.charAt(0));
			auto.driveForward(120);
			Timer.delay(1);
			auto.turnLeft(90);
			Timer.delay(1);
			auto.driveForward(6);
			Timer.delay(1);
			auto.openClaw();
			Timer.delay(.25);
			auto.rotateIn();
			auto.driveBackward(12);
			Timer.delay(1);
			auto.turnRight(90);
			Timer.delay(1);
			auto.driveForward(36);
			Timer.delay(1);
			auto.turnLeft(90);
			Timer.delay(1);
			auto.driveForward(28);
			Timer.delay(1);
			auto.turnLeft(85);
			auto.liftDown(24);
			auto.rotateOut();
			/*auto.straferight(62);
			Timer.delay(1);
			auto.turnleft(90);
			Timer.delay(1);
			auto.straferight(28);*/
		}
		
		else if (fmscode.charAt(1) == 'R') {
		System.out.println("FMS code " + fmscode.charAt(1));
		auto.driveAndLift(120,52);
		Timer.delay(.5);
		auto.turnLeft(90);
		Timer.delay(1);
		auto.driveForward(8);
		Timer.delay(1);
		auto.openClaw();
		}
		
		else {
		auto.driveForward(132);
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
