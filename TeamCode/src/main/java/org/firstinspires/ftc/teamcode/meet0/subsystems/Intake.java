package org.firstinspires.ftc.teamcode.meet0.subsystems;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;
import org.firstinspires.ftc.teamcode.meet0.util.CustomPID;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
public class Intake {

    MotorEx intakeMotor;
    CustomPID pid;
    public  Intake(RobotHardware robotHardware, CustomPID pid)
    {
        intakeMotor = robotHardware.intake;
        this.pid = pid;
    }


    public void suck(Gamepad gamepad)
    {
            if(gamepad.b)
            {
                double power = pid.calculate();
                intakeMotor.set(power);
            }
            else
            {
                intakeMotor.set(0);
            }
    }




}
