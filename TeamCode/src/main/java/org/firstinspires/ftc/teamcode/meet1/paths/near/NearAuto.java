package org.firstinspires.ftc.teamcode.meet1.paths.near;

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
                if(!follower.isBusy() && timer.seconds() > 10) {
                    double distance = drivetrain.calculateDistance();
                    drivetrain.aim(true);
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
                if(!follower.isBusy() && timer.seconds() > 5) {
                    intake.stoppySloppy();
                    drivetrain.aim(false);
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
                break;
        }
    }


}

