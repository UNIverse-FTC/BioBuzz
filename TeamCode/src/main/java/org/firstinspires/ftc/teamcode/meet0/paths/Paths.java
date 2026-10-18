package org.firstinspires.ftc.teamcode.meet0.paths;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class Paths {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(57, 9, 90);
    private final Pose path1 = poseFactory.of(57, 24.5284, 90);
    private final Pose point2 = poseFactory.of(18, 10, 250);
    private final Pose point3 = poseFactory.of(9, 9, 270);
    private final Pose point4 = poseFactory.of(32.2695, 38.4456, 90);
    private final Pose point5 = poseFactory.of(57.4594, 120.2094, 270);
    private final Pose point5Control1 = poseFactory.of(22.3693, 117.6339, 0);

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
        return line(point3, point4).linear(point3, point4);
    }

    public Path path5() {
        return curve(point4, point5Control1, point5).linear(point4, point5);
    }
}