package org.firstinspires.ftc.teamcode.meet1.paths;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.meet1.paths.near.NearPaths;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Intake;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Subsystems;

public class SuperAuto implements Auto{

    public Intake intake;
    public Launcher launcher;
    public Drivetrain drivetrain;

    public Follower follower;

    public ElapsedTime timer;

    public int pathState = 0;


    NearPaths autoPath = new NearPaths();

    public SuperAuto(Subsystems subsystems) {
        this.intake = subsystems.intake;
        this.launcher = subsystems.launcher;
        this.drivetrain = subsystems.drivetrain;

        follower = drivetrain.follower;
        timer = new ElapsedTime();
    }
    @Override
    public void update() {

    }
}
