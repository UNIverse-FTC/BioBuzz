package org.firstinspires.ftc.teamcode.meet0.paths;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
public class AutoSimple {
    private final PoseFactory poseFactory = PoseFactory.degrees();

    public final Pose start = poseFactory.of(56, 8, 90);
    private final Pose path1 = poseFactory.of(28, 10, 180);
    private final Pose point2 = poseFactory.of(10, 10, 225);
    private final Pose point3 = poseFactory.of(8, 7, -90);
    private final Pose point4 = poseFactory.of(60, 30, -90);
    private final Pose point4Control1 = poseFactory.of(13, 24, 0);
    private final Pose point5 = poseFactory.of(60, 112, -90);
    private final Pose point6 = poseFactory.of(8, 112, 0);


    public Path path1() {
        return line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return line(path1, point2).linear(path1, point2);
    }

    public Path path3() {
        return line(point2, point3).linear(point2, point3);
    }

    public Path path4() {
        return curve(point3, point4Control1, point4).linear(point3, point4);
    }

    public Path path5() {
        return line(point4, point5).reverseTangent();
    }

    public Path path6() {
        return line(point5, point6).linear(point5, point6);
    }
}
