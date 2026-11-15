package org.firstinspires.ftc.teamcode.meet1.autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.meet1.autonomous.enums.Alliance;
import org.firstinspires.ftc.teamcode.meet1.autonomous.near.NearPaths;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Intake;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Subsystems;

public class SuperAuto implements Auto {

    public Intake intake;
    public Launcher launcher;
    public Drivetrain drivetrain;

    public Follower follower;

    public ElapsedTime timer;

    public Alliance alliance;

    public int pathState = 0;


    //NearPaths autoPath = new NearPaths();

    public SuperAuto(Subsystems subsystems, Alliance alliance) {
        this.intake = subsystems.intake;
        this.launcher = subsystems.launcher;
        this.drivetrain = subsystems.drivetrain;

        this.alliance = alliance;
        follower = drivetrain.follower;
        timer = new ElapsedTime();
    }
    @Override
    public void update() {

    }

    @Override
    public Pose getStart() {
        return null;
    }
}
