package org.firstinspires.ftc.teamcode.meet0.util;

import com.bylazar.field.FieldManager;
import com.bylazar.field.PanelsField;
import com.pedropathing.math.Pose;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Debug {

    public static Telemetry telemetry;

    public static void init(Telemetry telemetryRef) {
        telemetry = telemetryRef;
    }
    public static void updateRobot(Pose robotPose) {
        FieldManager panelsField = PanelsField.INSTANCE.getField();

        panelsField.moveCursor(robotPose.x(), robotPose.y());
        panelsField.circle(10);
    }
}
