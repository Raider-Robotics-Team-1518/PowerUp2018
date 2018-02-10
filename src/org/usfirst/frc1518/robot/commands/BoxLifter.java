package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

public class BoxLifter extends Command {
	
	WPI_TalonSRX lifterMotor = Robot.rm.lift;
	DigitalInput TBS = Robot.rm.TopBoxSwitch;
	//DigitalInput BBS = Robot.rm.BottomBoxSwitch;
	boolean mDir;
	
	public BoxLifter(boolean motorDir) {
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		if (mDir == true) {
				Robot.rm.lift.set(1);
		}
		
		if (mDir == false) {
				Robot.rm.lift.set(-1);
		}
	}
	protected void end() {
		Robot.rm.lift.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
