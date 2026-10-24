package org.firstinspires.ftc.teamcode.meet0.opmodes;

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
        waitForStart();

        boolean leftOnLast = false;
        boolean leftOn = false;

//        boolean rightOnLast = false;
//        boolean rightOn = false;

        boolean hoodLast = false;
        boolean hood = false;

        boolean intakeLast = false;
        boolean intakeActive = false;

        boolean speedmodeLast = false;
        boolean speedmode = false;

        boolean prev_gamepad2_a = false;

        double speed = 1;

        while (opModeIsActive()) {

//            telemetry.addLine("Servo Position: " + robotHardware.hoodServo.getRawPosition());
            drivetrain.Drive(
                    gamepad1.right_stick_x * speed,
                    gamepad1.left_stick_y * speed,
                    gamepad1.left_stick_x * speed);
            boolean leftOnCurrent = gamepad1.a;
            boolean rightOnCurrent = gamepad1.b;
            boolean hoodCurrent = gamepad1.x;
            boolean intakeCurrent = gamepad1.right_bumper;
            boolean speedmodeCurrent = gamepad1.left_bumper;




            if (gamepad2.a && !prev_gamepad2_a) {
                drivetrain.resetPos();
            }

            prev_gamepad2_a = gamepad2.a;

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
            if (!intakeActive) {
                intake.suck(false);
            } else {
                intake.suck(true);
            }


//            if (hood) {
//                launcher.shooterLeft.setHood(HoodState.NECTAR);
//            } else {
//                launcher.shooterLeft.setHood(HoodState.POLLEN);
//            }
            //FANCIER SHOOTING?
            if (gamepad1.left_trigger_pressed) {
                drivetrain.aim(true);
                launcher.shooterLeft.shoot(drivetrain.calculateDistance());
                launcher.shooterRight.shoot(drivetrain.calculateDistance());

//                Debug.telemetry.addLine("Distance: " + drivetrain.calculateDistance());
                if (gamepad1.right_trigger_pressed) {
                   intake.suck(true);
                }
                else {
                    intake.suck(false);
                }
            }
            else {
                drivetrain.aim(false);
                launcher.shooterRight.setRPM(0);
                launcher.shooterLeft.setRPM(0);
            }

            telemetry.addLine("leftOn: " + leftOn);
            telemetry.addLine("hood: " + hood);

//
            launcher.update();
            intake.update();
            Debug.update();
        }
    }

}
