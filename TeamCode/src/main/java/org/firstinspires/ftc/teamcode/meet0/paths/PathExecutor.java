package org.firstinspires.ftc.teamcode.meet0.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Intake;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet0.subsystems.enums.LauncherState;
import org.firstinspires.ftc.teamcode.meet0.util.Debug;
import org.firstinspires.ftc.teamcode.pedro.Constants;

public class PathExecutor {
    public Follower follower;
    private AutoSimple paths;
    private int pathState = 0;

    private HardwareMap hardwareMap;

    private Launcher launcher;

    private Intake intake;
    private LinearOpMode opMode;

    private Timer launchTimer;

    public PathExecutor(RobotHardware robotHardware) {
        this.hardwareMap = robotHardware.hardwareMap;
    }
    public void init(Launcher launcher, Intake intake, LinearOpMode opMode) {
        pathState = 0;
        follower = Constants.create(hardwareMap);
        paths = new AutoSimple();
        follower.setPose(paths.start);
        this.launcher = launcher;
        this.opMode = opMode;
        this.intake = intake;
        launchTimer = new Timer();
    }

    public void update() {
        follower.update();
        Debug.updateRobot(follower.pose(), 10);
        Debug.telemetry.addLine("Current: " + follower.pose().x() + " " + follower.pose().y());
        Debug.telemetry.addLine("Path State: " + pathState);
        switch (pathState) {
            case(0):
                launcher.shooterLeft.setRPM(2000);
                launcher.shooterRight.setRPM(2000);
                if (launcher.shooterLeft.getLauncherState() == LauncherState.READY && launcher.shooterRight.getLauncherState() == LauncherState.READY) {
                    intake.suck(true);
                    launchTimer.reset();
                    pathState++;
                }
                break;

            case(1):
                if (launchTimer.seconds() >= 1.5) {
                    launcher.shooterLeft.setRPM(0);
                    launcher.shooterRight.setRPM(0);
                    intake.suck(false);
                    pathState++;
                }
                break;
            case(2):
                follower.follow(paths.path1());
                pathState++;
                break;
            case(3):
                if (!follower.isBusy()) {
                    intake.suck(true);
                    follower.follow(paths.path2());
                    pathState++;
                }
                break;
            case(4):
                if (!follower.isBusy()) {
                    follower.follow(paths.path3());
                    pathState++;
                }
                break;
            case(5):
                if (!follower.isBusy()) {
                    intake.suck(false);
                    follower.follow(paths.path4());
                    pathState++;
                }
                break;
            case(6):
                if (!follower.isBusy()) {
                    follower.follow(paths.path5());
                    pathState++;
                    break;
                }
                break;
            case(7):
                launcher.shooterLeft.setRPM(2000);
                launcher.shooterRight.setRPM(2000);
                if (!follower.isBusy()) {
                    if (launcher.shooterLeft.getLauncherState() == LauncherState.READY && launcher.shooterRight.getLauncherState() == LauncherState.READY) {
                        intake.suck(true);
                        launchTimer.reset();
                        pathState++;
                    }
                }
                break;
            case(8):
                if (launchTimer.seconds() >= 1.5) {
                    launcher.shooterLeft.setRPM(0);
                    launcher.shooterRight.setRPM(0);
                    intake.suck(false);
                    pathState++;
                }
                break;
            case(9):
                if(!follower.isBusy()) {
                    follower.follow(paths.path6());
                    pathState++;
                }
                break;
            case (10):
                break;
        }
    }
}
