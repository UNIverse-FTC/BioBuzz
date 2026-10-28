package org.firstinspires.ftc.teamcode.meet0.opmodes;

import static org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.Button.*;
import static org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.Type.*;

import org.firstinspires.ftc.teamcode.meet0.subsystems.Intake;
import org.firstinspires.ftc.teamcode.meet0.subsystems.enums.HoodState;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet0.util.CustomPID;
import org.firstinspires.ftc.teamcode.meet0.util.Debug;
import org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.EnhancedButton;


import com.seattlesolvers.solverslib.util.InterpLUT;
@TeleOp(name="\uD83C\uDFAE TeleOp")
public class Teleop extends LinearOpMode {


    public void runOpMode()
    {

        RobotHardware robotHardware = new RobotHardware(hardwareMap);
        Drivetrain drivetrain = new Drivetrain(robotHardware);
        Launcher launcher = new Launcher(robotHardware);
        Intake intake = new Intake(robotHardware);



        Debug.init(telemetry);

        int selected_alliance = 0;
        boolean previous_a = false;
        boolean previous_b = false;
        while (opModeInInit()) {
            String alliance = "none?? how did we get here?";

            switch (selected_alliance) {
                case (0):
                    alliance = "A alliance";
                    break;
                case (1):
                    alliance = "B alliance";
                    break;
            }
            Debug.telemetry.addLine("A for red, B for blue");
            Debug.telemetry.addLine("Selected " + alliance);

            if (gamepad1.a && !previous_a) {
                if (selected_alliance == 1) {
                    selected_alliance = 0;
                }
            }
            if (gamepad1.b && !previous_b) {
                if (selected_alliance == 0) {
                    selected_alliance = 1;
                }
            }
            previous_a = gamepad1.a;
            previous_b = gamepad1.b;
            Debug.update();
        }
        waitForStart();

        drivetrain.initLoc(selected_alliance);

        double fallBackSpeed = 2740;
        boolean leftOnLast = false;
        boolean leftOn = false;
        boolean previous_dpad_up = false;
        boolean previous_dpad_down = false;
//        boolean rightOnLast = false;
//        boolean rightOn = false;

        boolean hoodLast = false;
        boolean hood = false;

        boolean intakeLast = false;
        boolean intakeActive = false;

        boolean speedmodeLast = false;
        boolean speedmode = false;

        boolean prev_gamepad2_a = false;
        boolean prev_gamepad_b = false;
        double speed = 1;

        boolean aim_override = false;

        EnhancedButton shooterBackup = new EnhancedButton(gamepad2, A, TRIGGER);
        EnhancedButton manualSpeedUp = new EnhancedButton(gamepad2, DPAD_UP, ONCE);
        EnhancedButton manualSpeedDown = new EnhancedButton(gamepad2, DPAD_DOWN, ONCE);

        while (opModeIsActive()) {

//            telemetry.addLine("Servo Position: " + robotHardware.hoodServo.getRawPosition());
            drivetrain.Drive(
                    gamepad1.right_stick_x * speed * 0.7,
                    gamepad1.left_stick_y * speed,
                    gamepad1.left_stick_x * speed);
            boolean leftOnCurrent = gamepad2.left_trigger_pressed;
            boolean rightOnCurrent = gamepad1.b;
            boolean hoodCurrent = gamepad2.x;
            boolean intakeCurrent = gamepad2.right_trigger_pressed;
            boolean speedmodeCurrent = gamepad1.left_bumper;

            if (manualSpeedUp.get()) {
                fallBackSpeed += 20;
            }
            if (manualSpeedDown.get()) {
                fallBackSpeed -= 20;
            }


            Debug.telemetry.addLine("Adjusted Speed: "  + fallBackSpeed);

            if (gamepad1.a) {
                intake.suck(true, true);
            }
            else {
                if (gamepad1.b) {
                    intake.suck(true, false);
                }
                else {
                    intake.suck(false, false);
                }
            }





            if (gamepad2.a && !prev_gamepad2_a) {
                drivetrain.resetPos();
            }
            if(!prev_gamepad_b && gamepad2.b)
            {
                aim_override = !aim_override;
            }
            prev_gamepad2_a = gamepad2.a;
            prev_gamepad_b = gamepad1.b;
            if(leftOnCurrent && !leftOnLast) {
                leftOn = !leftOn;
            }
//            if(rightOnCurrent && !rightOnLast) {
//                rightOn = !rightOn;
//            }
            if(hoodCurrent && !hoodLast) {
                hood = !hood;
            }
            if(intakeCurrent && !intakeLast) {
                intakeActive = !intakeActive;
            }

            if(intakeCurrent && !intakeLast) {
                intakeActive = !intakeActive;
            }
            if(speedmodeCurrent && !speedmodeLast) {
                speedmode = !speedmode;
            }



            leftOnLast = leftOnCurrent;
//            rightOnLast = rightOnCurrent;
            hoodLast = hoodCurrent;
            intakeLast = intakeCurrent;
            speedmodeLast = speedmodeCurrent;




            if (!leftOn) {
                launcher.shooterLeft.setRPM(0);
                launcher.shooterRight.setRPM(0);

            } else {
                launcher.shooterLeft.setRPM(Constants.SHOOTER_POWER);
                launcher.shooterRight.setRPM(Constants.SHOOTER_POWER);

            }

            if (!speedmode) {
                speed = 1;

            } else {
                speed = 0.5;

            }


//            if (!rightOn) {
//                launcher.shooterRight.setRPM(0);
//            } else {
//                launcher.shooterRight.setRPM(Constants.SHOOTER_POWER);
//            }
//            if (!intakeActive) {
//                intake.suck(false, true);
//            } else {
//                intake.suck(true, true);
//            }

            Debug.telemetry.addLine("Distance: " + drivetrain.calculateDistance());

            if (hood) {
                launcher.shooterLeft.setHood(HoodState.NECTAR);
            } else {
                launcher.shooterLeft.setHood(HoodState.POLLEN);
            }
            //FANCIER SHOOTING?
            //If odometry working override, shoot with aiming
            if (gamepad1.left_trigger_pressed && !aim_override) {
                drivetrain.aim(false);
                launcher.shooterLeft.shoot(fallBackSpeed);
                launcher.shooterRight.shoot(fallBackSpeed);

//                Debug.telemetry.addLine("Distance: " + drivetraitruen.calculateDistance());
                if (gamepad1.right_trigger_pressed) {
                   intake.suck(true, true);
                }
                else {
                    intake.suck(false, true);
                }
            }
            //if odometry fails, shoot without aiming
            else if(gamepad2.left_trigger_pressed && aim_override){

                drivetrain.aim(false);
                launcher.shooterRight.setRPM(fallBackSpeed);
                launcher.shooterLeft.setRPM(fallBackSpeed);
                if (gamepad2.right_trigger_pressed) {
                    intake.suck(true, true);
                }
                else {
                    intake.suck(false, true);
                }
            }
            else
            {
                drivetrain.aim(false);
                launcher.shooterRight.setRPM(0);
                launcher.shooterLeft.setRPM(0);
            }

            telemetry.addLine("leftOn: " + leftOn);
            telemetry.addLine("hood: " + hood);
            telemetry.addLine("aim override: " + aim_override);
            previous_dpad_up = gamepad2.dpad_up;
            previous_dpad_down = gamepad2.dpad_down;
            launcher.update();
            intake.update();
            Debug.update();
        }


    }

}
