// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//This subsystem manages the climber

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.config.ClimberConfig;
import frc.robot.constants.MotorEnableConstants;
import frc.robot.constants.ClimberConstants;

public class Climber extends SubsystemBase {
//==================Variables=======================
  public TalonFX liftClimbMotor;  //Motor type definition
  public TalonFX rotateClimb1Motor;  //Motor type definition
  public TalonFX rotateClimb2Motor;  //Motor type definition

  public PositionVoltage liftClimbMotorMode; //Motor control type definition
  public PositionVoltage rotateClimb1MotorMode; //Motor control type definition
  public PositionVoltage rotateClimb2MotorMode; //Motor control type definition

  ClimberConfig climberConfig; //Create an object of type climber config to use to configure motors

  //===============Constructor======================
  public Climber(ClimberConfig config) {
    rotateClimb1Motor = new TalonFX(ClimberConfig.kRotateClimb1MotorCANID, "canivore");  //Create a motor for this subsystem
    rotateClimb1MotorMode = new PositionVoltage(0);  //Set the motor's control mode

    this.configureMechanism(rotateClimb1Motor, config.rotateClimb1MotorConfig);

    rotateClimb2Motor = new TalonFX(ClimberConfig.kRotateClimb2MotorCANID, "canivore");  //Create a motor for this subsystem
    rotateClimb2MotorMode = new PositionVoltage(0);  //Set the motor's control mode

    this.configureMechanism(rotateClimb2Motor, config.rotateClimb2MotorConfig);

    liftClimbMotor = new TalonFX(ClimberConfig.kLiftClimbMotorCANID, "canivore");  //Create a motor for this subsystem
    liftClimbMotorMode = new PositionVoltage(0);  //Set the motor's control mode
    
    this.configureMechanism(liftClimbMotor, config.liftClimbMotorConfig);
  }
  
  public void configureMechanism(TalonFX mechanism, TalonFXConfiguration config){     
    //Start Configuring Hopper Motor
    StatusCode mechanismStatus = StatusCode.StatusCodeNotInitialized;

    for(int i = 0; i < 5; ++i) {
       mechanismStatus = mechanism.getConfigurator().apply(config);
      if (mechanismStatus.isOK()) break;
    }
    if (!mechanismStatus.isOK()) {
      System.out.println("Could not configure device. Error: " + mechanismStatus.toString());
    }
  }

//====================Private Methods====================
  private void goToPositionLiftClimb(double position) {
    if (MotorEnableConstants.kLiftClimbMotorEnabled) {
      if (position <= ClimberConstants.kLiftClimbSafeExtend //Check that Value is below extended distance 
      && position >= ClimberConstants.kLiftClimbSafeRetract) { //Check that Value is above retracted distance
        rotateClimb1Motor.setControl(liftClimbMotorMode.withPosition(position));
      }
    }
  }

    private void goToPositionRotateClimb(double position) {
    if (MotorEnableConstants.kRotateClimb1MotorEnabled) {
      if (position <= ClimberConstants.kRotateClimbSafeExtend //Check that Value is below extended distance 
      && position >= ClimberConstants.kRotateClimbSafeRetract) { //Check that Value is above retracted distance
        rotateClimb1Motor.setControl(rotateClimb1MotorMode.withPosition(position));
      }
    }
        if (MotorEnableConstants.kRotateClimb2MotorEnabled) {
      if (position <= ClimberConstants.kRotateClimbSafeExtend //Check that Value is below extended distance 
      && position >= ClimberConstants.kRotateClimbSafeRetract) { //Check that Value is above retracted distance
        rotateClimb2Motor.setControl(rotateClimb2MotorMode.withPosition(position));
      }
    }
  }

  private double getRotateClimb1Position() {
    return rotateClimb1Motor.getPosition().getValueAsDouble();
  }

  private double getRotateClimb2Position() {
    return rotateClimb2Motor.getPosition().getValueAsDouble();
  }

  private double getLiftClimbPosition() {
    return liftClimbMotor.getPosition().getValueAsDouble();
  }


