package org.firstinspires.ftc.teamcode.offseason.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.ServoEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.offseason.util.Constants;

public class RobotHardware {
    public MotorEx fL, fR, bL, bR, launcher1, launcher2, intake, turret;
    public ServoEx blocker, hood;
    public MecanumDrive mecanum;

    public RobotHardware(HardwareMap hardwareMap) {
        init(hardwareMap);
    }

    //Initialize robot hardware
    public void init(HardwareMap hardwareMap) {

        //Declare drivetrain motors
        fL = new MotorEx(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312);
        fR = new MotorEx(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312);
        bL = new MotorEx(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312);
        bR = new MotorEx(hardwareMap, "backRight", Motor.GoBILDA.RPM_312);

        bL.setInverted(true);

        mecanum = new MecanumDrive(fL, fR, bL, bR);
        mecanum.setRightSideInverted(true);

        intake = new MotorEx(hardwareMap, "intakeMotor", Motor.GoBILDA.BARE);

    }
}
