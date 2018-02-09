package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.OI;
import org.usfirst.frc1518.robot.RobotMap;
import org.usfirst.frc1518.robot.subsystems.Launcher;

import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {

	public Shoot() {
	
	}

	public Shoot(String name) {
		super(name);
	}

	public Shoot(double timeout) {
		super(timeout);
	}

	public Shoot(String name, double timeout) {
		super(name, timeout);
	}

	protected void execute(){
		Launcher.shooter.set(1);
		//Launcher.shooter.set(-1);
		//Feeder.feeder.set(-0.8);
		
	}

    // Called once after isFinished returns true
    protected void end() {
    	Launcher.shooter.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Launcher.shooter.set(0);
    }

	@Override
	protected boolean isFinished() {
		return false;
	}

}
