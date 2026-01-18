// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.constants.ControllerConstants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.config.ClimberConfig;
import frc.robot.config.HopperConfig;
import frc.robot.config.IntakeConfig;
import frc.robot.config.ShooterConfig;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final Hopper m_exampleSubsystem = new Hopper();

  //Gamepad assignment
  private final CommandXboxController m_driverController =
    new CommandXboxController(ControllerConstants.kDriverControllerPort);

  private final CommandXboxController m_operatorController =
    new CommandXboxController(ControllerConstants.kOperatorControllerPort);


  //=======================================================================
  //=======================Assign Subsystem Names==========================
  //======================================================================= 
  public final ClimberConfig climberConfig = new ClimberConfig();
  public Climber climber = new Climber(climberConfig);

  public HopperConfig hopperConfig = new HopperConfig();
  public Hopper hopper = new Hopper(hopperConfig);

  public IntakeConfig intakeConfig = new IntakeConfig();
  public Intake intake = new Intake(intakeConfig);

  public ShooterConfig shooterConfig = new ShooterConfig();
  public Shooter shooter = new Shooter(shooterConfig);


  //Instantiate 
    private final CommandXboxController driver = new CommandXboxController(0);
    private final CommandXboxController operator = new CommandXboxController(1);
  
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
  //===================================================
  //===================Driver Commands=================
  //===================================================
  
  //Driver POV Up
  //driver.povUp().

  //Driver POV Down
  //driver.povDown().

  //Driver POV Left
  //driver.povLeft().

  //Driver POV Right
  //driver.povRight().

  //Driver X button
  //driver.x().

  //Driver Y button
  //driver.y().

  //Driver A button
  //driver.a().

  //Driver B button
  //driver.b().

  //Driver RTrigger
  //driver.rightTrigger(0.1).

  //Driver RBumper 
  //driver.rightBumper().

  //Driver LTrigger
  //driver.leftTrigger(0.1).

  //Driver LBumper
  //driver.leftBumper().

  //Driver Select
  //driver.

  //Driver Start
  //driver.start().

  //===================================================
  //==================Operator Commands================
  //===================================================

  //Operator POV Up
  //operator.povUp()
  
  //Operator POV Down
  //operator.povDown()

  //Operator POV Left
  //operator.povLeft()

  //Operator POV Right
  //operator.povRight()

  //Operator X button
  //operator.X()

  //Operator Y button
  //operator.y()

  //Operator A button
  //operator.a()

  //Operator B button
  //operator.b()

  //Operator RTrigger
  //operator.rightTrigger(0.1)

  //Operator RBumper 
  //operator.rightBumper()

  //Operator LTrigger
  //operator.leftTrigger(0.1)

  //Operator LBumper
  //operator.leftBumper()

  //Operator Select
  //operator.

  //Operator Start
  //operator.START().

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
