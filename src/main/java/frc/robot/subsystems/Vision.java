package frc.robot.subsystems;

import java.util.function.Supplier;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.PoseEstimate;
import frc.robot.constants.VisionConstants;

public class Vision extends SubsystemBase {
    
    public LimelightHelpers.PoseEstimate megaTag2 = new PoseEstimate();
    public Supplier<Pigeon2> drivetrainState;
    public CommandSwerveDrivetrain drivetrain;
    public Field2d limelightField = new Field2d();
    
    private LimelightHelpers.PoseEstimate cachedMegaTag2 = new PoseEstimate();
    private double cachedRobotHeading = 0.0;
    private double cachedRobotRotationRate = 0.0;
    private boolean cachedMegaTagValid = false;
    private boolean cachedAreTagsSeen = false;
    private boolean cachedIsRobotSlowEnough = false;
    private boolean cachedIsPoseValid = false;
    private double limelightTimestamp;

    /**
     * Constructor.
     */
    public Vision(CommandSwerveDrivetrain drivetrain) {
        this.drivetrain = drivetrain;

        this.setLimelightRobotPosition();
        //In the constructor, set the IMU mode to 1, so the limelight IMU is seeded with the robot gyro heading.
        LimelightHelpers.SetIMUMode(VisionConstants.kLimelightName, 1);
        LimelightHelpers.SetRobotOrientation(VisionConstants.kLimelightName, this.getRobotHeading(), 0.0, 0.0, 0.0, 0.0, 0.0);

        SmartDashboard.putData("Vision", this);
        SmartDashboard.putData("Vision/Pose", this.limelightField);
    }

    /**
     * Set the position of the Limelight relative to the center of the robot.
     * This only needs to be run during initialization.
     */
    private void setLimelightRobotPosition() {
        LimelightHelpers.setCameraPose_RobotSpace(
            VisionConstants.kLimelightName,
            VisionConstants.kLimelightForwardOffset,
            VisionConstants.kLimelightSideOffset,
            VisionConstants.kLimelightUpOffset,
            VisionConstants.kLimelightRollOffset,
            VisionConstants.kLimelightPitchOffset,
            VisionConstants.kLimelightYawOffset
        );
    }

    /**
     * Command the limelight to start using its internal IMU for the pose estimate it produces.
     */
    private void setLimelightToInternalIMU() {
        LimelightHelpers.SetIMUMode(VisionConstants.kLimelightName, 2);
    }

    /**
     * Returns true if the pose estimate is not 'null' and a valid target is in view.
     * 'Valid' in this case means the limelight actually sent data and sees a valid target; this method is not checking if the data makes sense.
     * @param poseEstimate
     * @return
     */
    private boolean isMegaTagValid(LimelightHelpers.PoseEstimate poseEstimate) {
        return (poseEstimate != null) && LimelightHelpers.getTV(VisionConstants.kLimelightName);
    }

    /**
     * Returns true if the latest megaTag estimate identifies at least the amount of tags passed into this method.
     * @param tagCount
     * @return
     */
    private boolean areTagsSeen(int tagCount) {
        return this.megaTag2.tagCount >= tagCount;
    }

    /**
     * Returns true if the rotational velocity of the robot is less than the value passed into this method.
     * @param maximumRotationRate
     * @return
     */
    private boolean isRobotSlowEnough(double maximumRotationRate) {
        return this.cachedRobotRotationRate <= maximumRotationRate;
    }

    /**
     * Signifies that the latest estimated pose is valid if:
     * 1. The megaTag2 estimate is valid.
     * 2. At least one AprilTag was seen.
     * 3. The robot is not turning too fast.
     * @return
     */
    private boolean isPoseValid() {
        //3.3 radians per second is currently 75% of our maximum rotational speed.
        return this.isMegaTagValid(this.megaTag2) && this.areTagsSeen(1) && this.isRobotSlowEnough(3.3);
    }

    /**
     * Return the current robot heading, in degrees.
     * The current heading is based on the robot pose, because the pigeon yaw doesn't wrap around 0 - 360 degrees.
     */
    private double getRobotHeading() {
        return this.drivetrain.getState().Pose.getRotation().getDegrees();
    }

    /**
     * Return the absolute angular velocity of the robot, in radians per second.
     * @return
     */
    private double getRobotRotationRate() {
        return Math.abs(this.drivetrain.getState().Speeds.omegaRadiansPerSecond);
    }

    /**
     * Return the pose component of the current megaTag2 estimate.
     * @return
     */
    private Pose2d getCurrentPose() {
        return this.megaTag2.pose;
    }

    /**
     * Returns a string of the name of the currently running command.
     * If no command is running, return "No Command".
     * @return
     */
    private String getCurrentCommandName() {
        if (this.getCurrentCommand() == null) {
            return "No Command";
        }
        else {
            return this.getCurrentCommand().getName();
        }
        // Refactoring this method with a ternary operator.
        // return (this.getCurrentCommand == null) ? "No Command" : this.getCurrentCommand().getName();
    }

    public Trigger addLimelightPose = new Trigger(() -> {return this.cachedIsPoseValid;});

    /**
     * Add the current megaTag2 pose estimate to the drivetrain pose estimate.
     * @param drivetrain
     * @return
     */
    public Command addMegaTag2(Supplier<CommandSwerveDrivetrain> drivetrain) {
        return run(
            () -> {
                limelightTimestamp = Utils.getCurrentTimeSeconds();
                drivetrain.get().setVisionMeasurementStdDevs(VecBuilder.fill(0.5, 0.5, 9999999));
                drivetrain.get().addVisionMeasurement(megaTag2.pose, megaTag2.timestampSeconds);
            }
        ).withName("Adding Limelight Vision Measurement").ignoringDisable(true);
    }

    /**
     * Switch the limelight to use its internal IMU for the pose estimate.
     * @return
     */
    public Command switchToInternalIMU() {
        return runOnce(() -> {this.setLimelightToInternalIMU();}).withName("Setting Limelight to IMU Mode 2").ignoringDisable(true);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addStringProperty("Command", this::getCurrentCommandName, null);
        builder.addDoubleProperty("Robot Heading", () -> {return this.cachedRobotHeading;}, null);
        builder.addDoubleProperty("Robot Rotation Rate", () -> {return this.cachedRobotRotationRate;}, null);
        builder.addBooleanProperty("Is Robot Slow Enough", () -> {return this.cachedIsRobotSlowEnough;}, null);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run.
     
        // Start by caching important values.
        // By caching these values, any other code that requires them will use the same values for the current 20 ms loop.
        this.cachedRobotHeading = this.getRobotHeading();
        this.cachedRobotRotationRate = this.getRobotRotationRate();
        this.cachedMegaTag2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(VisionConstants.kLimelightName);
        this.cachedMegaTagValid = this.isMegaTagValid(this.cachedMegaTag2);
        this.cachedAreTagsSeen = this.areTagsSeen(1);
        this.cachedIsRobotSlowEnough = this.isRobotSlowEnough(cachedRobotRotationRate);
        this.cachedIsPoseValid = this.isPoseValid();

        //Only update the megaTag if the most recent megaTag is valid.
        if (this.cachedMegaTagValid) {
            this.megaTag2 = this.cachedMegaTag2;
        }

        //Every loop, seed the limelight IMU with the current robot heading.
        LimelightHelpers.SetRobotOrientation(VisionConstants.kLimelightName, this.cachedRobotHeading, 0.0, 0.0, 0.0, 0.0, 0.0);

        //Every loop, update the odometry with the current pose estimated by the limelight.
        limelightField.setRobotPose(this.getCurrentPose());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation.
    }
}
