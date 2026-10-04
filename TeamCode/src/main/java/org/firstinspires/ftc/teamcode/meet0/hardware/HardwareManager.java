package org.firstinspires.ftc.teamcode.meet0.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class HardwareManager {

    public HardwareManager(HardwareMap hardwareMap) {
        init(hardwareMap);
    }
    public MotorEx frontLeft, backLeft, frontRight, backRight, shooter1, shooter2, intake;
    public MecanumDrive mecanum;
    private void init(HardwareMap hardwareMap) {
        // Hardware initialization goes here.

        frontLeft = new MotorEx(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312);
        frontRight = new MotorEx(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312);
        backLeft = new MotorEx(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312);
        backRight = new MotorEx(hardwareMap, "backRight", Motor.GoBILDA.RPM_312);

        shooter1 = new MotorEx(hardwareMap, "shooter1", Motor.GoBILDA.BARE);
        shooter1 = new MotorEx(hardwareMap, "shooter2", Motor.GoBILDA.BARE);

        mecanum = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
        mecanum.setRightSideInverted(true);
    }
}
