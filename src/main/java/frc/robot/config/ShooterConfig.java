package frc.robot.config;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

public class ShooterConfig {
    //Constants go Here
    public static final int kShooter1MotorCANID = 0;
    public static final int kShooter2MotorCANID = 0;
    public static final int kHoodMotorCANID = 0;
    public static final int kTurretMotorCANID = 0;
    public static final int kSpindexerMotorCANID = 0;
    public static final int kKickupMotorCANID = 0;
   
    //Variables
    public TalonFXConfiguration hoodMotorConfig; //x44 motor
    public TalonFXConfiguration shooter1MotorConfig; //kraken motor (x60)
    public TalonFXConfiguration shooter2MotorConfig; //kraken motor (x60)
    public TalonFXConfiguration turretMotorConfig; //x44 motor
    public TalonFXConfiguration spindexerMotorConfig; //kraken motor (x60)
    public TalonFXConfiguration kickupMotorConfig; //kraken motor (x60)

    //Constructor - only runs one
    public ShooterConfig(){
        hoodMotorConfig = new TalonFXConfiguration(); //Instantiate - make a framework
        this.configureHoodMotor(hoodMotorConfig); //Fill in framework, requires a method below

        shooter1MotorConfig = new TalonFXConfiguration(); //Instantiate - make a framework
        this.configureShooterMotor1(shooter1MotorConfig); //Fill in framework, requires a method below

        shooter2MotorConfig = new TalonFXConfiguration(); //Instantiate - make a framework
        this.configureShooterMotor2(shooter2MotorConfig); //Fill in framework, requires a method below

        turretMotorConfig = new TalonFXConfiguration(); //Instantiate - make a framework
        this.configureTurretMotor(turretMotorConfig); //Fill in framework, requires a method below

        spindexerMotorConfig = new TalonFXConfiguration(); //Instantiate - make a framework
        this.configureSpindexerMotor(spindexerMotorConfig); //Fill in framework, requires a method below

        kickupMotorConfig = new TalonFXConfiguration(); //Instantiate - make a framework
        this.configureKickupMotor(kickupMotorConfig); //Fill in framework, requires a method below
    }

    public void configureHoodMotor(TalonFXConfiguration hood){
        //Configure Motor
        hood.MotorOutput.Inverted=InvertedValue.CounterClockwise_Positive;
        hood.MotorOutput.NeutralMode=NeutralModeValue.Brake;
        hood.MotorOutput.PeakForwardDutyCycle = 1;
        hood.MotorOutput.PeakReverseDutyCycle = -1;

        hood.CurrentLimits.StatorCurrentLimit = 120.0;
        hood.CurrentLimits.StatorCurrentLimitEnable = true;
        hood.CurrentLimits.SupplyCurrentLimit = 20;    //Was 20
        hood.CurrentLimits.SupplyCurrentLimitEnable = true;
        hood.CurrentLimits.SupplyCurrentLowerLimit = 40.0;
        hood.CurrentLimits.SupplyCurrentLowerTime = 1;

        //Slot 0 Config
        hood.Slot0.kP = 0;  // An error of 1 rotation per second results in 2V output
        hood.Slot0.kI = 0;  // An error of 1 rotation per second increases output by 0.5V every second
        hood.Slot0.kD = 0;  // A change of 1 rotation per second squared results in 0.01 volts output
        hood.Slot0.kS = 0;
        hood.Slot0.kV = 0;  // KV for a Kraken X60 is 490 rpm/V. 490/60 is 8.1667 rps/V.  The inverse is 0.122449 V/rps.
        hood.Slot0.kA = 0;
        hood.Slot0.kG = 0;

        hood.Voltage.PeakForwardVoltage = 11;
        hood.Voltage.PeakReverseVoltage = -11;

        hood.Audio.AllowMusicDurDisable = true;
    }

