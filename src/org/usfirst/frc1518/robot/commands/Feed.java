package org.usfirst.frc1518.robot.commands;


import org.usfirst.frc1518.robot.OI;
import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class Feed extends Command{

	public Feed() {
		
	}
	
	protected void initialize(){
	}
	
	protected void execute(){
		if(OI.trigger.get() == true){
			RobotMap.intakeMotor.set(0);
		}
		else{
			RobotMap.intakeMotor.set(-1);
		}
	}
		
	


	
	protected void end() {
		RobotMap.intakeMotor.set(0);
	}
	
	protected void interrupted(){
		RobotMap.intakeMotor.set(0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
