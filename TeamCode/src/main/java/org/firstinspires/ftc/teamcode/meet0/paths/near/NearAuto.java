package org.firstinspires.ftc.teamcode.meet0.paths.near;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.meet0.paths.Auto;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Intake;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Subsystems;

import java.util.Timer;

public class NearAuto implements Auto {

    private Intake intake;
    private Launcher launcher;
    private Drivetrain drivetrain;

    private Follower follower;

    private ElapsedTime timer;

    private int pathState = 0;


    NearPaths autoPath = new NearPaths();

    public NearAuto(Subsystems subsystems) {
        this.intake = subsystems.intake;
        this.launcher = subsystems.launcher;
        this.drivetrain = subsystems.drivetrain;
    }

    @Override
    public void initialize() {
        follower = drivetrain.follower;
        timer = new ElapsedTime();
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

