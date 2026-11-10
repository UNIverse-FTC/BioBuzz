package org.firstinspires.ftc.teamcode.meet0.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.paths.near.NearAuto;
import org.firstinspires.ftc.teamcode.meet0.paths.near.NearPaths;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Intake;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Subsystems;
import org.firstinspires.ftc.teamcode.meet0.subsystems.enums.LauncherState;
import org.firstinspires.ftc.teamcode.meet0.util.Debug;
import org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.Button;
import org.firstinspires.ftc.teamcode.pedro.Constants;

public class PathExecutor {
    public Follower follower;
    private AutoSimple paths;
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
