// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public class ShooterConstants {

  //==============DutyCycles====================
  public static final double kTurretZeroDutyCycle=0;
  public static final double kKickupZeroDutyCycle=0;
  public static final double kSpindexerZeroDutyCycle=0;
  public static final double kShooterZeroDutyCycle=0;

  //==============Positions=================
  public static final double kTurretZeroPosition=0;
  public static final double kTurretSlowShootPosition=0;
  public static final double kTurretClimbPosition=0;

  //===============Speeds===================
  public static final double kKickUpForward=0;
  public static final double kKickUpReverse=0;

  public static final double kSpindexerForward=0;
  public static final double kSpindexerReverse=0;

  public static final double kSpindexerStoppedVelocityTolerance = 0;
  public static final double kKickupStoppedVelocityTolerance = 0;

  public static final double kSlowShoot = 0;
  //===============Safeties==================
  public static final double kHoodSafeExtend = 0;
  public static final double kHoodSafeRetract = 0;
  
  public static final double kTurretSafeClockwise = 0;
  public static final double kTurretSafeCounterClockwise = 0;

  public static final double kShooterMaxSpeed = 0;
  public static final double kShooterMinSpeed = 0;

  public static final double kKickupMaxSpeed = 0;
  public static final double kKickupMinSpeed = 0;

  //=================Misc========================
    public static final int kTurretZeroCurrentLimit=0;

}
