package org.firstinspires.ftc.teamcode.meet0.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Teleop extends LinearOpMode {

    public void runOpMode()
    {
        waitForStart();

        while (opModeIsActive())
        {
            telemetry.update();
        }
    }
}