    public void configureShooterMotor1(TalonFXConfiguration shooter1){
        //configure motor
        shooter1.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        shooter1.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        shooter1.MotorOutput.PeakForwardDutyCycle = 1;
        shooter1.MotorOutput.PeakReverseDutyCycle = -1;

        shooter1.CurrentLimits.StatorCurrentLimit = 120.0;
        shooter1.CurrentLimits.StatorCurrentLimitEnable = true;
        shooter1.CurrentLimits.SupplyCurrentLimit = 20;    //Was 20
        shooter1.CurrentLimits.SupplyCurrentLimitEnable = true;
        shooter1.CurrentLimits.SupplyCurrentLowerLimit = 40.0;
        shooter1.CurrentLimits.SupplyCurrentLowerTime = 1;

        //Slot 0 Configs
        shooter1.Slot0.kP = 0;  // An error of 1 rotation per second results in 2V output
        shooter1.Slot0.kI = 0;  // An error of 1 rotation per second increases output by 0.5V every second
        shooter1.Slot0.kD = 0;  // A change of 1 rotation per second squared results in 0.01 volts output
        shooter1.Slot0.kS = 0;
        shooter1.Slot0.kV = 0;  // KV for a Kraken X60 is 490 rpm/V. 490/60 is 8.1667 rps/V.  The inverse is 0.122449 V/rps.
        shooter1.Slot0.kA = 0;
        shooter1.Slot0.kG = 0.0;

        shooter1.Slot0.StaticFeedforwardSign = StaticFeedforwardSignValue.UseVelocitySign;

        shooter1.Voltage.PeakForwardVoltage = 11;
        shooter1.Voltage.PeakReverseVoltage = -11;

        shooter1.Audio.AllowMusicDurDisable = true;
    }

    public void configureShooterMotor2(TalonFXConfiguration shooter2){
        //configure motor
        shooter2.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        shooter2.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        shooter2.MotorOutput.PeakForwardDutyCycle = 1;
        shooter2.MotorOutput.PeakReverseDutyCycle = -1;

        shooter2.CurrentLimits.StatorCurrentLimit = 120.0;
        shooter2.CurrentLimits.StatorCurrentLimitEnable = true;
        shooter2.CurrentLimits.SupplyCurrentLimit = 20;    //Was 20
        shooter2.CurrentLimits.SupplyCurrentLimitEnable = true;
        shooter2.CurrentLimits.SupplyCurrentLowerLimit = 40.0;
        shooter2.CurrentLimits.SupplyCurrentLowerTime = 1;

        //Slot 0 Configs
        shooter2.Slot0.kP = 0;  // An error of 1 rotation per second results in 2V output
        shooter2.Slot0.kI = 0;  // An error of 1 rotation per second increases output by 0.5V every second
        shooter2.Slot0.kD = 0;  // A change of 1 rotation per second squared results in 0.01 volts output
        shooter2.Slot0.kS = 0;
        shooter2.Slot0.kV = 0;  // KV for a Kraken X60 is 490 rpm/V. 490/60 is 8.1667 rps/V.  The inverse is 0.122449 V/rps.
        shooter2.Slot0.kA = 0;
        shooter2.Slot0.kG = 0;
        shooter2.Slot0.StaticFeedforwardSign = StaticFeedforwardSignValue.UseVelocitySign;

        shooter2.Voltage.PeakForwardVoltage = 11;
        shooter2.Voltage.PeakReverseVoltage = -11;

        shooter2.Audio.AllowMusicDurDisable = true;
    }


