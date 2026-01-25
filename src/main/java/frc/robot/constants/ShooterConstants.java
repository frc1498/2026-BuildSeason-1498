// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.math.interpolation.InterpolatingTreeMap;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public class ShooterConstants {

  //==============Positions=================

  //===============Speeds===================
  public static final int kKickUpIntake = 0;
  public static final int kKickUpOuttake = 0;

  //===============Safeties==================
  public static final int kHoodSafeExtend = 0;
  public static final int kHoodSafeRetract = 0;
  
  public static final int kTurretSafeClockwise = 0;
  public static final int kTurretSafeCounterClockwise = 0;

  public static final int kShooterMaxSpeed = 0;
  public static final int kShooterMinSpeed = 0;

  public static final int kKickupMaxSpeed = 0;
  public static final int kKickupMinSpeed = 0;

  public static final Pose2d kRedHubCenter = new Pose2d(11.912, 4.028, Rotation2d.fromDegrees(180.0));
  public static final Pose2d kBlueHubCenter = new Pose2d(4.622, 4.028, new Rotation2d(0.0));

  public static final InterpolatingDoubleTreeMap hoodAngleMap = new InterpolatingDoubleTreeMap(); // First column is distance (in meters), second column is angle (in degrees).
  public static final InterpolatingDoubleTreeMap flywheelSpeedMap = new InterpolatingDoubleTreeMap(); // First column is distance (in meters), second column is speed (in rotations per minute).
  public static final InterpolatingDoubleTreeMap timeOfFlightMap = new InterpolatingDoubleTreeMap();  // First column is distance (in meters), second column is time (in seconds).
                                                                                                      // Time of flight is the amount of time that the ball is in the air.

  // None of these values are currently 'real'.  We need theoretical values for testing the algorithm.
  static {
    hoodAngleMap.put(0.5, 75.0);
    hoodAngleMap.put(1.0, 65.0);
    hoodAngleMap.put(1.5, 50.0);
    hoodAngleMap.put(2.0, 45.0);
    hoodAngleMap.put(2.5, 35.0);
    hoodAngleMap.put(3.0, 35.0);
    hoodAngleMap.put(3.5, 35.0);
    hoodAngleMap.put(4.0, 35.0);

    flywheelSpeedMap.put(0.5, 800.0);
    flywheelSpeedMap.put(1.0, 825.0);
    flywheelSpeedMap.put(2.0, 875.0);
    flywheelSpeedMap.put(2.5, 900.0);
    flywheelSpeedMap.put(3.0, 935.0);
    flywheelSpeedMap.put(3.5, 950.0);
    flywheelSpeedMap.put(4.0, 975.0);

    timeOfFlightMap.put(0.5, 0.6);
    timeOfFlightMap.put(1.0, 0.63);
    timeOfFlightMap.put(1.5, 0.71);
    timeOfFlightMap.put(2.0, 0.75);
    timeOfFlightMap.put(2.5, 0.78);
    timeOfFlightMap.put(3.0, 0.84);
    timeOfFlightMap.put(3.5, 0.89);
    timeOfFlightMap.put(4.0, 0.95);
  }
}
