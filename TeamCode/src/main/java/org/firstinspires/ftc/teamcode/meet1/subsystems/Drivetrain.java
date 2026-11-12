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
import org.firstinspires.ftc.teamcode.meet1.util.Util;

public class Drivetrain {

    public final  MecanumDrive driveTrain;
    public Follower follower;
    private Pose robotPosition;
    private boolean aiming = false;

    private CustomPID aimPID;

    Pose hivePosition;

    private double strafe, lateral, horizontal;
    public Drivetrain(RobotHardware hardware, DrivetrainState drivetrainState) {

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

        double targetHeading = Util.getAimHeading(hivePosition, robotPosition);

        if (aiming) {
            autoAim(targetHeading);
        }
        else {
            driveTrain.driveRobotCentric(strafe, lateral, -horizontal);
        }

        Debug.updateRobot(robotPosition, targetHeading);

        Debug.panelsTelemetry.addData("Target Heading", Math.toDegrees(targetHeading));
        Debug.panelsTelemetry.addData("Robot Heading", Math.round(Math.toDegrees(robotPosition.heading())));

        Debug.updateActiveHive(hivePosition);
    }
    public void aim(boolean active) {
        aiming = active;
    }

    private void autoAim(double heading) {
        double angleAdjustment = aimPID.calculate(heading, robotPosition.heading());
        driveTrain.driveRobotCentric(-strafe, angleAdjustment, horizontal);
    }

    public double calculateDistance() {
        return Math.hypot(Util.xDistance,Util.yDistance);
    }

    public void resetPos() {
        follower.setPose(new Pose(56, 8, Math.toRadians(90)));
    }

    public void driveToLaunch() {
        //implement this later ig, should be simple. filter by alliance & closest hive
    }

}
