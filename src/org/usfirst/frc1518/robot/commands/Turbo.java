package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Turbo extends Command {

	
	public Turbo() {
		
	}
	protected void execute() {
		Robot.turbo = true;
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		Robot.turbo = false;
		return false;
	}

}
