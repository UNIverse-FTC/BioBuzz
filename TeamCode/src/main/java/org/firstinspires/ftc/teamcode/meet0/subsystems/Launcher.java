package org.firstinspires.ftc.teamcode.meet0.subsystems;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;


public class Launcher {

    MotorEx shooter1, shooter2;
    public Launcher(RobotHardware robotHardware) {
        this.shooter1 = robotHardware.shooter1;
        this.shooter2 = robotHardware.shooter2;
    }
    public void launch(Gamepad gamepad) {
        if(gamepad.a) {
            shooter2.set(Constants.Launcher.SHOOTER_POWER);
            shooter1.set(Constants.Launcher.SHOOTER_POWER);
        }
    }
}
