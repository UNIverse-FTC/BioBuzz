package org.firstinspires.ftc.teamcode.meet1.subsystems;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;

import com.seattlesolvers.solverslib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.meet1.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet1.opmodes.OpModeStorage;
import org.firstinspires.ftc.teamcode.meet1.subsystems.enums.DrivetrainState;
import org.firstinspires.ftc.teamcode.meet1.util.Constants;
import org.firstinspires.ftc.teamcode.meet1.util.CustomPID;
import org.firstinspires.ftc.teamcode.meet1.util.Debug;

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

    private DrivetrainState drivetrainState;
    Pose hivePosition;

    private double strafe, lateral, horizontal;
    public Drivetrain(RobotHardware hardware, DrivetrainState drivetrainState) {

        driveTrain = hardware.mecanum;

        follower = org.firstinspires.ftc.teamcode.pedro.Constants.create(hardware.hardwareMap);
        follower.setPose(OpModeStorage.autonomousEndPose);

        this.drivetrainState = drivetrainState;

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


    public  void drive(double lateral, double horizontal, double strafe)
    {
        this.lateral = lateral;
        this.horizontal = horizontal;
        this.strafe = strafe;
    }

    public void update() {
        if (aiming) {
            double rotationAim = calculatePID();


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
        else if (!(drivetrainState == DrivetrainState.AUTO)) {
                driveTrain.driveRobotCentric(strafe, lateral, -horizontal);
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
        aiming = active;
    }

    private double calculatePID() {
        angleRads = Math.atan(xDistance / yDistance) + (Math.PI / 2) + Math.PI;

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
