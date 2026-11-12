package org.firstinspires.ftc.teamcode.meet1.util;

import com.pedropathing.math.Pose;

public class Util {
    public static double xDistance;
    public static double yDistance;
    public static double getAimHeading(Pose hivePosition, Pose robotPosition) {

        xDistance = robotPosition.x() - hivePosition.x();
        yDistance = hivePosition.y() - robotPosition.y();

        if (xDistance > 0) {
            return Math.atan(yDistance / xDistance);
        }
        else {
            return Math.atan((yDistance / xDistance) + Math.PI);
        }
    }
}
