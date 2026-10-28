package org.firstinspires.ftc.teamcode.meet0.subsystems;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;

import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;


import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.opmodes.OpModeStorage;
import org.firstinspires.ftc.teamcode.meet0.paths.AutoSimple;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;
import org.firstinspires.ftc.teamcode.meet0.util.CustomPID;
import org.firstinspires.ftc.teamcode.meet0.util.Debug;

public class Drivetrain {

    public final  MecanumDrive driveTrain;
    public MotorEx frontLeft, backLeft, frontRight, backRight;

    public Follower follower;
    private Pose robotPosition;
    private boolean aiming = false;
    private double xDistance;
    private double yDistance;
    private CustomPID aimPID;
    private double angleRads;
    private double rotationAim = 0;
    Pose hivePosition;

    public Drivetrain(RobotHardware hardware) {

        driveTrain = hardware.mecanum;
        follower = org.firstinspires.ftc.teamcode.pedro.Constants.create(hardware.hardwareMap);
        aimPID = new CustomPID(
                2.0,
                0,
                0.67,
                0,
                0,
                0,
                0
        );

    }
    public void initLoc(int Selected) {

//        if (Selected == 0) {
            follower.setPose(OpModeStorage.autonomousEndPose);

//        }
//        else {
//            if (OpModeStorage.autonomousEndPose ==)
//            follower.setPose(new Pose((double)2.0F * 0 - 56, 8, Math.PI - Math.toRadians(270)));
//
//        }

    }

    public  void Drive(double lateral, double horizontal, double strafe)
    {
        if (!aiming) {
            driveTrain.driveRobotCentric(strafe, lateral, -horizontal);
//            follower.manual(strafe, lateral, horizontal);
        }
        else {
            if (hivePosition.y() > 72) {
                if (robotPosition.y() <= hivePosition.y() + 5) {
                    driveTrain.driveRobotCentric(strafe, lateral, -horizontal);
                }
                else {
                    driveTrain.driveRobotCentric(strafe, rotationAim, -horizontal);
                }
            }
            else {
                if (robotPosition.y() >= hivePosition.y() - 5) {
                    driveTrain.driveRobotCentric(strafe, lateral, -horizontal);
                }
                else {
                    driveTrain.driveRobotCentric(strafe, rotationAim, -horizontal);
                }
            }
        }
        follower.update();
        robotPosition = follower.pose();
        Debug.updateRobot(robotPosition, angleRads);
        Debug.telemetry.addLine("Robot Heading: " + Math.round(Math.toDegrees(robotPosition.heading())));

    }

    public void aim(boolean active) {
        if (active) {
            aiming = true;
            if (robotPosition.x() < 72) {
                if (robotPosition.y() < 72) {
                    hivePosition = Constants.RED_HIVE_SOUTH;
                }
                else {
                    hivePosition = Constants.RED_HIVE_NORTH;
                }
            }
            else {
                if (robotPosition.y() < 72) {
                    hivePosition = Constants.BLUE_HIVE_SOUTH;
                }
                else {
                    hivePosition = Constants.BLUE_HIVE_NORTH;
                }
            }

            Debug.updateActiveHive(hivePosition);

            xDistance = robotPosition.x() - hivePosition.x();
//            Debug.telemetry.addLine("X Distance: " + xDistance);
            yDistance = hivePosition.y() - robotPosition.y();
//            Debug.telemetry.addLine("Y Distance: " + yDistance);
            angleRads = Math.atan(xDistance / yDistance) + (Math.PI / 2) + Math.PI;
            Debug.telemetry.addLine("Target Angle: ");


            rotationAim = aimPID.calculate(angleRads, robotPosition.heading());

        }
        else {
            aiming = false;
        }
    }

    public double calculateDistance() {
        return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
    }

    public void resetPos() {
        follower.setPose(new Pose(56, 8, Math.toRadians(90)));
    }

}
