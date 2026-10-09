package org.firstinspires.ftc.teamcode.meet0.subsystems;

import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;

public class Drivetrain {

    public final  MecanumDrive driveTrain;
    public MotorEx frontLeft, backLeft, frontRight, backRight;

    public Drivetrain(RobotHardware hardware){
        driveTrain=hardware.mecanum;
    }

    public  void Drive(double lateral, double horizontal, double strafe)
    {
       driveTrain.driveRobotCentric(strafe, -lateral, horizontal);
    }


}
