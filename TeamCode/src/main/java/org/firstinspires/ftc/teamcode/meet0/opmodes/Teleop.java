package org.firstinspires.ftc.teamcode.meet0.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.meet0.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;

@TeleOp(name="Meet0 Teleop")
public class Teleop extends LinearOpMode {

    private HardwareManager robotHardware;



    private Drivetrain drivetrain;
    public void runOpMode()
    {
        HardwareManager robotHardware = new HardwareManager(hardwareMap);
        Drivetrain driveTrain = new Drivetrain(robotHardware);
        Launcher launcher = new Launcher(robotHardware);
        Init();
        waitForStart();



        while (opModeIsActive()) {
            telemetry.update();
            drivetrain.Drive(
                    gamepad1.right_stick_x,
                    gamepad1.left_stick_y,
                    gamepad1.left_stick_x);
            launcher.launch(gamepad1);
        }
    }


    public void Init()
    {
    }
}
