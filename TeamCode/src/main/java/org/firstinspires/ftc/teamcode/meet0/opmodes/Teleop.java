package org.firstinspires.ftc.teamcode.meet0.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.meet0.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode.offseason.hardware.RobotHardware;

public class Teleop extends LinearOpMode {

    public void runOpMode()
    {
        HardwareManager hardware = new HardwareManager(hardwareMap);

        waitForStart();

        while (opModeIsActive())
        {
            telemetry.update();
        }
    }
}
