package org.firstinspires.ftc.teamcode.meet1.subsystems;


import com.seattlesolvers.solverslib.hardware.ServoEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.util.InterpLUT;

import org.firstinspires.ftc.teamcode.meet1.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet1.subsystems.enums.LauncherState;
import org.firstinspires.ftc.teamcode.meet1.util.Debug;


public class Launcher {

    private MotorEx motor1, motor2;

    private double targetRPM1, targetRPM2, RPM1, RPM2;

    public boolean ready1, ready2;

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

        Debug.debugPID(targetRPM1, RPM1, targetRPM2, RPM2);


    }

    public void shoot(double distance) {
        shooterLeft.setRPM(pollenLUT.get(distance));
        shooterRight.setRPM(pollenLUT.get(distance));
    }

    public void stop() {
        shooterRight.setRPM(0);
        shooterLeft.setRPM(0);
    }
    public class ShooterLeft {
        public void setRPM(double targetRPM) {
            targetRPM2 = targetRPM;
        }
        public double getRPM() {
            return  RPM2;
        }
    }

    public class ShooterRight {
        public void setRPM(double targetRPM) {
            targetRPM1 = targetRPM;
        }
        public double getRPM() {
            return  RPM1;
        }
    }
    private void initLUTs() {
        nectarLUT.add(-1000, 2800);
        nectarLUT.add(1000, 2800);

        nectarLUT.createLUT();
        //this continues
        pollenLUT.add(0,2400);
        pollenLUT.add(10, 2500);
        pollenLUT.add(20, 2600);
        pollenLUT.add(30, 2700);
        pollenLUT.add(40, 2800);
        pollenLUT.add(50, 2900);
        pollenLUT.add(60,3000);

        pollenLUT.createLUT();
        //same here
    }
}

