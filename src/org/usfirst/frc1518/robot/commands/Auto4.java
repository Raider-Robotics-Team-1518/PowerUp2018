package org.usfirst.frc1518.robot.commands;

import org.usfirst.frc1518.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto4 extends Command{
	double circumferenceInInches = 25.5;
	int pulsesPerRotation = 160;
	//public static RobotDrive drive;
	double distanceToTravel = 0;
	double startPosition = 0;
	double currentAngle = 0;
	double currentPosition = 0;
	double targetPulseCount = 0;
	double targetPosition = 0;
	double drivePower = 0;
	boolean taskDone = false;

		//FMS Code
	String fmscode = DriverStation.getInstance().getGameSpecificMessage();
	
	
	public Auto4() {
		// TODO Auto-generated constructor stub
	}
	protected void execute() {
		System.out.println("Starting Auto 4");
		taskDone = false;
		if (fmscode.charAt(0) == 'R') {
			closeClaw();
			rotateOut();
			liftUp(19);
			driveforward(140);
			turnleft(90);
			driveforward(17.05);
			openClaw();
		}
		
		else {
		driveforward(132);
		}
		
		end();
		
	}
	
	protected void end(){
		System.out.println("Auto Mode 4 Completed");
		stop();
	}

	protected void interrupted() {
		stop();
		System.out.println("Auto Mode 4 Interrupted");
	}

    public void stop() {
		System.out.println("Auto Mode 4 Stopped");
    	Robot.m_drive.driveCartesian(0, 0, 0);
    	taskDone = true;
    	
    }
    
    public boolean hasDrivenFarEnough(double startPos, double distance) {
		currentPosition = ((Robot.rm.encoderLRear.get() + Robot.rm.encoderRRear.get()) / 2);
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
		else {
			return true;
		}
	}

   
    public boolean strafeFarEnough(double distance) {
		currentPosition = ((Math.abs(Robot.rm.encoderLRear.get()) + Math.abs(Robot.rm.encoderRRear.get())) / 2);
		targetPulseCount = distance / circumferenceInInches * pulsesPerRotation *2;
		//System.out.println("Current Position: " + String.valueOf(currentPosition));
		//System.out.println("Target Position: " + String.valueOf(targetPulseCount));
		if (distance > 0) { // Driving RIGHT
			currentPosition = ((Math.abs(Robot.rm.encoderLRear.get()) + Math.abs(Robot.rm.encoderRRear.get())) / 2);
			if (currentPosition >= targetPulseCount) {
				return true;
			}
			else{
				return false;
			}
		}
		else { // Driving LEFT
			currentPosition = - ((Math.abs(Robot.rm.encoderLRear.get()) + Math.abs(Robot.rm.encoderRRear.get())) / 2);
			if (currentPosition <= targetPulseCount) {
				return true;
			}
			else {
				return false;
			}
		}
	}    

    public boolean gyroTurn(double targetAngle) {
		Robot.rm.rioGyro.reset();
		while ((RobotState.isAutonomous() == true) && (Math.abs(readGyro()) < Math.abs(targetAngle)) && (Math.abs(calcP(targetAngle)) > 0.22)) {
			Robot.m_drive.driveCartesian(0, 0, calcP(targetAngle));//(0, calcP(targetAngle));
		}
		stop();	
		return true;
	}
	public boolean gyroDrive(double distance) {
		Robot.rm.rioGyro.reset();
		Robot.rm.encoderLRear.reset();
		Robot.rm.encoderRRear.reset();
		startPosition = 0; // ((Robot.rm.encoderLRear.get() + Robot.rm.encoderRRear.get()) / 2);
		while (hasDrivenFarEnough(startPosition, distance) == false) {
	    	SmartDashboard.putNumber("Left Encoder Count", Robot.rm.encoderLRear.get());
	    	SmartDashboard.putNumber("Right Encoder Count", Robot.rm.encoderRRear.get());
			double drift = readGyro() / 10;
			if (distance > 0) {
				Robot.m_drive.driveCartesian(0, 0.3, -drift);  // FORWARD
			}
			else {
				Robot.m_drive.driveCartesian(0, -0.3, -drift);  // FORWARD
			}
			//System.out.println("Gyro Heading: " + drift);
		}
		stop();
		return true;
	}
	public boolean strafeDrive(double distance) {
		Robot.rm.rioGyro.reset();
		Robot.rm.encoderLRear.reset();
		Robot.rm.encoderRRear.reset();
		startPosition = 0; // ((Robot.rm.encoderLRear.get() + Robot.rm.encoderRRear.get()) / 2);
		while (strafeFarEnough(distance) == false) {
	    	SmartDashboard.putNumber("Left Encoder Count", Robot.rm.encoderLRear.get());
	    	SmartDashboard.putNumber("Right Encoder Count", Robot.rm.encoderRRear.get());
			double drift = readGyro() / 10;
			if (distance > 0) {
				Robot.m_drive.driveCartesian(0.3, 0, -drift);  // RIGHT
			}
			else {
				Robot.m_drive.driveCartesian(-0.3, 0, -drift);  // LEFT
			}
			//System.out.println("Gyro Heading: " + drift);
		}
		
		stop();
		return true;
	}
	
		//Terms For Pneumatics
	protected void openClaw() {
		Robot.rm.solenoid0.set(true);
		Robot.rm.solenoid1.set(false);
	}
	
	protected void closeClaw() {
		Robot.rm.solenoid0.set(false);
		Robot.rm.solenoid1.set(true);
	}
	
	protected void rotateIn() {
		Robot.rm.solenoid2.set(true);
		Robot.rm.solenoid3.set(false);
	}
	
	protected void rotateOut() {
		Robot.rm.solenoid2.set(false);
		Robot.rm.solenoid3.set(true);
	}
		//Terms for Lift
	protected void liftUp(double height) {
		Robot.rm.lift.set(.5);
	}
	
	protected void liftDown(double height) {
		Robot.rm.lift.set(-.5);
	}
	
		//Drive Directions
	protected void driveforward(double distance) {
		gyroDrive(distance);
	}
	
	protected void drivebackward(double distance) {
		gyroDrive(-distance);
	}
	
	protected void strafeleft(double distance) {
		strafeDrive(-distance);
	}
	
	protected void straferight(double distance) {
		strafeDrive(distance);
	}
	
	protected void turnleft(double degrees) {
		turnleft(-degrees);
	}
	
	protected void turnright(double degrees) {
		turnright(degrees);
	}
	
	//--------------------------------------

	protected double readGyro() {
		double angle = Robot.rm.rioGyro.getAngle();
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
		System.out.println("Auto Mode 4 isFinished");
		return taskDone;
	}

}
