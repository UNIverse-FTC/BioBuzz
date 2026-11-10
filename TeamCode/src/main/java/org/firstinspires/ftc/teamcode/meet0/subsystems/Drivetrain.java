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
    public Follower follower;
    private Pose robotPosition;
    private boolean aiming = false;
    private boolean returning = false;
    private double xDistance;
    private double yDistance;
    private CustomPID aimPID;
    private double angleRads;
    private double lastHeading = 0;
    Pose hivePosition;

    public Drivetrain(RobotHardware hardware) {

        driveTrain = hardware.mecanum;

        follower = org.firstinspires.ftc.teamcode.pedro.Constants.create(hardware.hardwareMap);
        follower.setPose(OpModeStorage.autonomousEndPose);

        aimPID = new CustomPID(
                Constants.Drivetrain.KP,
                Constants.Drivetrain.KI,
                Constants.Drivetrain.KD,
                Constants.Drivetrain.KV,
                Constants.Drivetrain.KS,
                0,
                0
        );

    }


    public  void Drive(double lateral, double horizontal, double strafe)
    {
        if (aiming) {
            double rotationAim = calculatePID(Math.PI / 2);


            Debug.panelsTelemetry.addData("Target Heading", Math.toDegrees(angleRads));

            Debug.panelsTelemetry.addData("PID", Math.toDegrees(rotationAim));
            if (hivePosition.y() > 72) {
                if (robotPosition.y() <= hivePosition.y() + 5) {
                    driveTrain.driveRobotCentric(strafe, lateral, -horizontal);
                }
                else {
                    driveTrain.driveRobotCentric(-strafe, rotationAim, horizontal);
                }
            }
            else {
                if (robotPosition.y() >= hivePosition.y() - 5) {
                    driveTrain.driveRobotCentric(strafe, lateral, -horizontal);
                }
                else {
                    driveTrain.driveRobotCentric(-strafe, rotationAim, horizontal);
                }
            }
        }
        else {
            if (returning) {
                double rotationAim = calculatePID(lastHeading + Math.PI);
                driveTrain.driveRobotCentric(-strafe, rotationAim, horizontal);
                if (robotPosition.heading() > lastHeading - Math.toRadians(10) && robotPosition.heading() < lastHeading + Math.toRadians(10)) {
                    returning = false;
                }
                if (lateral > 0.1) {
                    returning = false;
                }
            }
            else {
                driveTrain.driveRobotCentric(strafe, lateral, -horizontal);
            }
        }

        follower.update();
        robotPosition = follower.pose();

        Debug.updateRobot(robotPosition, angleRads);
        Debug.telemetry.addLine("Robot Heading: " + Math.round(Math.toDegrees(robotPosition.heading())));
        Debug.panelsTelemetry.addData("Robot Heading", Math.round(Math.toDegrees(robotPosition.heading())));

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
        yDistance = hivePosition.y() - robotPosition.y();

    }

    public void aim(boolean active) {
        if (active) {
            returning = false;
            aiming = true;
        }
        else {
            aiming = false;
            lastHeading = robotPosition.heading();
            returning = true;
        }
    }

    private double calculatePID(double targetAngle) {
        angleRads = Math.atan(xDistance / yDistance) + (targetAngle) + Math.PI;

        return aimPID.calculate(angleRads, robotPosition.heading());
    }
    public double calculateDistance() {
        return Math.hypot(xDistance,yDistance);
        //return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
    }

    public void resetPos() {
        follower.setPose(new Pose(56, 8, Math.toRadians(90)));
    }

}
