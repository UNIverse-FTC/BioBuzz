package org.firstinspires.ftc.teamcode.meet0.subsystems;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
public class Intake {

    MotorEx intakeMotor;
    public  Intake(RobotHardware robotHardware)
    {
        intakeMotor = robotHardware.intake;
    }


    public void suck(Gamepad gamepad)
    {
            if(gamepad.b)
            {
                intakeMotor.set(Constants.Intake.INTAKE_POWER);
            }
            else
            {
                intakeMotor.set(0);
            }
    }

}
