package org.firstinspires.ftc.teamcode.meet1.subsystems;

import com.pedropathing.api.Paths;
import com.pedropathing.controllers.Controller;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;

import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.meet1.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet1.opmodes.OpModeStorage;
import org.firstinspires.ftc.teamcode.meet1.opmodes.Teleop;
import org.firstinspires.ftc.teamcode.meet1.util.Constants;
import org.firstinspires.ftc.teamcode.meet1.util.CustomPID;
import org.firstinspires.ftc.teamcode.meet1.util.Debug;

public class Drivetrain {

    public final  MecanumDrive driveTrain;
    public Follower follower;
    private Pose robotPosition;
    public boolean aiming = false;

    private CustomPID aimPID;

    private boolean returning = false;

    private double returnHeading = 0.0;

    public static double xDistance;
    public static double yDistance;

    private Controller headingPID;

    private boolean fieldCentric = false;

    private DrivePowers drivePowers;

    private double targetHeading;

    private int aimState;

    private ElapsedTime timer;
    Pose hivePosition;

    Teleop teleop;
    private double forward, lateral, turn;
    public Drivetrain(RobotHardware hardware, Teleop teleop) {

        driveTrain = hardware.mecanum;
        aimPID = hardware.aimPID;

        headingPID = hardware.headingPID;

        this.teleop = teleop;
        follower = org.firstinspires.ftc.teamcode.pedro.Constants.create(hardware.hardwareMap);
        follower.setPose(OpModeStorage.autonomousEndPose);
    }


    public  void drive(double forward, double lateral, double turn)
    {
        this.forward = forward;
        this.lateral = lateral;
        this.turn = turn;
    }

    public void update() {

        follower.update();
        robotPosition = follower.pose();


        if (robotPosition.y() < 72) {
            hivePosition = Constants.RED_HIVE_SOUTH;
        }
        else {
            hivePosition = Constants.RED_HIVE_NORTH;
        }

        targetHeading = getAimHeading(hivePosition, robotPosition);

        if (!follower.isBusy()) {
            if (fieldCentric) {
                drivePowers = ManualDrive.fieldCentric(lateral, -forward, -turn, robotPosition.heading());

            }
            else {
                drivePowers = new DrivePowers(-forward, -lateral, -turn);
            }
            Debug.panelsTelemetry.addLine(drivePowers.toString());
            if (aiming) {
                Pose aimPose = new Pose(0,0);
                switch (aimState) {
                    case (0):
                        aimPose = robotPosition;
                        teleop.launcher.shoot(calculateDistance());
                        aimState++;
                        break;
                    case (1):
                        follower.hold(aimPose);
                        aimState++;
                        break;
                    case (2):
                        double error = Math.abs(robotPosition.heading() - aimPose.heading());
                        if (error < Math.toRadians(10) && follower.velocity().vx < 0.5 && follower.velocity().vy < 0.5) {
                            aimState++;
                        }
                        break;
                    case (3):
                        if (teleop.launcher.ready1 && teleop.launcher.ready2) {
                            teleop.intake.suck();
                            timer.reset();
                            aimState++;
                        }
                        break;
                    case (4):
                        if (timer.seconds() > 0.8) {
                            teleop.intake.stoppySloppy();
                            aiming = false;
                        }
                        break;
                }
//                autoAim(targetHeading);
                drivePowers = ManualDrive.headingLock(follower, headingPID, drivePowers, targetHeading);
                ManualDrive.driveOrHold(
                        follower,
                        drivePowers)
                ;
            }
            else {
                follower.manual(drivePowers);

            }
        }

        Debug.updateRobot(robotPosition, targetHeading);

        Debug.panelsTelemetry.addData("Target Heading", Math.toDegrees(targetHeading));
        Debug.panelsTelemetry.addData("Robot Heading", Math.round(Math.toDegrees(robotPosition.heading())));
        Debug.panelsTelemetry.addData("Return Heading", Math.round(Math.toDegrees(returnHeading)));

        Debug.telemetry.addLine("Aiming?: " + aiming);

        Debug.updateActiveHive(hivePosition);
    }
    public void aim(boolean active) {
        if (active) {
            aimState = 0;
        }
        aiming = active;
    }

    public double calculateDistance() {
        return Math.hypot(xDistance,yDistance);
    }

    public void resetPos() {
        follower.setPose(new Pose(56, 8, Math.toRadians(90)));
    }

    public void driveToLaunch() {
    Pose poseTo;
        if (robotPosition.y() > 70) {
            poseTo = Constants.Poses.RED_NORTH;
        }
        else {
            poseTo = Constants.Poses.RED_SOUTH;
        }
        follower.follow(createPath(poseTo));
    }

    public void stopFollower() {
        follower.stop();
    }

    public void toggleFieldCentric(boolean toggle) {
        fieldCentric = toggle;
    }

    private Path createPath(Pose target) {
        return Paths.line(robotPosition, target).linear(robotPosition, target);
    }

    public static double getAimHeading(Pose hivePosition, Pose robotPosition) {

        xDistance = hivePosition.x() - robotPosition.x();
        yDistance = hivePosition.y() - robotPosition.y();

        if (xDistance >= 0) {
            return Math.atan(yDistance / xDistance);
        }
        else {
            return Math.atan(yDistance / xDistance) + Math.PI;

        }

    }
}
