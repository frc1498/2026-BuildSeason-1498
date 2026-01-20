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
import frc.robot.config.HopperConfig;
import frc.robot.constants.MotorEnableConstants;
import frc.robot.constants.ClimberConstants;
import frc.robot.constants.HopperConstants;

public class Climber extends SubsystemBase {
//==================Variables=======================
  public TalonFX outerClimbMotor;  //Motor type definition
  public TalonFX innerClimbMotor;  //Motor type definition

  public PositionVoltage outerClimbMotorMode; //Motor control type definition
  public PositionVoltage innerClimbMotorMode; //Motor control type definition

  ClimberConfig climberConfig; //Create an object of type climber config to use to configure motors

  //===============Constructor======================
  public Climber(ClimberConfig config) {
    innerClimbMotor = new TalonFX(config.kInnerClimbMotorCANID, "canivore");  //Create a motor for this subsystem
    innerClimbMotorMode = new PositionVoltage(0);  //Set the motor's control mode

    this.configureMechanism(innerClimbMotor, config.innerClimbMotorConfig);

    outerClimbMotor = new TalonFX(config.kOuterClimbMotorCANID, "canivore");  //Create a motor for this subsystem
    outerClimbMotorMode = new PositionVoltage(0);  //Set the motor's control mode
    
    this.configureMechanism(outerClimbMotor, config.outerClimbMotorConfig);

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
  private void goToPositionInnerClimb(double position) {
    if (MotorEnableConstants.kInnerClimbMotorEnabled) {
      if (position <= ClimberConstants.kInnerClimbSafeExtend //Check that Value is below extended distance 
      && position >= ClimberConstants.kInnerClimbSafeRetract) { //Check that Value is above retracted distance
        innerClimbMotor.setControl(innerClimbMotorMode.withPosition(position));
      }
    }
  }

    private void goToPositionOuterClimb(double position) {
    if (MotorEnableConstants.kOuterClimbMotorEnabled) {
      if (position <= ClimberConstants.kOuterClimbSafeExtend //Check that Value is below extended distance 
      && position >= ClimberConstants.kInnerClimbSafeRetract) { //Check that Value is above retracted distance
        outerClimbMotor.setControl(outerClimbMotorMode.withPosition(position));
      }
    }
  }

  private double getInnerClimbPosition() {
    return innerClimbMotor.getPosition().getValueAsDouble();
  }

  private double getOuterClimbPosition() {
    return outerClimbMotor.getPosition().getValueAsDouble();
  }


  private boolean isInnerClimbAtPosition(double position) {
    return ((position - ClimberConstants.kDeadband) <= this.getInnerClimbPosition()) 
    && ((position + ClimberConstants.kDeadband) >= this.getInnerClimbPosition());
  }

  private boolean isOuterClimbAtPosition(double position) {
    return ((position - ClimberConstants.kDeadband) <= this.getOuterClimbPosition()) 
    && ((position + ClimberConstants.kDeadband) >= this.getOuterClimbPosition());
  }

//=====================Public Methods====================
  public Command innerClimbExtend() {
    return run(
      () -> {this.goToPositionInnerClimb(ClimberConstants.kInnerClimbExtend);}
    ).until(isInnerClimbExtended);
  }

  public Command innerClimbHandOff() {
    return run(
      () -> {this.goToPositionInnerClimb(ClimberConstants.kInnerClimbHandOff);}
    ).until(isInnerClimbHandedOff);
  }

  public Command innerClimbRetract() {
    return run(
      () -> {this.goToPositionInnerClimb(ClimberConstants.kInnerClimbRetract);}
    ).until(isInnerClimbRetracted);
  }

  public Command outerClimbExtend() {
    return run(
      () -> {this.goToPositionOuterClimb(ClimberConstants.kOuterClimbExtend);}
    ).until(isOuterClimbExtended);
  }
  public Command outerClimbHandoff() {
    return run(
      () -> {this.goToPositionOuterClimb(ClimberConstants.kOuterClimbHandOff);}
    ).until(isOuterClimbHandedOff);
  }

  public Command outerClimbRetract() {
    return run(
      () -> {this.goToPositionOuterClimb(ClimberConstants.kOuterClimbRetract);}
    ).until(isOuterClimbRetracted);
  }

//=======================Triggers======================
public Trigger isInnerClimbExtended = new Trigger(() -> {return this.isInnerClimbAtPosition(ClimberConstants.kInnerClimbExtend);});
public Trigger isInnerClimbHandedOff = new Trigger(() -> {return this.isInnerClimbAtPosition(ClimberConstants.kInnerClimbHandOff);});
public Trigger isInnerClimbRetracted = new Trigger(() -> {return this.isInnerClimbAtPosition(ClimberConstants.kInnerClimbRetract);});

public Trigger isOuterClimbExtended = new Trigger(() -> {return this.isOuterClimbAtPosition(ClimberConstants.kOuterClimbExtend);});
public Trigger isOuterClimbHandedOff = new Trigger(() -> {return this.isOuterClimbAtPosition(ClimberConstants.kOuterClimbHandOff);});
public Trigger isOuterClimbRetracted = new Trigger(() -> {return this.isOuterClimbAtPosition(ClimberConstants.kOuterClimbRetract);});




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
