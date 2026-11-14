package org.firstinspires.ftc.teamcode.meet1.hardware;

import com.pedropathing.controllers.Controller;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.ServoEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor.RunMode;

import org.firstinspires.ftc.teamcode.meet1.util.Constants;
import org.firstinspires.ftc.teamcode.meet1.util.CustomPID;

public class RobotHardware {
    public MotorEx frontLeft, backLeft, frontRight, backRight, shooter1, shooter2, intake;
    public HardwareMap hardwareMap;
    public MecanumDrive mecanum;
    public ServoEx hoodServo;
    public VoltageSensor voltage;

    public CustomPID aimPID;

    public Controller headingPID;
    public RobotHardware(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        voltage = hardwareMap.get(VoltageSensor.class, "Control Hub");

        frontLeft = new MotorEx(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312);
        frontLeft.setInverted(true);
        frontRight = new MotorEx(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312);
        frontRight.setInverted(true);
        backLeft = new MotorEx(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312);
        backLeft.setInverted(true);
        backRight = new MotorEx(hardwareMap, "backRight", Motor.GoBILDA.RPM_312);
        backRight.setInverted(true);

        shooter1 = new MotorEx(hardwareMap, "pollenMotor", Motor.GoBILDA.BARE);
        shooter2 = new MotorEx(hardwareMap, "nectarMotor", Motor.GoBILDA.BARE);

        shooter1.setRunMode(RunMode.VelocityControl);
        shooter2.setInverted(true);
        shooter2.setRunMode(RunMode.VelocityControl);

        shooter1.setVeloCoefficients(Constants.Launcher.KP,Constants.Launcher.KI,Constants.Launcher.KD);
        shooter1.setFeedforwardCoefficients(Constants.Launcher.KS, Constants.Launcher.KV);

        shooter2.setVeloCoefficients(Constants.Launcher.KP,Constants.Launcher.KI,Constants.Launcher.KD);
        shooter2.setFeedforwardCoefficients(Constants.Launcher.KS, Constants.Launcher.KV);

        intake = new MotorEx(hardwareMap, "intakeMotor", Motor.GoBILDA.RPM_435);

        intake.setRunMode(RunMode.VelocityControl);

        intake.setVeloCoefficients(Constants.Intake.KP,Constants.Intake.KI,Constants.Intake.KD);
        intake.setFeedforwardCoefficients(Constants.Intake.KS, Constants.Intake.KV);

        mecanum = new MecanumDrive(frontLeft, backRight, backLeft, frontRight);
        mecanum.setRightSideInverted(true);

        aimPID = new CustomPID(
                Constants.Drivetrain.KP,
                Constants.Drivetrain.KI,
                Constants.Drivetrain.KD,
                Constants.Drivetrain.KV,
                Constants.Drivetrain.KS,
                0,
                0
        );

        headingPID = Controller.pid(
                Constants.Drivetrain.KP,
                Constants.Drivetrain.KI,
                Constants.Drivetrain.KD
                );

    }

}
