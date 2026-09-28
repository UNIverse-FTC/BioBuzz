package org.firstinspires.ftc.teamcode.offseason.subsystems;

import com.seattlesolvers.solverslib.hardware.ServoEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.offseason.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.offseason.subsystems.enums.IntakeState;
import org.firstinspires.ftc.teamcode.offseason.util.Constants;

public class IntakeSubsystem {

    public IntakeState intakeState = IntakeState.IDLE;

    private MotorEx intakeMotor, launcherMotor1, launcherMotor2;

    private ServoEx blockerServo;

    public IntakeSubsystem(RobotHardware robot) {
        init(robot);
    }
    void init(RobotHardware robot) {
        intakeMotor = robot.intake;
//        blockerServo = robot.blocker;
//        launcherMotor1 = robot.launcher1;
//        launcherMotor2 = robot.launcher2;
    }

    public void update() {
        switch (intakeState) {
            case IDLE:
                intakeMotor.set(0.0);
//                blockerServo.set(Constants.Blocker.closedAngle);
                break;
            case INTAKING:
                intakeMotor.set(Constants.Intake.intakeSpeed);
//                blockerServo.set(Constants.Blocker.closedAngle);
                break;
            case LAUNCHING:
                intakeMotor.set(Constants.Intake.intakeSpeed);
//                blockerServo.set(Constants.Blocker.openAngle);
//                launcherMotor2.set(-2000);
//                launcherMotor1.set(-2000);
            case REJECTING:
                break;
        }
    }
}
