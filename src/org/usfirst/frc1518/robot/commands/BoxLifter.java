package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.OI;
import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;

public class BoxLifter extends Command {
	
	VictorSP lifterMotor = Robot.rm.lift;
	DigitalInput TBS = Robot.rm.TopBoxSwitch;
	DigitalInput BBS = Robot.rm.BottomBoxSwitch;
	boolean mDir;

	public BoxLifter(boolean motorDir) {
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		if (mDir == true) {
			while (OI.liftup.get() && (Robot.rm.TopBoxSwitch.get() == true)) {	
			Robot.rm.lift.set(.75);
			}
		}
		
		if (mDir == false) {
			while (OI.liftdown.get() && (Robot.rm.BottomBoxSwitch.get() == true)) {	
			Robot.rm.lift.set(-.75);
			}
		}
		Robot.rm.lift.set(0);
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
