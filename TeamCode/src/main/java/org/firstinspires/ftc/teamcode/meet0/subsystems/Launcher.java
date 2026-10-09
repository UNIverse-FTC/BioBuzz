package org.firstinspires.ftc.teamcode.meet0.subsystems;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.opmodes.Teleop;
import org.firstinspires.ftc.teamcode.meet0.subsystems.enums.LauncherState;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;


public class Launcher {

    private MotorEx motor1, motor2;

    private Telemetry telemetry;

    private double targetRPM1, targetRPM2, RPM1, RPM2;

    private boolean ready1, ready2;

    private LauncherState launcherState2, launcherState1 = LauncherState.IDLE;

    public ShooterLeft shooterLeft;
    public ShooterRight shooterRight;

    public Launcher(RobotHardware robotHardware) {
        this.motor1 = robotHardware.shooter1;
        this.motor2 = robotHardware.shooter2;
        this.telemetry = robotHardware.telemetry;

        shooterLeft = new ShooterLeft();
        shooterRight = new ShooterRight();
    }
    public void update(Gamepad gamepad) {
        double targetRpm;

        motor1.set(targetRPM1 / (6000 * 0.9));
        motor2.set(targetRPM2 / (6000 * 0.9));
        RPM1 = motor1.getCorrectedVelocity() / motor1.getCPR() * 60;
        RPM2 = motor2.getCorrectedVelocity() / motor2.getCPR() * 60;

        ready1 = Math.abs(RPM1 - targetRPM1) < 100;
        ready2 = Math.abs(RPM2 - targetRPM2) < 100;

        telemetry.addLine("RPM1: " + RPM1);
        telemetry.addLine("RPM2: " + RPM2);

        telemetry.addLine("ready1? " + ready1);
        telemetry.addLine("ready2? " + ready2);

    }

    public class ShooterLeft {
        public void setRPM(double targetRPM) {
            targetRPM2 = targetRPM;
        }

        public double getRPM() {
            return  RPM2;
        }

        public LauncherState getLauncherState() {
            if (ready2) {
                if (RPM2 == 0 & targetRPM2 == 0) {
                    launcherState2 = LauncherState.IDLE;
                }
                else {
                    launcherState2 = LauncherState.READY;
                }
            } else {
                launcherState2 = LauncherState.NOTREADY;
            }
            return launcherState2;
        }
    }

    public class ShooterRight {
        public void setRPM(double targetRPM) {
            targetRPM1 = targetRPM;
        }

        public double getRPM() {
            return  RPM1;
        }

        public LauncherState getLauncherState() {
            if (ready1) {
                if (RPM1 == 0 & targetRPM1 == 0) {
                    launcherState1 = LauncherState.IDLE;
                }
                else {
                    launcherState1 = LauncherState.READY;
                }
            } else {
                launcherState1 = LauncherState.NOTREADY;
            }
            return launcherState1;
        }
    }
}
