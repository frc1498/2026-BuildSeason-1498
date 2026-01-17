package frc.robot.config;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class ClimberConfig {
    //Constants go here
    public static final int kInnerClimbMotorCANID=0;
    public static final int kOuterClimbMotorCANID=0;

    //Variables
    public TalonFXConfiguration innerClimbMotorConfig;
    public TalonFXConfiguration outerClimbMotorConfig;

    //Constructor
    public ClimberConfig(){
        innerClimbMotorConfig = new TalonFXConfiguration();  //Instantiate - make a framework
        this.configureInnerClimber(innerClimbMotorConfig);  //Fill in framework 

        outerClimbMotorConfig = new TalonFXConfiguration();  //Instantiate - make a framework
        this.configureOuterClimber(outerClimbMotorConfig);  //Fill in framework 
    }

    public void configureInnerClimber(TalonFXConfiguration innerClimber){
//configure motor
        innerClimber.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        innerClimber.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        innerClimber.MotorOutput.PeakForwardDutyCycle = 1;
        innerClimber.MotorOutput.PeakReverseDutyCycle = -1;

        innerClimber.CurrentLimits.StatorCurrentLimit = 120.0;
        innerClimber.CurrentLimits.StatorCurrentLimitEnable = true;
        innerClimber.CurrentLimits.SupplyCurrentLimit = 20;    //Was 20
        innerClimber.CurrentLimits.SupplyCurrentLimitEnable = true;
        innerClimber.CurrentLimits.SupplyCurrentLowerLimit = 40.0;
        innerClimber.CurrentLimits.SupplyCurrentLowerTime = 1;

        //Slot 0 Configs
        innerClimber.Slot0.kP = 0;  // An error of 1 rotation per second results in 2V output
        innerClimber.Slot0.kI = 0;  // An error of 1 rotation per second increases output by 0.5V every second
        innerClimber.Slot0.kD = 0;  // A change of 1 rotation per second squared results in 0.01 volts output
        innerClimber.Slot0.kS = 0;
        innerClimber.Slot0.kV = 0;  // KV for a Kraken X60 is 490 rpm/V. 490/60 is 8.1667 rps/V.  The inverse is 0.122449 V/rps.
        innerClimber.Slot0.kA = 0;
        innerClimber.Slot0.kG = 0;
        innerClimber.Slot0.GravityType = GravityTypeValue.Arm_Cosine;

        innerClimber.Voltage.PeakForwardVoltage = 11;
        innerClimber.Voltage.PeakReverseVoltage = -11;

        innerClimber.Audio.AllowMusicDurDisable = true;
    }

    public void configureOuterClimber(TalonFXConfiguration outerClimber){
     //configure motor
        outerClimber.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        outerClimber.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        outerClimber.MotorOutput.PeakForwardDutyCycle = 1;
        outerClimber.MotorOutput.PeakReverseDutyCycle = -1;

        outerClimber.CurrentLimits.StatorCurrentLimit = 120.0;
        outerClimber.CurrentLimits.StatorCurrentLimitEnable = true;
        outerClimber.CurrentLimits.SupplyCurrentLimit = 20;    //Was 20
        outerClimber.CurrentLimits.SupplyCurrentLimitEnable = true;
        outerClimber.CurrentLimits.SupplyCurrentLowerLimit = 40.0;
        outerClimber.CurrentLimits.SupplyCurrentLowerTime = 1;

        //Slot 0 Configs
        outerClimber.Slot0.kP = 0;  // An error of 1 rotation per second results in 2V output
        outerClimber.Slot0.kI = 0;  // An error of 1 rotation per second increases output by 0.5V every second
        outerClimber.Slot0.kD = 0;  // A change of 1 rotation per second squared results in 0.01 volts output
        outerClimber.Slot0.kS = 0;
        outerClimber.Slot0.kV = 0;  // KV for a Kraken X60 is 490 rpm/V. 490/60 is 8.1667 rps/V.  The inverse is 0.122449 V/rps.
        outerClimber.Slot0.kA = 0;
        outerClimber.Slot0.kG = 0;
        outerClimber.Slot0.GravityType = GravityTypeValue.Arm_Cosine;

        outerClimber.Voltage.PeakForwardVoltage = 11;
        outerClimber.Voltage.PeakReverseVoltage = -11;

        outerClimber.Audio.AllowMusicDurDisable = true;   
    }

}
