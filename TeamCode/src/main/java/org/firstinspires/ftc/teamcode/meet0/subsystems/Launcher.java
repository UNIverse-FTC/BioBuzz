package org.firstinspires.ftc.teamcode.meet0.subsystems;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.hardware.ServoEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.opmodes.Teleop;
import org.firstinspires.ftc.teamcode.meet0.subsystems.enums.HoodState;
import org.firstinspires.ftc.teamcode.meet0.subsystems.enums.LauncherState;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;


public class Launcher {

    private MotorEx motor1, motor2;

    private Telemetry telemetry;

    private double targetRPM_R, targetRPM_L, RPM_R, RPM_L;

    private boolean ready_R, ready_L;

    private LauncherState launcherState_L, launcherState_R = LauncherState.IDLE;

    public ShooterLeft shooterLeft;
    public ShooterRight shooterRight;

    private ServoEx hoodServo;

    public Launcher(RobotHardware robotHardware) {
        this.motor1 = robotHardware.shooter1;
        this.motor2 = robotHardware.shooter2;
        this.telemetry = robotHardware.telemetry;

        this.hoodServo = robotHardware.hoodServo;

        shooterLeft = new ShooterLeft();
        shooterRight = new ShooterRight();
    }


    public void update() {
        double targetRpm;

        motor1.set(targetRPM_R / (6000 * 0.9));
        motor2.set(targetRPM_L / (6000 * 0.9));
        RPM_R = motor1.getCorrectedVelocity() / motor1.getCPR() * 60;
        RPM_L = motor2.getCorrectedVelocity() / motor2.getCPR() * 60;

        ready_R = Math.abs(RPM_R - targetRPM_R) < 100;
        ready_L = Math.abs(RPM_L - targetRPM_L) < 100;

        telemetry.addLine("RPM1: " + RPM_R);
        telemetry.addLine("RPM2: " + RPM_L);

        telemetry.addLine("ready1? " + ready_R);
        telemetry.addLine("ready2? " + ready_L);

    }

    public class ShooterLeft {
        public void setRPM(double targetRPM) {
            targetRPM_L = targetRPM;
        }

        public double getRPM() {
            return  RPM_L;
        }

        public LauncherState getLauncherState() {
            if (ready_L) {
                if (RPM_L == 0 & targetRPM_L == 0) {
                    launcherState_L = LauncherState.IDLE;
                }
                else {
                    launcherState_L = LauncherState.READY;
                }
            } else {
                launcherState_L = LauncherState.NOTREADY;
            }
            return launcherState_L;
        }

        public void setHood(HoodState hoodState) {
            if (hoodState == HoodState.POLLEN) {
                hoodServo.set(Constants.SERVO_POLLEN);
            }
            else {
                hoodServo.set(Constants.SERVO_NECTAR);
            }
        }
    }

    public class ShooterRight {
        public void setRPM(double targetRPM) {
            targetRPM_R = targetRPM;
        }


        public double getRPM() {
            return  RPM_R;
        }

        public LauncherState getLauncherState() {
            if (ready_R) {
                if (RPM_R == 0 & targetRPM_R == 0) {
                    launcherState_R = LauncherState.IDLE;
                }
                else {
                    launcherState_R = LauncherState.READY;
                }
            } else {
                launcherState_R = LauncherState.NOTREADY;
            }
            return launcherState_R;
        }
    }
}
