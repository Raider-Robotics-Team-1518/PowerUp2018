package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class LiftCounterReset  extends InstantCommand{
	public static boolean taskDone = false;

	public LiftCounterReset() {
	}

	
	protected void execute() {
		Robot.rm.BoxSwitch.reset();
		taskDone = true;
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return taskDone;
	}

}
