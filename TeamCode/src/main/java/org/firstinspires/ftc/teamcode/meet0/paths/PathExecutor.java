package org.firstinspires.ftc.teamcode.meet0.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.pedro.Constants;

public class PathExecutor {
    private Timer pathTimer;
    private Follower follower;
    private Paths paths;
    private int pathState = 0;

    private HardwareMap hardwareMap;

    private Launcher launcher;

    public PathExecutor(RobotHardware robotHardware) {
        this.hardwareMap = robotHardware.hardwareMap;
    }
    public void init(Launcher launcher) {
        this.launcher = launcher;
        pathTimer = new Timer();
        follower = Constants.create(hardwareMap);
        paths = new Paths();
    }

    public void update() {
        follower.update();
        switch (pathState) {
            case(0):
                follower.follow(paths.path1());
                pathTimer.reset();
                pathState++;
                break;
            case(1):
                if (!follower.isBusy()) {
                    follower.follow(paths.path2());
                    pathTimer.reset();
                    pathState++;
                    break;
                }
            case (2):
                if(!follower.isBusy()) {
                    launcher.shooterLeft.setRPM(2000);
                    pathTimer.reset();
                    pathState++;
                }
            case(3):
                if (!follower.isBusy()) {
                    follower.follow(paths.path3());
                    pathTimer.reset();
                    pathState++;
                    break;
                }
            case(4):
                if (!follower.isBusy()) {
                    follower.follow(paths.path4());
                    pathTimer.reset();
                    pathState++;
                    break;
                }
            case(5):
                if (!follower.isBusy()) {
                    follower.follow(paths.path5());
                    pathTimer.reset();
                    pathState++;
                    break;
                }
        }
    }
}
