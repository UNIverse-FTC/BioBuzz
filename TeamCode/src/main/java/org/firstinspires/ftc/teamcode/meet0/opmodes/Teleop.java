package org.firstinspires.ftc.teamcode.meet0.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;

@TeleOp(name="Meet 0 Teleop")
public class Teleop extends LinearOpMode {

    private RobotHardware robotHardware;



    public void runOpMode()
    {
        RobotHardware robotHardware = new RobotHardware(hardwareMap);
        Drivetrain drivetrain = new Drivetrain(robotHardware);
        Launcher launcher = new Launcher(robotHardware);

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
}
