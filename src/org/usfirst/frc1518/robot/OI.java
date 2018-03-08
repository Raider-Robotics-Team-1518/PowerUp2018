// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1518.robot;

import org.usfirst.frc1518.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * Mapping buttons - 
 * 	Shooting - Right Trigger
 * 	Intake - Left Trigger
 *  Climb - Left Thumb Low (2)
 *  Unjam - Right Thumb Low (2)
 *  Servos - Right Thumb High (3)
 *  Reverse Controls - Left Thumb High (3)
 */
public class OI {
public static Joystick mainstick;
public static JoystickButton soleButton0;
public static JoystickButton soleButton1;
public static JoystickButton soleButton2;
public static JoystickButton liftUp;
public static JoystickButton liftDown;
public static JoystickButton liftUp2;
public static JoystickButton liftDown2;
//public static JoystickButton fullLiftDown;
public static JoystickButton climbUp;
public static JoystickButton climbDown;
public static JoystickButton turbo;
public static  XboxController gp1;
public static JoystickButton gp1ButtonA;
public static JoystickButton gp1ButtonY;


    public OI() {

    	mainstick = new Joystick(0);
    	liftUp = new JoystickButton(mainstick, 5);
    	liftUp.whileHeld(new BoxLifter(true));	
    	liftDown = new JoystickButton(mainstick, 3);
    	liftDown.whileHeld(new BoxLifter(false));
    	liftUp2 = new JoystickButton(mainstick, 9);
    	liftUp2.whileHeld(new BoxLifter(true));	
    	liftDown2 = new JoystickButton(mainstick, 11);
    	liftDown2.whileHeld(new BoxLifter(false));
    	//fullLiftDown = new JoystickButton(mainstick, 10);
    	//fullLiftDown.whenPressed(new BoxLifter(false));
    	climbUp = new JoystickButton(mainstick, 6);
    	climbUp.whileHeld(new Climber(true));
    	climbDown = new JoystickButton(mainstick, 4);
    	climbDown.whileHeld(new Climber (false));
    	turbo = new JoystickButton(mainstick, 2);
    	soleButton0 = new JoystickButton(mainstick, 7);
    	soleButton0.whenPressed(new AirActuators(0));
    	soleButton1 = new JoystickButton(mainstick, 1);
    	soleButton1.whenPressed(new AirActuators(1));
    	soleButton2 = new JoystickButton(mainstick, 11);
    	soleButton2.whenPressed(new AirActuators(2));
    	gp1 = new XboxController(1);
    	gp1ButtonA = new JoystickButton(gp1, 1);
    	gp1ButtonY = new JoystickButton(gp1, 4);
    	gp1ButtonA.whileHeld(new Climber(false));
    	gp1ButtonY.whileHeld(new Climber(true));
    	
    }
    
    public static void init() {
    	SmartDashboard.putData("Autonomous Command", new Auto1());
        Robot.feedSpeed = mainstick.getThrottle();
    }
}

