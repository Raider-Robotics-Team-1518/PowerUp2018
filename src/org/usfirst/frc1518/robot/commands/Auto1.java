package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;
import org.usfirst.frc1518.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Auto1 extends Command {
	double circumferenceInInches = 13.6;
	int pulsesPerRotation = 42;
	//public static RobotDrive drive;
	double distanceToTravel = 0;
	double startPosition = 0;
	double currentAngle = 0;
	double currentPosition = 0;
	double targetPulseCount = 0;
	double targetPosition = 0;
	double drivePower = 0;
	boolean taskDone = false;
	
	public Auto1() {
		
		//drive = new RobotDrive(RobotMap.driveTrainRearLeftWheel, RobotMap.driveTrainFrontRightWheel);
	}
	
	protected void execute() {
		
		//System.out.println("Starting Auto 1");
		taskDone = false;
		int aTimer = 0;
		while(aTimer <= 10000)
		{
		Robot.m_drive.driveCartesian(0.0, 0.4, 0.0);
		aTimer++;
		}
		Robot.m_drive.driveCartesian(0, 0, 0);
		Timer.delay(0.25);
		gyroTurn(90);
		aTimer = 0;
		while(aTimer <= 10000)
		{
		Robot.m_drive.driveCartesian(0.0, 0.4, 0.0);
		aTimer++;
		}
		Robot.m_drive.driveCartesian(0, 0, 0);
		Timer.delay(0.25);
		gyroTurn(90);
		aTimer = 0;
		while(aTimer <= 10000)
		{
		Robot.m_drive.driveCartesian(0.0, 0.4, 0.0);
		aTimer++;
		}
		Robot.m_drive.driveCartesian(0, 0, 0);
		Timer.delay(0.25);
		gyroTurn(90);
		aTimer = 0;
		while(aTimer <= 10000)
		{
		Robot.m_drive.driveCartesian(0.0, 0.4, 0.0);
		aTimer++;
		}
		Robot.m_drive.driveCartesian(0, 0, 0);
		Timer.delay(0.25);
		gyroTurn(90);

		end();
		
	}
	
	protected void end(){
		System.out.println("Auto Mode 1 Completed");
		stop();
	}

	protected void interrupted() {
		stop();
		System.out.println("Auto Mode 1 Interrupted");
	}

    public void stop() {
		System.out.println("Auto Mode 1 Stopped");
    	Robot.m_drive.stopMotor();
    	taskDone = true;
    	
    }
    
    public boolean hasDrivenFarEnough(double startPos, double distance) {
		//currentPosition = -1 * RobotMap.driveTrainRearLeftWheel.getEncPosition();
		targetPulseCount = distance / circumferenceInInches * pulsesPerRotation;
		targetPosition = startPos + targetPulseCount;
		//System.out.println("Current Position: " + String.valueOf(currentPosition));
		//System.out.println("Target Position: " + String.valueOf(targetPulseCount));
		if (RobotState.isAutonomous() == true) {
		if (distance > 0) { // Driving FORWARD
			if (currentPosition >= targetPosition) {
				return true;
			}
			else{
				return false;
			}
		}
		else { // Driving REVERSE
			if (currentPosition <= targetPosition) {
				return true;
			}
			else {
				return false;
			}
		}
		}
		else { return true;}
	}

   
    public boolean drivenFarEnough(double distance) {
		//currentPosition = -1 * RobotMap.driveTrainRearLeftWheel.getEncPosition();
    	double yVal = Robot.rm.rioAccel.getY();
    	double xVal = Robot.rm.rioAccel.getX();
    	double zVal = Robot.rm.rioAccel.getZ();
		targetPulseCount = distance / circumferenceInInches * pulsesPerRotation;
		//System.out.println("Current Position: " + String.valueOf(currentPosition));
		//System.out.println("Target Position: " + String.valueOf(targetPulseCount));
		if (currentPosition >= targetPulseCount) {
			return true;
		}
		return false;
	}    
    public boolean gyroTurn(double targetAngle) {
		RobotMap.rioGyro.reset();
			while ((RobotState.isAutonomous() == true) && (Math.abs(readGyro()) < Math.abs(targetAngle)) && (Math.abs(calcP(targetAngle)) > 0.22)) {
				Robot.m_drive.driveCartesian(0, 0, calcP(targetAngle));//(0, calcP(targetAngle));
			}
			stop();	
			return true;
	}
	public boolean gyroDrive(double distance) {
		RobotMap.rioGyro.reset();
		//startPosition = -1 * RobotMap.driveTrainRearLeftWheel.getEncPosition();
		while (hasDrivenFarEnough(startPosition, distance) == false) {
			double drift = readGyro() / 10;
			if (distance > 0) {
			//Robot.driveTrain.drive.arcadeDrive(-0.6, -drift);  // FORWARD
			}
			else {
				//Robot.driveTrain.drive.arcadeDrive(0.6, -drift);  // REVERSE
			}
			System.out.println("Gyro Heading: " + drift);
		}
		stop();
		return true;
	}
	
	protected double readGyro() {
		double angle = RobotMap.rioGyro.getAngle();
		return angle;
	}
	protected double calcP(double tAngle) {
		double p = 0.95 * ((1-(Math.abs(readGyro()) / Math.abs(tAngle))) - 0.05);
		
		if (tAngle > 0) {
			return p;
		}
		else {
			return (p * -1);
		}
	}

	@Override
	protected boolean isFinished() {
		System.out.println("Auto Mode 1 isFinished");
		return taskDone;
	}

}
