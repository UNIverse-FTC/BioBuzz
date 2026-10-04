package org.firstinspires.ftc.teamcode.meet0.subsystems;

import com.seattlesolvers.solverslib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.meet0.hardware.HardwareManager;

public class Drivetrain {

    public final  MecanumDrive driveTrain;

    public Drivetrain(HardwareManager hardware){
        driveTrain=hardware.mecanum;
    }

    public  void Drive(double lateral, double horizontal, double strafe)
    {
       driveTrain.driveRobotCentric(strafe, lateral, horizontal);
    }


}
