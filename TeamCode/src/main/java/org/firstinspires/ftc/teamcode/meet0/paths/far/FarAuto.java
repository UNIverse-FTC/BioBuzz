package org.firstinspires.ftc.teamcode.meet0.paths.far;

import org.firstinspires.ftc.teamcode.meet0.paths.Auto;
import org.firstinspires.ftc.teamcode.meet0.paths.SuperAuto;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Subsystems;

public class FarAuto extends SuperAuto {

    FarPaths paths = new FarPaths();
    public FarAuto(Subsystems subsystems) {
        super(subsystems);
    }

    @Override
    public void update() {
        switch (pathState) {
            case (0):
                double distance = drivetrain.calculateDistance();
                launcher.shoot(distance);
                pathState++;
                break;
            case (1):
                if (launcher.ready1 && launcher.ready2) {
                    intake.suck();
                    timer.reset();
                    pathState++;
                }
                break;
            case (2):
                if (timer.seconds() > 5) {
                    intake.stoppySloppy();
                    launcher.stop();
                    follower.follow(paths.path2());
                    pathState++;
                }
                break;
            case (3):
                if (!follower.isBusy()) {
                    follower.follow(paths.path3());
                    pathState++;
                }
                break;
        }
    }
}
