package org.firstinspires.ftc.teamcode.meet1.subsystems;

import com.pedropathing.api.Paths;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;

import com.pedropathing.paths.Path;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.meet1.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet1.opmodes.OpModeStorage;
import org.firstinspires.ftc.teamcode.meet1.util.Constants;
import org.firstinspires.ftc.teamcode.meet1.util.CustomPID;
import org.firstinspires.ftc.teamcode.meet1.util.Debug;

public class Drivetrain {

    public final  MecanumDrive driveTrain;
    public Follower follower;
    private Pose robotPosition;
    private boolean aiming = false;

    private CustomPID aimPID;

    private boolean returning = false;

    private double returnHeading = 0.0;

    public static double xDistance;
    public static double yDistance;

    Pose hivePosition;

    private double strafe, lateral, horizontal;
    public Drivetrain(RobotHardware hardware) {

        driveTrain = hardware.mecanum;
        aimPID = hardware.aimPID;


        follower = org.firstinspires.ftc.teamcode.pedro.Constants.create(hardware.hardwareMap);
        follower.setPose(OpModeStorage.autonomousEndPose);
    }


    public  void drive(double lateral, double horizontal, double strafe)
    {
        this.lateral = lateral;
        this.horizontal = horizontal;
        this.strafe = strafe;
    }

    public void update() {

        follower.update();
        robotPosition = follower.pose();

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

        double targetHeading = getAimHeading(hivePosition, robotPosition);
        if (!follower.isBusy()) {
            if (aiming) {
                autoAim(targetHeading);
            } else {
                if (returning) {
                    autoAim(returnHeading);
                    if (Math.abs(robotPosition.heading() - returnHeading) < 0.174533) {
                        returning = false;
                    } else if (lateral > 0.1) {
                        returning = false;
                    }
                } else {
                    driveTrain.driveRobotCentric(strafe, lateral, -horizontal);
                }
            }
        }

        Debug.updateRobot(robotPosition, targetHeading);

        Debug.panelsTelemetry.addData("Target Heading", Math.toDegrees(targetHeading));
        Debug.panelsTelemetry.addData("Robot Heading", Math.round(Math.toDegrees(robotPosition.heading())));

        Debug.updateActiveHive(hivePosition);
    }
    public void aim(boolean active) {
        aiming = active;
        returning = !active;
        if (!active) {
            returnHeading = robotPosition.heading() + Math.PI;
        }
    }

    private void autoAim(double heading) {
        double angleAdjustment = aimPID.calculate(heading, robotPosition.heading());
        driveTrain.driveRobotCentric(-strafe, angleAdjustment, horizontal);
    }

    public double calculateDistance() {
        return Math.hypot(xDistance,yDistance);
    }

    public void resetPos() {
        follower.setPose(new Pose(56, 8, Math.toRadians(90)));
    }

    public void driveToLaunch(boolean alliance) {
        if (alliance) {
            if (robotPosition.y() > 70) {
                follower.follow(createPath(Constants.Poses.RED_NORTH));
            }
            else {
                follower.follow(createPath(Constants.Poses.RED_SOUTH));
            }
        }
        else  {
            if (robotPosition.y() > 70) {
                follower.follow(createPath(Constants.Poses.BLUE_NORTH));
            }
            else {
                follower.follow(createPath(Constants.Poses.BLUE_SOUTH));
            }
        }
    }

    public void stopFollower() {
        follower.stop();
    }

    private Path createPath(Pose target) {
        return Paths.line(robotPosition, target).linear(robotPosition, target);
    }

    public static double getAimHeading(Pose hivePosition, Pose robotPosition) {

        xDistance = robotPosition.x() - hivePosition.x();
        yDistance = hivePosition.y() - robotPosition.y();

        if (xDistance > 0) {
            return Math.atan(yDistance / xDistance);
        }
        else {
            return Math.atan((yDistance / xDistance) + Math.PI);
        }
    }
}
