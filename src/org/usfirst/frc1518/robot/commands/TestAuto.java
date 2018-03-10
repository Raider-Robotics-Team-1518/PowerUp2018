package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.subsystems.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class TestAuto extends Command {
	Autonomous auto = new Autonomous();
	boolean taskDone = false;
	String fmscode = DriverStation.getInstance().getGameSpecificMessage();

	public TestAuto() {
		// TODO Auto-generated constructor stub
	}

	protected void execute() {
		System.out.println("Starting Test Drive");
		taskDone = false;
		auto.liftUp(2);
		taskDone = true;
		
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return taskDone;
	}

}
