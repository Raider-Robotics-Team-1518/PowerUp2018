package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.OI;
import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BoxLifter extends Command {
	DigitalInput BS = Robot.rm.BoxSwitch;;
	boolean mDir;
	boolean switchState = false;
	
	int maxcount = 144; // maximum # of time reed switch can be hit
	//int maxcount = 72; // maximum # of time reed switch can be hit
	int mincount = 1; // minimum # of times reed switch can be hit

	public BoxLifter(boolean motorDir) {
		// TODO Auto-generated constructor stub
		mDir = motorDir;
	}

	protected void execute() {
		SmartDashboard.putNumber("BoxLiftCounter", Robot.boxSwitch);
		if (Robot.isTestBot == true) {
			if ((mDir == true)  && (Robot.boxSwitch <= maxcount)) {
				Robot.rm.testLift.set(1);
				if (HasSwitchChanged() ) {
					Robot.boxSwitch++;
				}
			}
			else if ((mDir == false) && (Robot.boxSwitch <= mincount)) {
				Robot.rm.testLift.set(-1);
				if (HasSwitchChanged()) {
					Robot.boxSwitch--;
				}
			}
			else {
			Robot.rm.lift.set(0);
			}
		}
		else {
			if ((mDir == true)  && (Robot.boxSwitch <= maxcount)) {
				Robot.rm.lift.set(1);
				if (HasSwitchChanged() ) {
					Robot.boxSwitch++;
				}
			}
			else if ((mDir == false) && (Robot.boxSwitch <= mincount)) {
				Robot.rm.lift.set(-1);
				if (HasSwitchChanged()) {
					Robot.boxSwitch--;
				}
			}
			else {
			Robot.rm.lift.set(0);
			}
		}
	}

	protected void end() {
		Robot.rm.lift.set(0);
		Robot.rm.testLift.set(0);
	}
	protected boolean HasSwitchChanged() {
		if (switchState == Robot.rm.BoxSwitch.get()) {
			return false;
		}
		else { 
			switchState = Robot.rm.BoxSwitch.get();
			return true;
		}

		
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
