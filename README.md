# 2026-BuildSeason-1498

Code for Team 1498's 2026 robot, Aurora Zwei. <br>
[ADD ROBOT PICTURE HERE]

## General

01/17/26 - Proved out the functionality of DogLog.  Will start integrating the logging into the subsystems. <br>
01/17/26 - Started creating the code framework with subsystems, configurations, and constants.

## Planned Subsystems

### Drivetrain
01/17/26 - CTRE Swerve Template generated. <br>
01/17/26 - Added the PathPlanner base code into the drive subsystem.
#### *To-Do*
- Drive the robot on the floor to determine gains.
- Add auton selection code.
- Add code to run the selected auton.
- Add code to drive to a set position during TeleOp.

### Vision
The Limelight will be used to supplement the pose estimate.
#### *To-Do*
- Add the vision subsystem.
- Configure the Limelight.
- Create the command and trigger to integrate the pose estimate from vision into the drivetrain.
- List variables to track in NetworkTables.
- List variables to log with DogLog.

### LED
Every year, the team wants to add LEDs to the robot for signalling, but it's always a low priority. <br>
Maybe this year will be the year.
#### *To-Do*
- Define parameters for each state (color, pattern, etc?).
- Define maximum amount of LEDs (including CANdle and LED strip).
- Create configuration settings.
- Create base commands for setting the mode.
- Create command factories for each state.
- List variables to track in NetworkTables.
- List variables to log with DogLog.

### Intake
An intake for picking up game pieces. <br>
Through bumper? Over the bumper? We'll find out.
#### *To-Do*
- Everything

### Hopper
Some system for agitating the game pieces and moving them up into the shooter.
#### *To-Do*
- Everything

### Shooter
A shooter that includes a turret, hood adjuster, and flywheel. <br>
Maybe more (definitely more), but this is just a draft...
#### *To-Do*
- Everything
### Climber
Probably some kind of hook or clamp to latch on the climb bars.
#### *To-Do*
- Everything
