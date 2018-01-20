package org.usfirst.frc1518.robot.subsystems;

import org.usfirst.frc1518.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import edu.wpi.first.wpilibj.CANSpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Launcher extends Subsystem {

    public static final WPI_TalonSRX shooter = RobotMap.shooterMotor;

    public Launcher() {
		// TODO Auto-generated constructor stub

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
