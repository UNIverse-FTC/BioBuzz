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
import org.firstinspires.ftc.teamcode.meet0.util.Debug;

public class Drivetrain {

    public final  MecanumDrive driveTrain;
    public MotorEx frontLeft, backLeft, frontRight, backRight;

    public Follower follower;
    private Pose robotPosition;
    private boolean aiming = false;
    public Drivetrain(RobotHardware hardware){
        driveTrain = hardware.mecanum;
        follower = org.firstinspires.ftc.teamcode.pedro.Constants.create(hardware.hardwareMap);
    }

    public  void Drive(double lateral, double horizontal, double strafe)
    {
        if (!aiming) {
            driveTrain.driveRobotCentric(strafe, lateral, -horizontal);
//            follower.manual(strafe, lateral, horizontal);
        }
        follower.update();
        robotPosition = follower.pose();
        Debug.telemetry.addLine("Pose: " + robotPosition.x() + " " +robotPosition.y());
        Debug.updateRobot(robotPosition);
    }

    public void aim(boolean active) {
        if (active) {
            aiming = true;
            double xDistance = Constants.HIVE_POSITION.x() - robotPosition.x();
            Debug.telemetry.addLine("X Distance: " + xDistance);
            double yDistance = Constants.HIVE_POSITION.y() - robotPosition.y();
            Debug.telemetry.addLine("Y Distance: " + yDistance);
            double angleRads = Math.atan(xDistance / yDistance);
            Debug.telemetry.addLine("Angle: " + Math.toDegrees(angleRads));

            Pose currentAnglePos = new Pose(follower.pose().x(), follower.pose().y(), follower.pose().heading());
            Pose targetAnglePos = new Pose(follower.pose().x(), follower.pose().y(), angleRads);


        }
        else {
            aiming = false;
        }
    }


}
