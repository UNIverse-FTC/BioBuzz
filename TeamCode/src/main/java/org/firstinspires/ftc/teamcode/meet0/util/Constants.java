package org.firstinspires.ftc.teamcode.meet0.util;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.util.InterpLUT;
@Configurable
public class Constants {

    public static double SHOOTER_POWER = 2500;

    public static double KP = 1.5;
    public static double KI = 0.0;
    public static double KD = 0.0;

    public static double KS = 0.0;
    public static double KV = 1.075;

    public static double SERVO_POLLEN = 0.0;
    public static double SERVO_NECTAR = 0.47;

    public static Pose HIVE_POSITION = new Pose(58, 58, 0);




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

    public  static class LUT
    {
        public  static  double[] lutValues = {
                1.0, 0.0,
                1.0, 0.0,
                1.0, 0.0
        };



    }
}
