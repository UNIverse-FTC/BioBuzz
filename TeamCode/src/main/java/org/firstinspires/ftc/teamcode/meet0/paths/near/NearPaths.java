package org.firstinspires.ftc.teamcode.meet0.paths.near;

import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class NearPaths {
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(10, 126, 0);
    private final Pose path1 = poseFactory.of(57.6951, 116.8225, -90);
    private final Pose point2Start = poseFactory.of(57.6951, 116.8225, -90);
    private final Pose point2 = poseFactory.of(10, 118, 0);

    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.line(point2Start, point2).linear(point2Start, point2);
    }
}
