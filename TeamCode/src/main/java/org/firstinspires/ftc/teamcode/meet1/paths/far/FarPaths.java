package org.firstinspires.ftc.teamcode.meet1.paths.far;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import com.pedropathing.api.Paths;
public class FarPaths {



    private final PoseFactory poseFactory = PoseFactory.degrees();

    public final Pose start = poseFactory.of(56, 8, 90);
    private final Pose path1 = poseFactory.of(56, 30, 270);
    private final Pose point2Start = poseFactory.of(56, 30, 270);
    private final Pose point2 = poseFactory.of(18, 9, 180);
    private final Pose point3Start = poseFactory.of(18, 9, 180);
    private final Pose point3 = poseFactory.of(8, 8, 270);
    private final Pose point4Start = poseFactory.of(8, 8, 270);
    private final Pose point4 = poseFactory.of(60, 37, 90);
    private final Pose point5 = poseFactory.of(60, 110, 90);
    private final Pose point6Start = poseFactory.of(60, 110, 90);
    private final Pose point6 = poseFactory.of(10.6466, 115.9248, 0);

    public Path path1() {
        return Paths.line(start, path1).constant(path1);
    }

    public Path path2() {
        return Paths.line(point2Start, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.line(point3Start, point3).linear(point3Start, point3);
    }

    public Path path4() {
        return Paths.line(point4Start, point4).linear(point4Start, point4);
    }

    public Path path5() {
        return Paths.line(point4, point5).constant(point5);
    }

    public Path path6() {
        return Paths.line(point6Start, point6).linear(point6Start, point6);
    }
}
