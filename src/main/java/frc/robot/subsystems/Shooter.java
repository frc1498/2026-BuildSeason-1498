// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//This subsystem manages the spindexer, kickup, turret, hood, shooter motors 

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
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

  
  public DutyCycleOut turretDutyCycle;
  boolean turretZeroed;


/** Creates a new ExampleSubsystem. */
public Shooter(ShooterConfig config) {
  shooter1Motor = new TalonFX(ShooterConfig.kShooter1MotorCANID, "canivore");  //Create a motor for this subsystem
  this.configureMechanism(shooter1Motor, config.shooter1MotorConfig);
  shooter2Motor = new TalonFX(ShooterConfig.kShooter2MotorCANID, "canivore");  //Create a motor for this subsystem
  this.configureMechanism(shooter2Motor, config.shooter2MotorConfig);
  shooterMotorMode = new VelocityVoltage(0);  //Set the motor's control mode

  spindexerMotor = new TalonFX(ShooterConfig.kSpindexerMotorCANID, "canivore");  //Create a motor for this subsystem
  spindexerMotorMode = new VelocityVoltage(0);  //Set the motor's control mode
  this.configureMechanism(spindexerMotor, config.spindexerMotorConfig);

  kickupMotor = new TalonFX(ShooterConfig.kKickupMotorCANID, "canivore");  //Create a motor for this subsystem
  kickupMotorMode = new VelocityVoltage(0);  //Set the motor's control mode
  this.configureMechanism(kickupMotor, config.kickupMotorConfig);
   
  turretMotor = new TalonFX(ShooterConfig.kTurretMotorCANID, "canivore");  //Create a motor for this subsystem
  turretMotorMode = new PositionVoltage(0);  //Set the motor's control mode
  this.configureMechanism(turretMotor, config.turretMotorConfig);
    
  hoodMotor = new TalonFX(ShooterConfig.kHoodMotorCANID, "canivore");  //Create a motor for this subsystem
  hoodMotorMode = new PositionVoltage(0);  //Set the motor's control mode
  this.configureMechanism(hoodMotor, config.hoodMotorConfig);

  turretZeroed = true;
  turretDutyCycle = new DutyCycleOut(0.0);

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
  private void goToHoodPosition(Double position) {
    if (MotorEnableConstants.kHoodMotorEnabled) {
      if (position <= ShooterConstants.kHoodSafeExtend //Check that Value is below extended distance 
      && position >= ShooterConstants.kHoodSafeRetract) { //Check that Value is above retracted distance
        hoodMotor.setControl(hoodMotorMode.withPosition(position));
      }
    }
  }

  private void goToTurretPosition(Double position) {
    if (MotorEnableConstants.kTurretMotorEnabled) {
      if (position <= ShooterConstants.kTurretSafeClockwise //Check that Value is below extended distance 
      && position >= ShooterConstants.kTurretSafeCounterClockwise) { //Check that Value is above retracted distance
        turretMotor.setControl(turretMotorMode.withPosition(position));
      }
    }
  }

  private void goToShooterSpeed(Double speed){
    if (MotorEnableConstants.kShooter1MotorEnabled && MotorEnableConstants.kShooter2MotorEnabled) {
      shooter1Motor.setControl(shooterMotorMode.withVelocity(speed));
      shooter2Motor.setControl(shooterMotorMode.withVelocity(speed));
    }
  }

  private void goToKickupSpeed(Double speed){
    if (MotorEnableConstants.kKickupMotorEnabled) {
      kickupMotor.setControl(kickupMotorMode.withVelocity(speed));
    }
  }

  private void goToSpindexerSpeed(Double speed){
    if (MotorEnableConstants.kSpindexerMotorEnabled) {
      spindexerMotor.setControl(spindexerMotorMode.withVelocity(speed));
    }
  }
 
  private void zeroTurret() {
    turretMotor.setControl(turretDutyCycle.withOutput(ShooterConstants.kTurretZeroDutyCycle)); //set a low constant speed
  }
  
  private boolean isTurretAtZero() {
    if (turretMotor.getStatorCurrent().getValueAsDouble() > ShooterConstants.kTurretZeroCurrentLimit) { //Check current draw for hard stop collision
      turretZeroed=true;  //Turret zeroing is complete because we passed the current limit threshold
      turretMotor.setPosition(ShooterConstants.kZeroPosition); //set the encoder position on the motor to whatever it should be
      //Going to have to talk to trevor - how do we go "back" into tracking more turretMotor.setControl(.withOutput();      
    }
    return turretZeroed;  
  }



  //====================Public Methods=====================
	public Command reZeroTurret() {
	  turretZeroed=false;
	  return run(() -> {this.zeroTurret();})
	    .until(isTurretZeroed);
	}

  public Command reverseSpindexer() {
    return run(() -> {this.goToSpindexerSpeed(ShooterConstants.kSpindexerReverse);});
  }

  public Command reverseKickup() {
    return run(() -> {this.goToKickupSpeed(ShooterConstants.kKickUpReverse);});
  }

  //======================Triggers=========================
  public Trigger isTurretZeroed = new Trigger(() -> {return this.isTurretAtZero();});

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
