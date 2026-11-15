package org.firstinspires.ftc.teamcode.meet1.subsystems;

import org.firstinspires.ftc.teamcode.meet1.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet1.util.CustomPID;
import org.firstinspires.ftc.teamcode.meet1.util.Debug;


import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
public class Intake {

    MotorEx intakeMotor;
    private double targetRPM = 0;

    private double RPM = 0;
    public Intake(RobotHardware robotHardware) {
        intakeMotor = robotHardware.intake;
    }


    public void suck() {
        targetRPM = 400;
    }
    public void eject() {
        targetRPM = -400;
    }

    public void stoppySloppy() {
        targetRPM = 0;
    }

    public void update() {

        intakeMotor.set(targetRPM / (435 * 0.9));
        RPM = intakeMotor.getCorrectedVelocity() / intakeMotor.getCPR() * 60;

        Debug.panelsTelemetry.addData("Intake Target", targetRPM);
        Debug.panelsTelemetry.addData("Intake RPM", RPM);

//        ready1 = Math.abs(RPM - targetRPM) < 100;
    }




}
