package org.firstinspires.ftc.teamcode.meet0.opmodes;

import org.firstinspires.ftc.teamcode.meet0.subsystems.Intake;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;

@TeleOp(name="Meet0 Teleop")
public class Teleop extends LinearOpMode {


    public void runOpMode()
    {

        RobotHardware robotHardware = new RobotHardware(hardwareMap, telemetry);
        Drivetrain drivetrain = new Drivetrain(robotHardware);
        Launcher launcher = new Launcher(robotHardware);
        Intake intake = new Intake(robotHardware);
        waitForStart();



        while (opModeIsActive()) {
            telemetry.update();
            drivetrain.Drive(
                    gamepad1.right_stick_x * 0.5,
                    gamepad1.left_stick_y * 0.5,
                    gamepad1.left_stick_x * 0.5);
            intake.suck(gamepad1);

            if(gamepad1.a) {
                launcher.shooterLeft.setRPM(Constants.SHOOTER_POWER);
            }
            else
            {
                launcher.shooterLeft.setRPM(0);
            }

            if(gamepad1.b) {
                launcher.shooterRight.setRPM(Constants.SHOOTER_POWER);
            }
            else {
                launcher.shooterRight.setRPM(0);
            }
            launcher.update(gamepad1);

        }
    }



}
