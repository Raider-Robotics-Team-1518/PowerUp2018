package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.OI;
import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;

public class Climber extends Command {
	
	WPI_TalonSRX climbMotor = Robot.rm.climb;
	boolean mDir;
	
	public Climber(boolean motorDir) {
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		if ((mDir == true) && (Robot.rm.ClimbSwitch.get() == true)) {
			Robot.rm.climb.set(1);
		}

		else if ((mDir == false) && (Robot.rm.ClimbSwitch.get() == true)) {
			Robot.rm.climb.set(-1);
		}
		else {
		Robot.rm.climb.set(0);
		}
	}
	protected void end() {
		Robot.rm.climb.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
