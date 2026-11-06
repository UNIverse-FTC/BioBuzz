package org.firstinspires.ftc.teamcode.meet0.subsystems;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;
import org.firstinspires.ftc.teamcode.meet0.util.CustomPID;
import org.firstinspires.ftc.teamcode.meet0.util.Debug;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
public class Intake {

    MotorEx intakeMotor;
    CustomPID customPID;

    private double targetRPM = 0;

    private double RPM = 0;
    public Intake(RobotHardware robotHardware) {
        intakeMotor = robotHardware.intake;
        customPID = robotHardware.intakePID;
    }


    public void suck() {
        targetRPM = 400;
    }
    public void eject() {
        targetRPM = -400;
    }

    public void stopSTOPSTOPITSTOP() {
        targetRPM = 0;
    }

    public void update() {

        intakeMotor.set(targetRPM / (435 * 0.9));
        RPM = intakeMotor.getCorrectedVelocity() / intakeMotor.getCPR() * 60;

        Debug.panelsTelemetry.addData("Intake Target", targetRPM);
        Debug.panelsTelemetry.addData("Intake RPM", RPM);

        Debug.telemetry.addLine("Target RPM: " + targetRPM);
//        ready1 = Math.abs(RPM - targetRPM) < 100;
    }




}
