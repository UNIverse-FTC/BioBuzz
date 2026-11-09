package org.firstinspires.ftc.teamcode.meet0.opmodes;

import static org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.ButtonType.*;

import org.firstinspires.ftc.teamcode.meet0.subsystems.Intake;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet0.util.Debug;
import org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.Button;
import org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.ContinuousButton;
import org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.MomentarySwitchButton;
import org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.ToggleButton;

import java.util.List;

@TeleOp(name="\uD83C\uDFAE TeleOp")
public class Teleop extends LinearOpMode {


    public void runOpMode()
    {

        RobotHardware robotHardware = new RobotHardware(hardwareMap);
        Drivetrain drivetrain = new Drivetrain(robotHardware);
        Launcher launcher = new Launcher(robotHardware);
        Intake intake = new Intake(robotHardware);

        Debug.init(telemetry);

        while (opModeInInit()) {

        }
        waitForStart();

        double fallBackSpeed = 2740;
        double speed = 1;

        Button manualSpeedUp = new MomentarySwitchButton(gamepad2, DPAD_UP);
        Button manualSpeedDown = new MomentarySwitchButton(gamepad2, DPAD_DOWN);
//        Button poseReset = new MomentarySwitchButton(gamepad2, A);
        Button toggleOverride = new ToggleButton(gamepad2, RIGHT_BUMPER);

        Button poseReset = new MomentarySwitchButton(gamepad2, A, drivetrain::resetPos);


        Button armShooter = new ToggleButton(gamepad1, A);
        Button speedMode = new ToggleButton(gamepad1, LEFT_BUMPER);
//        Button toggleAim = new ToggleButton(gamepad1, LEFT_TRIGGER);

        //If this gives an errors, or doesn't work, comment it out and uncomment the one above...
        Button toggleAim = new ToggleButton(gamepad1, LEFT_TRIGGER, () -> drivetrain.aim(true), () -> drivetrain.aim(false));

        Button intakeToggle = new ContinuousButton(gamepad1, RIGHT_BUMPER, intake::suck, intake::stoppySloppy);


        List<Button> buttons = List.of(
                manualSpeedUp,
                manualSpeedDown,
                poseReset,
                toggleOverride,
                armShooter,
                speedMode,
                toggleAim,
                intakeToggle
        );

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

//            if (toggleAim.get()) {
//                drivetrain.aim(true);
//                Debug.telemetry.addLine("AIMING ACTIVE");
//            }
//            else {
//                drivetrain.aim(false);
//            }

//            if (gamepad1.right_bumper) {
//                intake.suck();
//            }
//            else {
//                intake.stoppySloppy();
//            }

//            if (poseReset.get()) {
//                drivetrain.resetPos();
//            }

            Debug.telemetry.addLine("Manual Speed: "  + fallBackSpeed);

            if (armShooter.get()) {
                if (toggleOverride.get()) {
                    launcher.shooterLeft.setRPM(fallBackSpeed);
                    launcher.shooterRight.setRPM(fallBackSpeed);

                    Debug.telemetry.addLine("Shooter: ARMED - MANUAL");
                }
                else {
                    launcher.shoot(drivetrain.calculateDistance());
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

            launcher.update();
            intake.update();
            Debug.update();

            for (Button button : buttons) {
                button.update();
            }
        }
    }
}
