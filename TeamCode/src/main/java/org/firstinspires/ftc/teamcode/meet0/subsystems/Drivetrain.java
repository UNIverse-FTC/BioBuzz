package org.firstinspires.ftc.teamcode.meet0.subsystems;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.curves.Line;
import com.pedropathing.paths.curves.bezier.BezierCurve;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;


import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;
import org.firstinspires.ftc.teamcode.meet0.util.CustomPID;
import org.firstinspires.ftc.teamcode.meet0.util.Debug;

public class Drivetrain {

    public final  MecanumDrive driveTrain;
    public MotorEx frontLeft, backLeft, frontRight, backRight;

    public Follower follower;
    private Pose robotPosition;
    private boolean aiming = false;

    private CustomPID aimPID;

    private double rotationAim = 0;

    public Drivetrain(RobotHardware hardware){
        driveTrain = hardware.mecanum;
        follower = org.firstinspires.ftc.teamcode.pedro.Constants.create(hardware.hardwareMap);
        aimPID = new CustomPID(
                1.5,
                0,
                0,
                1.0,
                0,
                0,
                0
        );
    }

    public  void Drive(double lateral, double horizontal, double strafe)
    {
        if (!aiming) {
            driveTrain.driveRobotCentric(strafe, lateral, -horizontal);
//            follower.manual(strafe, lateral, horizontal);
        }
        else {
            driveTrain.driveRobotCentric(strafe, rotationAim, horizontal);
        }
        follower.update();
        robotPosition = follower.pose();
        Debug.telemetry.addLine("Pose: " + robotPosition.x() + " " +robotPosition.y());
        Debug.updateRobot(robotPosition);
    }

    public void aim(boolean active) {
        Pose hivePosition;
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

            double xDistance = hivePosition.x() - robotPosition.x();
            Debug.telemetry.addLine("X Distance: " + xDistance);
            double yDistance = hivePosition.y() - robotPosition.y();
            Debug.telemetry.addLine("Y Distance: " + yDistance);
            double angleRads = Math.atan(xDistance / yDistance);
            Debug.telemetry.addLine("Angle: " + Math.toDegrees(angleRads));


            rotationAim = aimPID.calculate(angleRads, robotPosition.heading());

        }
        else {
            aiming = false;
        }
    }


}
