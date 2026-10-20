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
import org.firstinspires.ftc.teamcode.meet0.util.Debug;

@TeleOp(name="Meet0 Teleop")
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

        boolean rightOnLast = false;
        boolean rightOn = false;

        boolean hoodLast = false;
        boolean hood = false;

        boolean aimLast = false;
        boolean aim = false;

        while (opModeIsActive()) {

            telemetry.addLine("Servo Position: " + robotHardware.hoodServo.getRawPosition());
            drivetrain.Drive(
                    gamepad1.right_stick_x * 0.5,
                    gamepad1.left_stick_y * 0.5,
                    gamepad1.left_stick_x * 0.5);
            intake.suck(gamepad1);

            boolean leftOnCurrent = gamepad1.a;
            boolean rightOnCurrent = gamepad1.b;
            boolean hoodCurrent = gamepad1.x;

            boolean aimCurrent = gamepad1.left_trigger_pressed;




            if(leftOnCurrent && !leftOnLast) {
                leftOn = !leftOn;
            }
            if(rightOnCurrent && !rightOnLast) {
                rightOn = !rightOn;
            }
            if(hoodCurrent && !hoodLast) {
                hood = !hood;
            }
            if(aimCurrent && !aimLast) {
                aim = !aim;
            }


            leftOnLast = leftOnCurrent;
            rightOnLast = rightOnCurrent;
            hoodLast = hoodCurrent;
            aimLast = aimCurrent;



            if (!leftOn) {
                launcher.shooterLeft.setRPM(0);
            }
            else {
                launcher.shooterLeft.setRPM(Constants.SHOOTER_POWER);
            }



            if (!rightOn) {
                launcher.shooterRight.setRPM(0);
            }
            else {
                launcher.shooterRight.setRPM(Constants.SHOOTER_POWER);
            }

            if (hood) {
                launcher.shooterLeft.setHood(HoodState.NECTAR);
            }
            else {
                launcher.shooterLeft.setHood(HoodState.POLLEN);
            }

            if (aim) {
                drivetrain.aim(true);
            }
            else {
                drivetrain.aim(false);
            }

            telemetry.addLine("leftOn: " + leftOn);
            telemetry.addLine("rightOn: " + rightOn);
            telemetry.addLine("hood: " + hood);


            launcher.update();
            telemetry.update();
        }
    }



}
