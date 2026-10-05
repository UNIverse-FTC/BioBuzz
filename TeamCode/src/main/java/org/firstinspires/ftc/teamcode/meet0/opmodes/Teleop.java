package org.firstinspires.ftc.teamcode.meet0.opmodes;

import static org.firstinspires.ftc.teamcode.meet0.util.Constants.SHOOTER_POWER;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.meet0.hardware.HardwareManager;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;

@TeleOp(name="Meet0 Teleop")
public class Teleop extends LinearOpMode {

    private HardwareManager robotHardware;

    public MotorEx frontLeft, backLeft, frontRight, backRight, shooter1, shooter2, intake;
    public static boolean SHOOTER_ON = false;

    private Drivetrain drivetrain;
    public void runOpMode()
    {

        HardwareManager robotHardware = new HardwareManager(hardwareMap);
        Drivetrain driveTrain = new Drivetrain(robotHardware);
        //Launcher launcher = new Launcher(robotHardware);
        Init();
        waitForStart();



        while (opModeIsActive()) {
            telemetry.update();
//            drivetrain.Drive(
//                    gamepad1.right_stick_x * 0.5,
//                    gamepad1.left_stick_y * 0.5,
//                    gamepad1.left_stick_x * 0.5);
//            launcher.launch(gamepad1);
            if (SHOOTER_ON) {
                frontLeft.set(SHOOTER_POWER);
                backLeft.set(SHOOTER_POWER);
                frontRight.set(SHOOTER_POWER);
                backRight.set(SHOOTER_POWER);
            } else {
                frontLeft.set(0);
                backLeft.set(0);
                frontRight.set(0);
                backRight.set(0);
            }
        }
    }


    public void Init() {
        frontLeft = new MotorEx(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312);
        frontRight = new MotorEx(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312);
        backLeft = new MotorEx(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312);
        backRight = new MotorEx(hardwareMap, "backRight", Motor.GoBILDA.RPM_312);
    }
}
