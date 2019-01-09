package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LeftScaleAuto extends Command{
	Autonomous auto = new Autonomous();
	boolean taskDone = false;

		//FMS Code
	String fmscode = DriverStation.getInstance().getGameSpecificMessage();
	
	
	public LeftScaleAuto() {
		// TODO Auto-generated constructor stub
	}
	protected void execute() {
		System.out.println("Starting Auto 5");
		taskDone = false;
		auto.rotateOut();
		Timer.delay(.5);
		fmscode = DriverStation.getInstance().getGameSpecificMessage().toString();
		 if (fmscode.charAt(1) == 'L') {
			 auto.driveAndLift(275,52);
			 Timer.delay(.5);
			 auto.turnRight(90);
			 Timer.delay(1);
			 auto.driveForward(8);
			 Timer.delay(1);
			 auto.openClaw();
		}
		 else {
			 
				if (fmscode.charAt(0) == 'L') {
					auto.driveForward(120);
					Timer.delay(1);
					auto.turnRight(90);
					Timer.delay(1);
					auto.driveForward(17.05);
					Timer.delay(1);
					auto.openClaw();
					Timer.delay(.25);
					auto.rotateIn();
					auto.driveBackward(12);
					Timer.delay(1);
				}
				else {
					auto.driveForward(100);
				}
				
/*//Steps to cross field for scale
			 auto.driveForward(204);
			Timer.delay(1);
			auto.turnRight(90);
			Timer.delay(1);
			auto.driveForward(252);
			Timer.delay(1);
			auto.turnLeft(90);
			Timer.delay(1);
			auto.driveForward(60);
			Timer.delay(1);
			auto.turnLeft(90);
			Timer.delay(1);
			auto.liftUp(12.9);
			Timer.delay(1);
			auto.driveForward(18);
			auto.openClaw();   */
			 
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
