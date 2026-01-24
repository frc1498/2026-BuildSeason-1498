// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//This subsystem manages the spindexer, kickup, turret, hood, shooter motors 

package frc.robot.subsystems;

import java.util.function.Supplier;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ShotCalculation;
import frc.robot.config.ShooterConfig;
import frc.robot.constants.MotorEnableConstants;
import frc.robot.constants.ShooterConstants;

public class Shooter extends SubsystemBase {
//==================Variables=======================
  public TalonFX shooter1Motor;  //Motor type definition
  public TalonFX shooter2Motor;  //Motor type definition
  public TalonFX spindexerMotor;  //Motor type definition
  public TalonFX turretMotor;  //Motor type definition
  public TalonFX kickupMotor;  //Motor type definition
  public TalonFX hoodMotor;  //Motor type definition

  public VelocityVoltage shooterMotorMode; //Motor control type definition
  public VelocityVoltage spindexerMotorMode; //Motor control type definition
  public VelocityVoltage kickupMotorMode; //Motor control type definition

  public PositionVoltage turretMotorMode; //Motor control type definition
  public PositionVoltage hoodMotorMode; //Motor control type definition

  ShooterConfig shooterConfig; //Create an object of type climber config to use to configure motors

  public Pose2d currentTarget;
  public Pose2d hubTarget;

  private double desiredHoodPosition;
  private double desiredTurretPosition;
  private double desiredShooterSpeed;
  private double desiredKickupSpeed;
  private double desiredSpindexerSpeed;

  private Supplier<Pose2d> poseSupplier;
  private double distanceToTarget;


/** Creates a new ExampleSubsystem. */
public Shooter(ShooterConfig config, Supplier<Pose2d> chassisSpeed) {
  shooter1Motor = new TalonFX(config.kShooter1MotorCANID, "canivore");  //Create a motor for this subsystem
  this.configureMechanism(shooter1Motor, config.shooter1MotorConfig);

  shooter2Motor = new TalonFX(config.kShooter2MotorCANID, "canivore");  //Create a motor for this subsystem
  this.configureMechanism(shooter2Motor, config.shooter2MotorConfig);
  shooterMotorMode = new VelocityVoltage(0);  //Set the motor's control mode

  spindexerMotor = new TalonFX(config.kSpindexerMotorCANID, "canivore");  //Create a motor for this subsystem
  spindexerMotorMode = new VelocityVoltage(0);  //Set the motor's control mode
  this.configureMechanism(spindexerMotor, config.spindexerMotorConfig);

  kickupMotor = new TalonFX(config.kKickupMotorCANID, "canivore");  //Create a motor for this subsystem
  kickupMotorMode = new VelocityVoltage(0);  //Set the motor's control mode
  this.configureMechanism(kickupMotor, config.kickupMotorConfig);
   
  turretMotor = new TalonFX(config.kTurretMotorCANID, "canivore");  //Create a motor for this subsystem
  turretMotorMode = new PositionVoltage(0);  //Set the motor's control mode
  this.configureMechanism(turretMotor, config.turretMotorConfig);
    
  hoodMotor = new TalonFX(config.kHoodMotorCANID, "canivore");  //Create a motor for this subsystem
  hoodMotorMode = new PositionVoltage(0);  //Set the motor's control mode
  this.configureMechanism(hoodMotor, config.hoodMotorConfig);

  this.poseSupplier = chassisSpeed;

  SmartDashboard.putData("Shooter", this);
}

public void configureMechanism(TalonFX mechanism, TalonFXConfiguration config){     
    //Start Configuring Climber Motor
    StatusCode mechanismStatus = StatusCode.StatusCodeNotInitialized;

    for(int i = 0; i < 5; ++i) {
        mechanismStatus = mechanism.getConfigurator().apply(config);
        if (mechanismStatus.isOK()) break;
    }
    if (!mechanismStatus.isOK()) {
        System.out.println("Could not configure device. Error: " + mechanismStatus.toString());
    }
  }

