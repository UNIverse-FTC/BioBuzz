package org.firstinspires.ftc.teamcode.meet1.paths.far;

import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.meet1.paths.SuperAuto;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Subsystems;

public class FarAuto extends SuperAuto {

    FarPaths autoPath = new FarPaths();
    public FarAuto(Subsystems subsystems) {
        super(subsystems);
    }

    @SuppressWarnings("DuplicateBranchesInSwitch")
    @Override
    public void update() {
        switch (pathState) {
            case (0):
                follower.follow(autoPath.path1());
                pathState++;
                break;
            case (1):
                if (!follower.isBusy()) {
                    double distance = drivetrain.calculateDistance();
                    launcher.shoot(distance);
                    pathState++;
                }
                break;
            case (2):
                if (launcher.ready1 && launcher.ready2) {
                    timer.reset();
                    intake.suck(1);
                    pathState++;
                }
                break;
            case (3):
                if (timer.seconds() > 1) {
                    intake.stoppySloppy();
                    launcher.stop();
                    follower.follow(autoPath.path2());
                    pathState++;
                }
                break;
            case (4):
                if (!follower.isBusy()) {
                    intake.suck(1);
                    follower.follow(autoPath.path3());
                    pathState++;
                }
                break;
            case (5):
                if (!follower.isBusy()) {
                    intake.stoppySloppy();
                    follower.follow(autoPath.path4());
                    pathState++;
                }
                break;
            case (6):
                if (!follower.isBusy()) {
                    follower.follow(autoPath.path5());
                    pathState++;
                }
                break;
            case (7) :
                if (!follower.isBusy()) {
                    double distance = drivetrain.calculateDistance();
                    launcher.shoot(distance);
                    pathState++;
                }
                break;
            case (8):
                if (launcher.ready1 && launcher.ready2) {
                    timer.reset();
                    intake.suck(1);
                    pathState++;
                }
            case (9):
                if (timer.seconds() > 1) {
                    intake.stoppySloppy();
                    launcher.stop();
                    follower.follow(autoPath.path6());
                    pathState++;
                }
                break;
            case(10):
                if (!follower.isBusy()) {
                    pathState++;
                }
                break;
            case (11):
                break;
        }
    }

    @Override
    public Pose getStart() {
        return autoPath.start;
    }
}
