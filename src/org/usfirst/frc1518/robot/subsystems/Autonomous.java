/**
 * 
 */
package org.usfirst.frc1518.robot.subsystems;

import org.usfirst.frc1518.robot.Robot;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author USX27182
 *
 */
public class Autonomous extends Subsystem {

	/**
	 * 
	 */
	double circumferenceInInches = 28.5;
	int pulsesPerRotation = 360;
	//public static RobotDrive drive;
	double distanceToTravel = 0;
	double startPosition = 0;
	double currentAngle = 0;
	double currentPosition = 0;
	double targetPulseCount = 0;
	double targetPosition = 0;
	double drivePower = 0;

	
	public Autonomous() {
		// TODO Auto-generated constructor stub
	}

	
    protected boolean hasDrivenFarEnough(double startPos, double distance) {
		currentPosition = ((Robot.rm.encoderLRear.get() + Robot.rm.encoderRRear.get()) / 2);
		targetPulseCount = (distance / circumferenceInInches) * pulsesPerRotation;
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

   
    protected boolean strafeFarEnough(double startPos, double distance) {
		currentPosition = ((Math.abs(Robot.rm.encoderLRear.getRaw()) + Math.abs(Robot.rm.encoderRRear.getRaw())) / 2);
		targetPulseCount = distance / circumferenceInInches * pulsesPerRotation *2;
		targetPosition = startPos + targetPulseCount;
		//System.out.println("Current Position: " + String.valueOf(currentPosition));
		//System.out.println("Target Position: " + String.valueOf(targetPulseCount));
		if (distance > 0) { // Driving RIGHT
			currentPosition = ((Math.abs(Robot.rm.encoderLRear.getRaw()) + Math.abs(Robot.rm.encoderRRear.getRaw())) / 2);
			if (currentPosition >= targetPosition) {
				return true;
			}
			else{
				return false;
			}
		}
		else { // Driving LEFT
			currentPosition = - ((Math.abs(Robot.rm.encoderLRear.getRaw()) + Math.abs(Robot.rm.encoderRRear.getRaw())) / 2);
			if (currentPosition <= targetPosition) {
				return true;
			}
			else {
				return false;
			}
		}
	}    

    protected boolean gyroTurn(double targetAngle) {
		Robot.rm.rioGyro.reset();
		while ((RobotState.isAutonomous() == true) && (Math.abs(readGyro()) < Math.abs(targetAngle)) && (Math.abs(calcP(targetAngle)) > 0.22)) {
			Robot.m_drive.driveCartesian(0, 0, calcP(targetAngle));//(0, calcP(targetAngle));
		}
		stop();	
		return true;
	}
    
	protected boolean gyroDrive(double distance) {
		Robot.rm.rioGyro.reset();
		Robot.rm.encoderLRear.reset();
		Robot.rm.encoderRRear.reset();
		startPosition = 0; //((Robot.rm.encoderLRear.get() + Robot.rm.encoderRRear.get()) / 2);
		double targetPosition = (distance / circumferenceInInches * pulsesPerRotation);
		while (hasDrivenFarEnough(startPosition, distance) == false) {
			SmartDashboard.putNumber("Left Encoder Count", Robot.rm.encoderLRear.getRaw());
	    	SmartDashboard.putNumber("Right Encoder Count", Robot.rm.encoderRRear.getRaw());
			double drift = readGyro() / 10;
			if (distance > 0) {
				Robot.m_drive.driveCartesian(0, 0.3, -drift);  // FORWARD
			}
			else {
				Robot.m_drive.driveCartesian(0, -0.3, -drift);  // REVERSE
			}
			//System.out.println("Gyro Heading: " + drift);
		}
		stop();
		return true;
	}
	
	protected boolean strafeDrive(double distance) {
		Robot.rm.rioGyro.reset();
		Robot.rm.encoderLRear.reset();
		Robot.rm.encoderRRear.reset();
		startPosition = ((Robot.rm.encoderLRear.get() + Robot.rm.encoderRRear.get()) / 2);
		while (strafeFarEnough(startPosition, distance) == false) {
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
	public void openClaw() {
		Robot.rm.solenoid0.set(true);
		Robot.rm.solenoid1.set(false);
	}
	
	public void closeClaw() {
		Robot.rm.solenoid0.set(false);
		Robot.rm.solenoid1.set(true);
	}
	
	public void rotateIn() {
		Robot.rm.solenoid2.set(true);
		Robot.rm.solenoid3.set(false);
	}
	
	public void rotateOut() {
		Robot.rm.solenoid2.set(false);
		Robot.rm.solenoid3.set(true);
	}
		//Terms for Lift
	public void liftUp(double height) {
		Robot.rm.lift.set(.5);
	}
	
	public void liftDown(double height) {
		Robot.rm.lift.set(-.5);
	}
	
		//Drive Directions
	public void driveforward(double distance) {
		gyroDrive(distance);
	}
	
	public void drivebackward(double distance) {
		gyroDrive(-distance);
	}
	
	public void strafeleft(double distance) {
		strafeDrive(-distance);
	}
	
	public void straferight(double distance) {
		strafeDrive(distance);
	}
	
	public void turnleft(double degrees) {
		gyroTurn(-degrees);
	}
	
	public void turnright(double degrees) {
		gyroTurn(degrees);
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
	
	public void stop() {

    	Robot.m_drive.driveCartesian(0, 0, 0);
    	//taskDone = true;
    	
    }


	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
