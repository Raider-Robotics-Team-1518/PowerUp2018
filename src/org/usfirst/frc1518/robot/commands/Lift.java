package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Lift extends Command{

	public Lift() {
		
	}

	protected void execute(){
		if(Robot.isTestBot == false){
		RobotMap.lift.set(1);
		}
		else {
			RobotMap.pwmLift.set(-1);
		}
	}
	
	protected void end(){
		RobotMap.lift.set(0);
		RobotMap.pwmLift.set(0);
	}
	
	protected void interrupted(){
		RobotMap.lift.set(0);
		RobotMap.pwmLift.set(0);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
