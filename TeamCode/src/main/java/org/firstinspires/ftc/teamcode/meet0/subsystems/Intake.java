package org.firstinspires.ftc.teamcode.meet0.subsystems;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;
import org.firstinspires.ftc.teamcode.meet0.util.CustomPID;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
public class Intake {

    MotorEx intakeMotor;
    CustomPID customPID;
    public Intake(RobotHardware robotHardware, CustomPID intakePid) {
        intakeMotor = robotHardware.intake;
        customPID = intakePid;
    }


    public void suck(Gamepad gamepad)
    {
            if(gamepad.b)
            {
                //calculating what? how do you control the velocity?
                double power = customPID.calculate();
                intakeMotor.set(power);
            }
            else
            {
                intakeMotor.set(0);
            }
    }




}
