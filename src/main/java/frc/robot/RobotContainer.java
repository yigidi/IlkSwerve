// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants;
import frc.robot.subsystems.swerve.SwerveSubsystem;
import yams.mechanisms.swerve.utility.SwerveInputStream;
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
  private final SwerveSubsystem swerve = new SwerveSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController Controller = new CommandXboxController(Constants.kDriverControllerPort);
  
  private final SwerveInputStream driveAngularVelocity =
      swerve.getAngularVelocityStream(
                Controller::getLeftY,
                Controller::getLeftX,
                () -> Controller.getRawAxis(2))
            .withAllianceRelativeControl();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    swerve.setDefaultCommand(swerve.drive(driveAngularVelocity));

    // Zero the gyro with Start + Back — use this if the field-relative heading drifts
    Controller.start().and(Controller.back()).onTrue(swerve.zeroGyro());
  


    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
   
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
