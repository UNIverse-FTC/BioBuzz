package org.firstinspires.ftc.teamcode.meet1.autonomous.near;

import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.meet1.autonomous.enums.Alliance;

public class NearPaths {
    private final PoseFactory poseFactory = PoseFactory.degrees();

    public NearPaths(Alliance alliance) {
        if(alliance == Alliance.BLUE)
        {
            poseFactory.mirrorX(70.75);
            poseFactory.mirrorY(70.75);
        }
    }


    public final Pose start = poseFactory.of(10, 126, 0);
    private final Pose path1 = poseFactory.of(57.6951, 116.8225, 270);
    private final Pose point2Start = poseFactory.of(57.6951, 116.8225, 270);
    private final Pose point2 = poseFactory.of(15, 118, 0);



    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.line(point2Start, point2).linear(point2Start, point2);
    }
}