    public void configureTurretMotor(TalonFXConfiguration turret){
        //configure motor
        turret.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        turret.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        turret.MotorOutput.PeakForwardDutyCycle = 1;
        turret.MotorOutput.PeakReverseDutyCycle = -1;

        turret.CurrentLimits.StatorCurrentLimit = 120.0;
        turret.CurrentLimits.StatorCurrentLimitEnable = true;
        turret.CurrentLimits.SupplyCurrentLimit = 20;    //Was 20
        turret.CurrentLimits.SupplyCurrentLimitEnable = true;
        turret.CurrentLimits.SupplyCurrentLowerLimit = 40.0;
        turret.CurrentLimits.SupplyCurrentLowerTime = 1;

        //Slot 0 Configs
        turret.Slot0.kP = 0;  // An error of 1 rotation per second results in 2V output
        turret.Slot0.kI = 0;  // An error of 1 rotation per second increases output by 0.5V every second
        turret.Slot0.kD = 0;  // A change of 1 rotation per second squared results in 0.01 volts output
        turret.Slot0.kS = 0;
        turret.Slot0.kV = 0;  // KV for a Kraken X60 is 490 rpm/V. 490/60 is 8.1667 rps/V.  The inverse is 0.122449 V/rps.
        turret.Slot0.kA = 0;
        turret.Slot0.kG = 0.0;

        turret.Voltage.PeakForwardVoltage = 11;
        turret.Voltage.PeakReverseVoltage = -11;

        turret.Audio.AllowMusicDurDisable = true;
    }

        public void configureSpindexerMotor(TalonFXConfiguration turret){
        //configure motor
        turret.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        turret.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        turret.MotorOutput.PeakForwardDutyCycle = 1;
        turret.MotorOutput.PeakReverseDutyCycle = -1;

        turret.CurrentLimits.StatorCurrentLimit = 120.0;
        turret.CurrentLimits.StatorCurrentLimitEnable = true;
        turret.CurrentLimits.SupplyCurrentLimit = 20;    //Was 20
        turret.CurrentLimits.SupplyCurrentLimitEnable = true;
        turret.CurrentLimits.SupplyCurrentLowerLimit = 40.0;
        turret.CurrentLimits.SupplyCurrentLowerTime = 1;

        //Slot 0 Configs
        turret.Slot0.kP = 0;  // An error of 1 rotation per second results in 2V output
        turret.Slot0.kI = 0;  // An error of 1 rotation per second increases output by 0.5V every second
        turret.Slot0.kD = 0;  // A change of 1 rotation per second squared results in 0.01 volts output
        turret.Slot0.kS = 0;
        turret.Slot0.kV = 0;  // KV for a Kraken X60 is 490 rpm/V. 490/60 is 8.1667 rps/V.  The inverse is 0.122449 V/rps.
        turret.Slot0.kA = 0;
        turret.Slot0.kG = 0.0;

        turret.Voltage.PeakForwardVoltage = 11;
        turret.Voltage.PeakReverseVoltage = -11;

        turret.Audio.AllowMusicDurDisable = true;
    }

        public void configureKickupMotor(TalonFXConfiguration turret){
        //configure motor
        turret.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        turret.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        turret.MotorOutput.PeakForwardDutyCycle = 1;
        turret.MotorOutput.PeakReverseDutyCycle = -1;

        turret.CurrentLimits.StatorCurrentLimit = 120.0;
        turret.CurrentLimits.StatorCurrentLimitEnable = true;
        turret.CurrentLimits.SupplyCurrentLimit = 20;    //Was 20
        turret.CurrentLimits.SupplyCurrentLimitEnable = true;
        turret.CurrentLimits.SupplyCurrentLowerLimit = 40.0;
        turret.CurrentLimits.SupplyCurrentLowerTime = 1;

        //Slot 0 Configs
        turret.Slot0.kP = 0;  // An error of 1 rotation per second results in 2V output
        turret.Slot0.kI = 0;  // An error of 1 rotation per second increases output by 0.5V every second
        turret.Slot0.kD = 0;  // A change of 1 rotation per second squared results in 0.01 volts output
        turret.Slot0.kS = 0;
        turret.Slot0.kV = 0;  // KV for a Kraken X60 is 490 rpm/V. 490/60 is 8.1667 rps/V.  The inverse is 0.122449 V/rps.
        turret.Slot0.kA = 0;
        turret.Slot0.kG = 0.0;

        turret.Voltage.PeakForwardVoltage = 11;
        turret.Voltage.PeakReverseVoltage = -11;

        turret.Audio.AllowMusicDurDisable = true;
    }

}
