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


    public void suck(boolean active)
    {
        if (active) {
            targetRPM = 400;
        }
        else {
            targetRPM = 0;
        }

    }

    public void update() {

        intakeMotor.set(targetRPM / (435 * 0.9));
        RPM = intakeMotor.getCorrectedVelocity() / intakeMotor.getCPR() * 60;

        Debug.telemetry.addLine("Intake RPM: " + RPM);
//        ready1 = Math.abs(RPM - targetRPM) < 100;
    }




}
