package org.firstinspires.ftc.teamcode.offseason.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.offseason.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.offseason.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.offseason.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.offseason.subsystems.enums.IntakeState;

import com.qualcomm.robotcore.hardware.HardwareMap;


@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {

    RobotHardware robot;
    DriveSubsystem drive;
    IntakeSubsystem intake;

    @Override
    public void runOpMode() {

        //Initialize classes
        robot = new RobotHardware(hardwareMap);
        drive = new DriveSubsystem(robot);
        intake = new IntakeSubsystem(robot);

        waitForStart();
        while(opModeIsActive()) {
            drive.drive(
                    gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x
            );
            handleInput();
            intake.update();
        }
    }
    public void handleInput() {
        if(gamepad1.a) {
            intake.intakeState = IntakeState.INTAKING;
        }
       else if (gamepad1.b) {
           intake.intakeState = IntakeState.LAUNCHING;
       }
       else{
           intake.intakeState = IntakeState.IDLE;
       }

    }
}

