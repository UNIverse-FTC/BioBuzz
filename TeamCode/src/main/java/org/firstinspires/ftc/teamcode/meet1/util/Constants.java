package org.firstinspires.ftc.teamcode.meet1.util;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.math.Pose;

public class Constants {

    //higher number make it less detailed, and numbers closer to zero increase res.
    public static int GRID_RES = 7;
    public static double SHOOTER_POWER = 2500;
    public  static  double FALLBACK_POWER = 2000;

    @Configurable
    public static class Drivetrain {
        public static double KP = 0.56;
        public static double KI = 0.0;
        public static double KD = 0.08;

        public static double KS = 0.0;
        public static double KV = 0.0;
    }

    @Configurable
    public static class Launcher {
        public static double KP = 6;
        public static double KI = 0.0;
        public static double KD = 0.0;

        public static double KS = 0.0;
        public static double KV = 1;
    }

    @Configurable
    public static class Poses {
        public static Pose RED_SOUTH = new Pose(58, 24, Math.toRadians(270));
        public static Pose RED_NORTH = new Pose(58, 120, 0);
        public static Pose BLUE_SOUTH = new Pose(84, 24, Math.toRadians(270));
        public static Pose BLUE_NORTH = new Pose(84, 120, 0);

    }


    public static double SERVO_POLLEN = 0.0;
    public static double SERVO_NECTAR = 0.47;

    public static Pose RED_HIVE_SOUTH = new Pose(58, 58, Math.toRadians(90));
    public static Pose RED_HIVE_NORTH = new Pose(58, 84, Math.toRadians(270));

    public static Pose BLUE_HIVE_SOUTH = new Pose(84, 58, Math.toRadians(90));
    public static Pose BLUE_HIVE_NORTH = new Pose(84, 84, Math.toRadians(270));


    @Configurable
    public  static class Intake
    {
        public  static  double INTAKE_POWER = 0.3;

        public static  double KP = 1.5;
        public static  double KI = 0.0;
        public  static  double KD = 0.0;

        public  static  double KS = 0.0;
        public  static  double KV = 1.075;
        public  static  double sp = 0.0;
        public  static  double pv = 0.0;
    }
}
