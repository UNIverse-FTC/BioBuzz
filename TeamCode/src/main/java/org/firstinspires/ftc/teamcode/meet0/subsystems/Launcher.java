package org.firstinspires.ftc.teamcode.meet0.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;

public class Launcher {

    MotorEx shooter1, shooter2;
    public Launcher(RobotHardware hardwareManager) {
        this.shooter1 = hardwareManager.shooter1;
        this.shooter2 = hardwareManager.shooter2;
    }
    public void launch(Gamepad gamepad) {
        if(gamepad.a) {
            shooter2.set(Constants.SHOOTER_SPEED);
            shooter1.set(Constants.SHOOTER_SPEED);
        }
    }
}
