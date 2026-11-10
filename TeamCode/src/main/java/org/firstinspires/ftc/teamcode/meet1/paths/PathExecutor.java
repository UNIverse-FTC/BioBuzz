package org.firstinspires.ftc.teamcode.meet1.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.meet1.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet1.paths.far.FarAuto;
import org.firstinspires.ftc.teamcode.meet1.paths.far.FarPaths;
import org.firstinspires.ftc.teamcode.meet1.paths.near.NearAuto;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Intake;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Subsystems;
import org.firstinspires.ftc.teamcode.meet1.util.enhancedinput.Button;

public class PathExecutor {
    public Follower follower;
    private FarPaths paths;
    private int pathState = 0;

    private HardwareMap hardwareMap;
    private LinearOpMode opMode;

    private Timer launchTimer;

    private Intake intake;
    private Drivetrain drivetrain;
    private Launcher launcher;
    private Auto auto;
    public PathExecutor(RobotHardware robotHardware) {
        this.hardwareMap = robotHardware.hardwareMap;
    }
    public void init(Subsystems subsystems, Button auto) {
        if (auto.get()) {
            this.auto = new NearAuto(subsystems);
        }
        else {
            this.auto = new FarAuto(subsystems);
        }
        this.launcher = subsystems.launcher;
        this.drivetrain = subsystems.drivetrain;
        this.intake = subsystems.intake;

        follower = drivetrain.follower;
        follower.setPose(paths.start);
    }

    public void update() {
        auto.update();
        drivetrain.update();
        intake.update();
        launcher.update();
    }
}
