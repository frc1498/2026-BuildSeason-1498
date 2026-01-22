package frc.robot.constants;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;

/**
 * All constant values used for the vision co-processors.
 * Mainly co-processor names, physical coordinates of their positions on the robot, and camera settings.
 */
public class VisionConstants {

    public static class limelight {
        public static final String kLimelightName = "limelight";
        public static final double kLimelightForwardOffset = 0.34925;   // meters from the center of the robot.
        public static final double kLimelightSideOffset = 0.180975;     // meters from the center of the robot.
        public static final double kLimelightUpOffset = 0.2746375;      // meters from the center of the robot.
        public static final double kLimelightRollOffset = 0.0;          // degrees from vertical.
        public static final double kLimelightPitchOffset = 0.0;         // degrees from vertical.
        public static final double kLimelightYawOffset = 0.0;           // degrees from vertical.
    }
    
    public static class photonVision {
        public static final String kPhotonVisionLeftName = "Left Swerve Camera";
        public static final Transform3d kRobotToLeftCamera = new Transform3d(new Translation3d(0.5, 0.0, 0.5), new Rotation3d(0, 0, 0));

        public static final String kPhotonVisionRightName = "Right Swerve Camera";
        public static final Transform3d kRobotToRightCamera = new Transform3d(new Translation3d(0.5, 0.0, 0.5), new Rotation3d(0, 0, 0));

        public static final AprilTagFieldLayout kTagLayout = AprilTagFieldLayout.loadField(AprilTagFields.k2026RebuiltWelded);  //FiM fields are welded.
    }
}