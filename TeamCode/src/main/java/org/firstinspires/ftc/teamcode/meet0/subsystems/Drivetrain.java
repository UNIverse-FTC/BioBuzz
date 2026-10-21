package org.firstinspires.ftc.teamcode.meet0.subsystems;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
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
            driveTrain.driveRobotCentric(strafe, lateral, horizontal);
            follower.manual(strafe, lateral, horizontal);
        }

        robotPosition = follower.pose();
        Debug.updateRobot(robotPosition);
    }

    public void aim(boolean active) {
        if (active) {
            aiming = true;
            double xDistance = Constants.HIVE_POSITION.x() - robotPosition.x();
            double yDistance = Constants.HIVE_POSITION.y() - robotPosition.y();
            double angleRads = Math.atan(xDistance / yDistance);

            double aDistance = angleRads - robotPosition.heading();
            double movement = (2 * Math.PI * Math.log(aDistance)) / 5.01511202135;
            driveTrain.driveRobotCentric(0,0, movement);
        }
        else {
            aiming = false;
        }
    }


}
