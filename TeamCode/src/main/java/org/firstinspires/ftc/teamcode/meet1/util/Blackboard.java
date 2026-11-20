package org.firstinspires.ftc.teamcode.meet1.util;

import com.pedropathing.math.Pose;

public  class Blackboard {
    public static Pose globalPosition;
    public  static Double globalHeading;
    public  static  Pose currentHive;

    public static void UpdateBlackboard()
    {
        if (globalPosition.y() < 72) {
            currentHive = Constants.RED_HIVE_SOUTH;
        }
        else {
            currentHive = Constants.RED_HIVE_NORTH;
        }
    }
}
