package org.firstinspires.ftc.teamcode.meet1.opmodes;

import static org.firstinspires.ftc.teamcode.meet1.util.enhancedinput.ButtonType.*;

import org.firstinspires.ftc.teamcode.meet1.subsystems.Intake;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.meet1.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet1.subsystems.enums.DrivetrainState;
import org.firstinspires.ftc.teamcode.meet1.util.Debug;
import org.firstinspires.ftc.teamcode.meet1.util.enhancedinput.Button;
import org.firstinspires.ftc.teamcode.meet1.util.enhancedinput.ContinuousButton;
import org.firstinspires.ftc.teamcode.meet1.util.enhancedinput.MomentarySwitchButton;
import org.firstinspires.ftc.teamcode.meet1.util.enhancedinput.ToggleButton;

import java.util.List;



@TeleOp(name="\uD83C\uDFAE TeleOp")
public class Teleop extends LinearOpMode {

    Button manualSpeedUp, manualSpeedDown, toggleOverride, poseReset, armShooter, speedMode, toggleAim, intakeToggle;
    double fallBackSpeed = 2740;
    double speed = 1;

    public void runOpMode()
    {

        RobotHardware robotHardware = new RobotHardware(hardwareMap);

        Launcher launcher = new Launcher(robotHardware);
        Intake intake = new Intake(robotHardware);
        Drivetrain drivetrain = new Drivetrain(robotHardware, DrivetrainState.TELEOP);

        Debug.init(telemetry);

        while (opModeInInit()) {

        }
        waitForStart();



        List<Button> buttons = List.of(
                manualSpeedUp = new MomentarySwitchButton(gamepad1, DPAD_UP, () -> adjustFallBackSpeed(1)),
                manualSpeedDown = new MomentarySwitchButton(gamepad1, DPAD_DOWN, () -> adjustFallBackSpeed(-1)),
                poseReset = new MomentarySwitchButton(gamepad2, A, drivetrain::resetPos),
                toggleOverride = new ToggleButton(gamepad2, RIGHT_BUMPER),

                armShooter = new ToggleButton(gamepad1, A),
                speedMode = new ToggleButton(gamepad1, LEFT_BUMPER, this::slowMode, this::normalMode),
                toggleAim = new ToggleButton(gamepad1, LEFT_TRIGGER, () -> drivetrain.aim(true), () -> drivetrain.aim(false)),
                intakeToggle = new ContinuousButton(gamepad1, RIGHT_TRIGGER, intake::suck, intake::stoppySloppy)
        );

        while (opModeIsActive()) {

            drivetrain.drive(
                    gamepad1.right_stick_x * speed * 0.7,
                    gamepad1.left_stick_y * speed,
                    gamepad1.left_stick_x * speed
            );

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
                launcher.stop();

                Debug.telemetry.addLine("Shooter: IDLE");
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

    public void adjustFallBackSpeed(int m) {
        fallBackSpeed += m * 20;
    }

    public void slowMode() {
        speed = 0.5;
    }

    public void normalMode() {
        speed = 1;
    }
}