  //===================Private Methods=====================
  /**
   * Set the position of turret hood.
   * @param position
   */
  private void setHoodPosition(double position) {
    this.desiredHoodPosition = position;

    if (MotorEnableConstants.kHoodMotorEnabled) {
      if (this.isSetpointWithinSafetyRange(desiredHoodPosition, ShooterConstants.kHoodSafeRetract, ShooterConstants.kHoodSafeExtend)) {
        hoodMotor.setControl(hoodMotorMode.withPosition(desiredHoodPosition));
      }
    }
  }

  /**
   * Return the current position of the turret hood.
   * @return
   */
  private double getHoodPosition() {
    return hoodMotor.getPosition().getValueAsDouble();
  }

  /**
   * Returns true if the current setpoint is within the range of minimum and maximum parameters.
   */
  private boolean isSetpointWithinSafetyRange(double currentSetpoint, double minimum, double maximum) {
    return (currentSetpoint >= minimum) && (currentSetpoint <= maximum);
  }

  /**
   * Set the position of the turret angle.
   * @param position
   */
  private void setTurretPosition(double position) {
    this.desiredTurretPosition = position;

    if (MotorEnableConstants.kTurretMotorEnabled) {
      if (this.isSetpointWithinSafetyRange(desiredTurretPosition, ShooterConstants.kTurretSafeCounterClockwise, ShooterConstants.kTurretSafeClockwise)) {
        turretMotor.setControl(turretMotorMode.withPosition(desiredTurretPosition));
      }
    }
  }

  /**
   * Return the current position of the turret angle.
   * @return
   */
  private double getTurretPosition() {
    return turretMotor.getPosition().getValueAsDouble();
  }

  /**
   * Set the velocity of the shooter motors.
   * @param speed
   */
  private void setShooterSpeed(double speed) {
    this.desiredShooterSpeed = speed;

    if (MotorEnableConstants.kShooter1MotorEnabled && MotorEnableConstants.kShooter2MotorEnabled) {
      shooter1Motor.setControl(shooterMotorMode.withVelocity(desiredShooterSpeed));
      shooter2Motor.setControl(shooterMotorMode.withVelocity(desiredShooterSpeed));
    }
  }

  /**
   * Return the current velocity of shooter motor 1.
   * @return
   */
  private double getShooterSpeed() {
    return shooter1Motor.getVelocity().getValueAsDouble();
  }

  /**
   * Set the velocity of the kickup motor.
   * @param speed
   */
  private void setKickupSpeed(double speed) {
    this.desiredKickupSpeed = speed;

    if (MotorEnableConstants.kKickupMotorEnabled) {
      kickupMotor.setControl(kickupMotorMode.withVelocity(desiredKickupSpeed));
    }
  }

  /**
   * Return the current velocity of the kickup motor.
   * @return
   */
  private double getKickupSpeed() {
    return kickupMotor.getVelocity().getValueAsDouble();
  }

  /**
   * Set the velocity of the spindexer motor.
   * @param speed
   */
  private void setSpindexerSpeed(double speed) {
    this.desiredSpindexerSpeed = speed;

    if (MotorEnableConstants.kSpindexerMotorEnabled) {
      spindexerMotor.setControl(spindexerMotorMode.withVelocity(desiredSpindexerSpeed));
    }
  }

  /**
   * Return the current velocity of the spindexer motor.
   * @return
   */
  private double getSpindexerSpeed() {
    return spindexerMotor.getVelocity().getValueAsDouble();
  }
  
  //====================Public Methods=====================





  //======================Triggers=========================



  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("Distance to Target", () -> {return distanceToTarget;}, null);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    this.distanceToTarget = ShotCalculation.getInstance().getTargetDistance(this.poseSupplier.get(), ShooterConstants.kRedHubCenter);

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
