package org.firstinspires.ftc.teamcode.meet1.paths.near;

import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.meet1.paths.SuperAuto;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Subsystems;

public class NearAuto extends SuperAuto {
    NearPaths autoPath = new NearPaths();

    public NearAuto(Subsystems subsystems) {
        super(subsystems);
    }

    @Override
    public void update() {
        switch (pathState) {
            case (0):
                follower.follow(autoPath.path1());
                pathState++;
                timer.reset();
                break;
            case (1):
                if(!follower.isBusy() && timer.seconds() > 5) {
                    double distance = drivetrain.calculateDistance();
                    launcher.shoot(distance);
                    pathState++;
                }
                break;
            case (2):
                if(!follower.isBusy() && launcher.ready1 && launcher.ready2) {
                    intake.suck();
                    timer.reset();
                    pathState++;
                }
                break;
            case (3):
                if(!follower.isBusy() && timer.seconds() > 1) {
                    intake.stoppySloppy();
                    launcher.stop();
                    pathState++;
                }
                break;
            case(4):
                if(!follower.isBusy()) {
                    follower.follow(autoPath.path2());
                    pathState++;
                }
                break;
            case(5):
                if (!follower.isBusy()) {
                    pathState++;
                }
                break;
            case (6):
                break;
        }
    }

    @Override
    public Pose getStart() {
        return autoPath.start;
    }
}

