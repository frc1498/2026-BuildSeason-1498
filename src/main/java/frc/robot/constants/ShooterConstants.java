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

public static final double kTurretZeroDutyCycle=0.0;
public static final int kTurretZeroCurrentLimit=0;
public static final int kZeroPosition=0;

  //==============Positions=================

  //===============Speeds===================
  public static final double kKickUpForward=0;
  public static final double kKickUpReverse=0;

  public static final double kSpindexerForward=0;
  public static final double kSpindexerReverse=0;

  //===============Safeties==================
  public static final int kHoodSafeExtend = 0;
  public static final int kHoodSafeRetract = 0;
  
  public static final int kTurretSafeClockwise = 0;
  public static final int kTurretSafeCounterClockwise = 0;

  public static final int kShooterMaxSpeed = 0;
  public static final int kShooterMinSpeed = 0;

  public static final int kKickupMaxSpeed = 0;
  public static final int kKickupMinSpeed = 0;

}
