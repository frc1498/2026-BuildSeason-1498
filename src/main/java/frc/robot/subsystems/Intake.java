// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//This subsystem manages the run/stop of the intake rollers

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.config.IntakeConfig;
import frc.robot.constants.Constants;
import frc.robot.constants.IntakeConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  //Variables
  public TalonFX intakeMotor;  //Motor type definition
  
  public VelocityVoltage intakeMotorMode; //Motor control type definition

  IntakeConfig intakeConfig; //Create an object of type IntakeConfig

  //Constructor
  public Intake(IntakeConfig config) {

    intakeMotor = new TalonFX(config.kIntakeCANID, "canivore");  //Create the intake motor for this subsystem
    intakeMotorMode = new VelocityVoltage(0);  //Set the motor's control mode

    this.configureMechanism(intakeMotor, config.intakeConfig);
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

  //====================================================
  //=============Private Methods========================
  //====================================================

  private void intake(){

    if (Constants.kIntakeMotorEnabled) {
      intakeMotor.setControl(intakeMotorMode.withVelocity(IntakeConstants.kIntakeSpeed));
    }

  }

  //=====================================================
  //=============Public Methods==========================
  //=====================================================



  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
