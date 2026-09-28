package org.firstinspires.ftc.teamcode.offseason.subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.offseason.hardware.RobotHardware;

public class DriveSubsystem {

    private final MecanumDrive driveTrain;
    public DriveSubsystem(RobotHardware robot) {
        driveTrain = robot.mecanum;
    }

    public void drive(double forward, double strafe, double rotate) {

        driveTrain.driveRobotCentric(strafe,forward,rotate);

    }
}
