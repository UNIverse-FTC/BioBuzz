package org.firstinspires.ftc.teamcode.meet0.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor.RunMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;

public class RobotHardware {
    public Telemetry telemetry;
    public RobotHardware(HardwareMap hardwareMap, Telemetry telemetry) {
        init(hardwareMap);
        this.telemetry = telemetry;

    }
    public MotorEx frontLeft, backLeft, frontRight, backRight, shooter1, shooter2, intake;
    public MecanumDrive mecanum;


    private void init(HardwareMap hardwareMap) {


        frontLeft = new MotorEx(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312);
        frontRight = new MotorEx(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312);
        backLeft = new MotorEx(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312);
        backRight = new MotorEx(hardwareMap, "backRight", Motor.GoBILDA.RPM_312);



        shooter1 = new MotorEx(hardwareMap, "pollenMotor", Motor.GoBILDA.BARE);
        shooter2 = new MotorEx(hardwareMap, "nectarMotor", Motor.GoBILDA.BARE);

        shooter1.setRunMode(RunMode.VelocityControl);
        shooter2.setInverted(true);
        shooter2.setRunMode(RunMode.VelocityControl);

        shooter1.setVeloCoefficients(Constants.KP,Constants.KI,Constants.KD);
        shooter1.setFeedforwardCoefficients(Constants.KS, Constants.KV);

        shooter2.setVeloCoefficients(Constants.KP,Constants.KI,Constants.KD);
        shooter2.setFeedforwardCoefficients(Constants.KS, Constants.KV);



        intake = new MotorEx(hardwareMap, "intakeMotor", Motor.GoBILDA.BARE);

        mecanum = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
        mecanum.setRightSideInverted(true);
    }
}
