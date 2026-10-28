package org.firstinspires.ftc.teamcode.meet0.subsystems;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.hardware.ServoEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.util.InterpLUT;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.opmodes.Teleop;
import org.firstinspires.ftc.teamcode.meet0.subsystems.enums.HoodState;
import org.firstinspires.ftc.teamcode.meet0.subsystems.enums.LauncherState;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;
import org.firstinspires.ftc.teamcode.meet0.util.Debug;


public class Launcher {

    private MotorEx motor1, motor2;

    private double targetRPM1, targetRPM2, RPM1, RPM2;

    private boolean ready1, ready2;

    private LauncherState launcherState2, launcherState1 = LauncherState.IDLE;

    public ShooterLeft shooterLeft;
    public ShooterRight shooterRight;

    private ServoEx hoodServo;

    private InterpLUT pollenLUT;

    private InterpLUT nectarLUT;

    public Launcher(RobotHardware robotHardware) {
        this.motor1 = robotHardware.shooter1;
        this.motor2 = robotHardware.shooter2;

        this.hoodServo = robotHardware.hoodServo;

        shooterLeft = new ShooterLeft();
        shooterRight = new ShooterRight();

        pollenLUT = new InterpLUT();
        nectarLUT = new InterpLUT();

        initLUTs();
    }
    public void update() {
        motor1.set(targetRPM1 / (6000 * 0.9));
        motor2.set(targetRPM2 / (6000 * 0.9));
        RPM1 = motor1.getCorrectedVelocity() / motor1.getCPR() * 60;
        RPM2 = motor2.getCorrectedVelocity() / motor2.getCPR() * 60;

        ready1 = Math.abs(RPM1 - targetRPM1) < 100;
        ready2 = Math.abs(RPM2 - targetRPM2) < 100;

        Debug.telemetry.addLine("RPM1: " + RPM1);
        Debug.telemetry.addLine("RPM2: " + RPM2);

        Debug.telemetry.addLine("ready1? " + ready1);
        Debug.telemetry.addLine("ready2? " + ready2);

    }

    public class ShooterLeft {

        public HoodState hoodState;
        public void setRPM(double targetRPM) {
            targetRPM2 = targetRPM;
        }

        public void shoot(double distance) {
            if (hoodState == HoodState.NECTAR) {
                this.setRPM(nectarLUT.get(distance));
            }
            else {
                this.setRPM(pollenLUT.get(distance));
            }
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

        public void setHood(HoodState hoodState) {
            this.hoodState = hoodState;
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
            targetRPM1 = targetRPM;
        }

        public double getRPM() {
            return  RPM1;
        }
        public void shoot(double distance) {
            this.setRPM(pollenLUT.get(distance));
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
    private void initLUTs() {
        nectarLUT.add(0, 2800);
        nectarLUT.add(1000, 2800);
        //this continues
        pollenLUT.add(0,2800);

        pollenLUT.add(1000,2800);
        //same here
    }
}

