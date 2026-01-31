package frc.robot.sim;

import com.ctre.phoenix6.sim.TalonFXSimState;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

import frc.robot.config.ShooterConfig;
import frc.robot.constants.ShooterConstants;

public class ShooterSim implements AutoCloseable {

    public ShooterConfig shooterConfig;
    public TalonFXSimState hood;
    public TalonFXSimState turret;
    public TalonFXSimState shooterOne;
    public TalonFXSimState shooterTwo;
    public TalonFXSimState spindexer;
    public TalonFXSimState kickup;

    public DCMotor shooterGearbox = DCMotor.getKrakenX60Foc(2);
    public DCMotor hoodAdjust = DCMotor.getKrakenX44Foc(1);
    public DCMotor turretRotate = DCMotor.getKrakenX44Foc(1);
    public DCMotor spindexerRotate = DCMotor.getKrakenX60Foc(1);
    public DCMotor kickupRotate = DCMotor.getKrakenX60Foc(1);

    public FlywheelSim shooterFlywheel;
    public DCMotorSim hoodAdjustSim;
    public DCMotorSim turretRotateSim;
    public DCMotorSim spindexerRotateSim;
    public DCMotorSim kickupRotateSim;

     /*new DCMotorSim(LinearSystemId.createDCMotorSystem(gearbox, 0.001, 100.0), gearbox);*/
    public ShooterSim(ShooterConfig config, TalonFXSimState hood, TalonFXSimState turret, TalonFXSimState shooterOne, TalonFXSimState shooterTwo, TalonFXSimState spindexer, TalonFXSimState kickup) {
        this.shooterConfig = config;
        this.hood = hood;
        this.turret = turret;
        this.shooterOne = shooterOne;
        this.shooterTwo = shooterTwo;
        this.spindexer = spindexer;
        this.kickup = kickup;

        this.shooterFlywheel = new FlywheelSim(
            LinearSystemId.createFlywheelSystem(this.shooterGearbox, 0.001, ShooterConstants.kShooterFlywheelGearing),
            this.shooterGearbox,
             null
        );
        this.hoodAdjustSim = new DCMotorSim(LinearSystemId.createDCMotorSystem(this.hoodAdjust, 0.001, ShooterConstants.kHoodGearing), this.hoodAdjust);
        this.turretRotateSim = new DCMotorSim(LinearSystemId.createDCMotorSystem(this.turretRotate, 0.001, ShooterConstants.kTurretGearing), this.turretRotate);
        this.spindexerRotateSim = new DCMotorSim(LinearSystemId.createDCMotorSystem(this.spindexerRotate, 0.001, ShooterConstants.kSpindexerGearing), this.spindexerRotate);
        this.kickupRotateSim = new DCMotorSim(LinearSystemId.createDCMotorSystem(this.kickupRotate, 0.001, ShooterConstants.kKickupGearing), this.kickupRotate);
    }

     @Override
    public void close() {

    }
}