  private boolean isLiftClimbAtPosition(double position) {
    return ((position - ClimberConstants.kLiftClimbDeadband) <= this.getLiftClimbPosition()) 
    && ((position + ClimberConstants.kLiftClimbDeadband) >= this.getLiftClimbPosition());
  }

  private boolean isRotateClimb1AtPosition(double position) {
    return ((position - ClimberConstants.kRotateClimbDeadband) <= this.getRotateClimb1Position()) 
    && ((position + ClimberConstants.kRotateClimbDeadband) >= this.getRotateClimb1Position());
  }

  private boolean isRotateClimb2AtPosition(double position) {
    return ((position - ClimberConstants.kRotateClimbDeadband) <= this.getRotateClimb2Position()) 
    && ((position + ClimberConstants.kRotateClimbDeadband) >= this.getRotateClimb2Position());
  }

//=====================Public Methods====================
  public Command liftClimbExtend() {
    return run(
      () -> {this.goToPositionLiftClimb(ClimberConstants.kLiftClimbExtend);}
    ).until(isLiftClimbExtended);
  }

  public Command liftClimbHandOff() {
    return run(
      () -> {this.goToPositionLiftClimb(ClimberConstants.kLiftClimbHandOff);}
    ).until(isLiftClimbHandedOff);
  }

  public Command LiftClimbRetract() {
    return run(
      () -> {this.goToPositionLiftClimb(ClimberConstants.kLiftClimbRetract);}
    ).until(isLiftClimbRetracted);
  }

  public Command rotateClimb1Extend() {
    return run(
      () -> {this.goToPositionRotateClimb(ClimberConstants.kRotateClimbExtend);}
    ).until(isRotateClimb1Extended);
  }
  public Command rotateClimb1Handoff() {
    return run(
      () -> {this.goToPositionRotateClimb(ClimberConstants.kRotateClimbHandOff);}
    ).until(isRotateClimb1HandedOff);
  }

  public Command rotateClimb1Retract() {
    return run(
      () -> {this.goToPositionRotateClimb(ClimberConstants.kRotateClimbRetract);}
    ).until(isRotateClimb1Retracted);
  }

  public Command rotateClimb2Extend() {
    return run(
      () -> {this.goToPositionRotateClimb(ClimberConstants.kRotateClimbExtend);}
    ).until(isRotateClimb1Extended);
  }
  public Command rotateClimb2Handoff() {
    return run(
      () -> {this.goToPositionRotateClimb(ClimberConstants.kRotateClimbHandOff);}
    ).until(isRotateClimb1HandedOff);
  }

  public Command rotateClimb2Retract() {
    return run(
      () -> {this.goToPositionRotateClimb(ClimberConstants.kRotateClimbRetract);}
    ).until(isRotateClimb1Retracted);
  }

//=======================Triggers======================
public Trigger isLiftClimbExtended = new Trigger(() -> {return this.isLiftClimbAtPosition(ClimberConstants.kLiftClimbExtend);});
public Trigger isLiftClimbHandedOff = new Trigger(() -> {return this.isLiftClimbAtPosition(ClimberConstants.kLiftClimbHandOff);});
public Trigger isLiftClimbRetracted = new Trigger(() -> {return this.isLiftClimbAtPosition(ClimberConstants.kLiftClimbRetract);});

public Trigger isRotateClimb1Extended = new Trigger(() -> {return this.isRotateClimb1AtPosition(ClimberConstants.kRotateClimbExtend);});
public Trigger isRotateClimb1HandedOff = new Trigger(() -> {return this.isRotateClimb1AtPosition(ClimberConstants.kRotateClimbHandOff);});
public Trigger isRotateClimb1Retracted = new Trigger(() -> {return this.isRotateClimb1AtPosition(ClimberConstants.kRotateClimbRetract);});

public Trigger isRotateClimb2Extended = new Trigger(() -> {return this.isRotateClimb2AtPosition(ClimberConstants.kRotateClimbExtend);});
public Trigger isRotateClimb2HandedOff = new Trigger(() -> {return this.isRotateClimb2AtPosition(ClimberConstants.kRotateClimbHandOff);});
public Trigger isRotateClimb2Retracted = new Trigger(() -> {return this.isRotateClimb2AtPosition(ClimberConstants.kRotateClimbRetract);});


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
