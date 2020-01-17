/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrain extends SubsystemBase {
  // Left Motor group on drivetrain
  
  private final WPI_TalonSRX frontLeftMotor;
  private final WPI_TalonSRX backLeftMotor;
  private final SpeedControllerGroup leftMotors;

  // Right motor group on drivetrain
  private final WPI_TalonSRX frontRightMotor;
  private final WPI_TalonSRX backRightMotor;
  private final SpeedControllerGroup rightMotors;

  // Robot's drivebase
  private final DifferentialDrive m_driveBase;

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    frontLeftMotor = new WPI_TalonSRX(DriveConstants.id_frontLeftMotor);
    backLeftMotor = new WPI_TalonSRX(DriveConstants.id_backLeftMotor);
    backLeftMotor.setInverted(true);
    leftMotors = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);

    frontRightMotor = new WPI_TalonSRX(DriveConstants.id_frontRightMotor);
    backRightMotor = new WPI_TalonSRX(DriveConstants.id_backRightMotor);
    rightMotors = new SpeedControllerGroup(frontRightMotor, backRightMotor);

    m_driveBase = new DifferentialDrive(leftMotors, rightMotors);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  /**
   * This function sets 3 axis'. One is used for acceleration (forwards drive),
   * one is used for deceleration (backwards drive), one is used for turning (left
   * & right rotation)
   * 
   * @param acceleration : Set this parameter to the axis that will accelerate and
   *                     decelerate the robot.
   * @param turn         : Set this parameter to the axis that will rotate the
   *                     robot.
   */
  public void manualDrive(final double acceleration, final double turn) {
    m_driveBase.arcadeDrive(acceleration, turn);
  }

  /**
   * This function sets 3 axis'. One is used for acceleration (forwards drive),
   * one is used for deceleration (backwards drive), one is used for turning (left
   * & right rotation)
   * 
   * @param acceleration : Set this parameter to the axis that will accelerate the
   *                     robot.
   * @param deceleration : Set this parameter to the axis that will decelerate the
   *                     robot.
   * @param turn         : Set this parameter to the axis that will rotate the
   *                     robot.
   */
  public void manualDrive(final double acceleration, final double deceleration, final double turn) {
    final double accelerate = acceleration - deceleration;
    m_driveBase.arcadeDrive(turn, accelerate);
  }
}
