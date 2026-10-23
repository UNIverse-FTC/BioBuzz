package org.firstinspires.ftc.teamcode.meet0.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet0.util.Debug;
import org.firstinspires.ftc.teamcode.pedro.Constants;

public class PathExecutor {
    private Follower follower;
    private AutoSimple paths;
    private int pathState = 0;

    private HardwareMap hardwareMap;

    private Launcher launcher;

    public PathExecutor(RobotHardware robotHardware) {
        this.hardwareMap = robotHardware.hardwareMap;
    }
    public void init() {
        follower = Constants.create(hardwareMap);
        paths = new AutoSimple();
        follower.setPose(paths.start);
    }

    public void update() {
        follower.update();
        Debug.updateRobot(follower.pose());
        Debug.telemetry.addLine("Current: " + follower.pose().x() + " " + follower.pose().y());
        Debug.telemetry.addLine("Path State: " + pathState);
        switch (pathState) {
            case(0):
                follower.follow(paths.path1());
                pathState++;
                break;
            case(1):
                if (!follower.isBusy()) {
                    follower.follow(paths.path2());
                    pathState++;
                    break;
                }
            case(2):
                if (!follower.isBusy()) {
                    follower.follow(paths.path3());
                    pathState++;
                    break;
                }
            case(3):
                if (!follower.isBusy()) {
                    follower.follow(paths.path4());
                    pathState++;
                    break;
                }
            case(4):
                if (!follower.isBusy()) {
                    follower.follow(paths.path5());
                    pathState++;
                    break;
                }

            case(5):
                if(!follower.isBusy()) {
                    follower.follow(paths.path6());
                    pathState++;
                    break;
                }
        }
    }
}
