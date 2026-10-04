package org.firstinspires.ftc.teamcode.meet0.subsystems;

import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.meet0.hardware.HardwareManager;

public class Drivetrain {

    public final  MecanumDrive driveTrain;
    public MotorEx frontLeft, backLeft, frontRight, backRight;

    public Drivetrain(HardwareManager hardware){
        driveTrain=hardware.mecanum;
        frontLeft=hardware.frontLeft;
        backLeft=hardware.backLeft;
        frontRight=hardware.frontRight;
        backRight=hardware.backRight;
    }

    public  void Drive(double lateral, double horizontal, double strafe)
    {
       driveTrain.driveRobotCentric(strafe, lateral, horizontal);
    }

    public void shoot() {
        frontLeft.set(0.5);
        backLeft.set(0.5);
        frontRight.set(0.5);
        backRight.set(0.5);
    }

    public void disable() {
        frontLeft.set(0);
        backLeft.set(0);
        frontRight.set(0);
        backRight.set(0);
    }


}
