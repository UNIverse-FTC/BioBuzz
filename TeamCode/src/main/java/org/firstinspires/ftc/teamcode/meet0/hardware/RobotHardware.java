package org.firstinspires.ftc.teamcode.meet0.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.ServoEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor.RunMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;
import org.firstinspires.ftc.teamcode.meet0.util.CustomPID;

public class RobotHardware {
    public RobotHardware(HardwareMap hardwareMap) {
        init(hardwareMap);

    }
    public MotorEx frontLeft, backLeft, frontRight, backRight, shooter1, shooter2, intake;
    public HardwareMap hardwareMap;
    public MecanumDrive mecanum;

    public ServoEx hoodServo;

    public CustomPID intakePID;

    public VoltageSensor voltage;
    private void init(HardwareMap hardwareMap) {

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
//        hoodServo = new ServoEx(hardwareMap, "hoodServo");
//
//
        shooter1 = new MotorEx(hardwareMap, "pollenMotor", Motor.GoBILDA.BARE);
        shooter2 = new MotorEx(hardwareMap, "nectarMotor", Motor.GoBILDA.BARE);

        shooter1.setRunMode(RunMode.VelocityControl);
        shooter2.setInverted(true);
        shooter2.setRunMode(RunMode.VelocityControl);

        shooter1.setVeloCoefficients(Constants.KP,Constants.KI,Constants.KD);
        shooter1.setFeedforwardCoefficients(Constants.KS, Constants.KV);

        shooter2.setVeloCoefficients(Constants.KP,Constants.KI,Constants.KD);
        shooter2.setFeedforwardCoefficients(Constants.KS, Constants.KV);
//
//
        intake = new MotorEx(hardwareMap, "intakeMotor", Motor.GoBILDA.RPM_435);

        intake.setRunMode(RunMode.VelocityControl);

        intake.setVeloCoefficients(Constants.KP,Constants.KI,Constants.KD);
        intake.setFeedforwardCoefficients(Constants.KS, Constants.KV);

        mecanum = new MecanumDrive(frontLeft, backRight, backLeft, frontRight);
        mecanum.setRightSideInverted(true);

        intakePID = new CustomPID(
                Constants.Intake.KP,
                Constants.Intake.KI,
                Constants.Intake.KD,
                Constants.Intake.KV,
                Constants.Intake.KS,
                Constants.Intake.sp,
                Constants.Intake.pv
        );
    }

}
