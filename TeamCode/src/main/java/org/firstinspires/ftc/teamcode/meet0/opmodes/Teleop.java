package org.firstinspires.ftc.teamcode.meet0.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.meet0.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Drivetrain;

@TeleOp(name="Meet0 Teleop")
public class Teleop extends LinearOpMode {

    private HardwareManager hardwareManager;



    private Drivetrain drivetrain;
    public void runOpMode()
    {
        Init();
        waitForStart();



        while (opModeIsActive()) {
            telemetry.update();
            drivetrain.Drive(
                    gamepad1.right_stick_x,
                    gamepad1.left_stick_y,
                    gamepad1.left_stick_x);
        }
    }


    public void Init()
    {
        hardwareManager = new HardwareManager();
        hardwareManager.init(hardwareMap);
        drivetrain = new Drivetrain(hardwareManager);
    }
}
