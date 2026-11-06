package org.firstinspires.ftc.teamcode.meet0.util;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.util.InterpLUT;
@Configurable
public class Constants {

    //higher number make it less detailed, and numbers closer to zero increase res.
    public static int GRID_RES = 6;
    public static double SHOOTER_POWER = 2500;
    public  static  double FALLBACK_POWER = 2000;
    public static double KP = 6;
    public static double KI = 0.0;
    public static double KD = 0.0;

    public static double KS = 0.0;
    public static double KV = 0.0075;

    public static double KP_intake = 6;
    public static double KI_intake = 0.0;
    public static double KD_intake = 0.0;

    public static double KS_intake = 0.0;
    public static double KV_intake = 0.0075;

    public static double SERVO_POLLEN = 0.0;
    public static double SERVO_NECTAR = 0.47;

    public static Pose RED_HIVE_SOUTH = new Pose(58, 58, 0);
    public static Pose RED_HIVE_NORTH = new Pose(58, 88, 0);

    public static Pose BLUE_HIVE_SOUTH = new Pose(88, 58, 0);
    public static Pose BLUE_HIVE_NORTH = new Pose(88, 88, 0);


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
