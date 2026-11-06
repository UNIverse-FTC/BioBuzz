package org.firstinspires.ftc.teamcode.meet0.opmodes;

import static org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.ButtonType.*;

import org.firstinspires.ftc.teamcode.meet0.subsystems.Intake;
import org.firstinspires.ftc.teamcode.meet0.subsystems.enums.HoodState;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet0.util.Debug;
import org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.Button;
import org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.FlipFlopButton;
import org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.IdempotentButton;
import org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.ToggleButton;

@TeleOp(name="\uD83C\uDFAE TeleOp")
public class Teleop extends LinearOpMode {


    public void runOpMode()
    {

        RobotHardware robotHardware = new RobotHardware(hardwareMap);
        Drivetrain drivetrain = new Drivetrain(robotHardware);
        Launcher launcher = new Launcher(robotHardware);
        Intake intake = new Intake(robotHardware);

        Debug.init(telemetry);


        Button selected_alliance = new FlipFlopButton(gamepad1, A, B);
        while (opModeInInit()) {
            String alliance = "none?? how did we get here?";

            if (selected_alliance.get()) {
                alliance = "A alliance";
            }
            else {
                alliance = "B alliance";
            }
            Debug.telemetry.addLine("A for red, B for blue");
            Debug.telemetry.addLine("Selected " + alliance);

            Debug.update();
        }
        waitForStart();

        drivetrain.initLoc(selected_alliance.get());

        double fallBackSpeed = 2740;
        double speed = 1;

        Button shooterBackup = new ToggleButton(gamepad2, A);
        Button manualSpeedUp = new IdempotentButton(gamepad2, DPAD_UP);
        Button manualSpeedDown = new IdempotentButton(gamepad2, DPAD_DOWN);
        Button poseReset = new IdempotentButton(gamepad2, A);

        Button hood = new ToggleButton(gamepad1, X);
        Button armShooter = new ToggleButton(gamepad1, A);

        Button speedMode = new ToggleButton(gamepad1, LEFT_BUMPER);

        Button toggleOverride = new ToggleButton(gamepad2, RIGHT_BUMPER);

        while (opModeIsActive()) {

            drivetrain.Drive(
                    gamepad1.right_stick_x * speed * 0.7,
                    gamepad1.left_stick_y * speed,
                    gamepad1.left_stick_x * speed
            );

            if (manualSpeedUp.get()) {
                fallBackSpeed += 20;
            }
            if (manualSpeedDown.get()) {
                fallBackSpeed -= 20;
            }




            if (gamepad1.right_bumper) {
                intake.suck(true, true);
            }
            else {
                intake.suck(false, false);
            }

            if (poseReset.get()) {
                drivetrain.resetPos();
            }
            Debug.telemetry.addLine("Manual Speed: "  + fallBackSpeed);


            if (armShooter.get()) {
                if (toggleOverride.get()) {
                    launcher.shooterLeft.setRPM(fallBackSpeed);
                    launcher.shooterRight.setRPM(fallBackSpeed);

                    Debug.telemetry.addLine("Shooter: ARMED - MANUAL");
                    Debug.telemetry.addLine("Manual Speed: "  + fallBackSpeed);
                }
                else {
                    launcher.shooterLeft.shoot(drivetrain.calculateDistance());
                    launcher.shooterRight.shoot(drivetrain.calculateDistance());

                    Debug.telemetry.addLine("Shooter: ARMED - AUTO");
                }
            }
            else {
                launcher.shooterLeft.setRPM(0);
                launcher.shooterRight.setRPM(0);

                Debug.telemetry.addLine("Shooter: IDLE");
            }


            if (speedMode.get()) {
                Debug.telemetry.addLine("SLOW MODE");
                speed = 0.5;

            } else {
                speed = 1;

            }

            Debug.telemetry.addLine("Distance: " + drivetrain.calculateDistance());

            if (hood.get()) {
                launcher.shooterLeft.setHood(HoodState.NECTAR);
            } else {
                launcher.shooterLeft.setHood(HoodState.POLLEN);
            }



            launcher.update();
            intake.update();
            Debug.update();
        }


    }

}
