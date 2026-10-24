package org.firstinspires.ftc.teamcode.meet0.util;

import com.bylazar.field.CanvasRotation;
import com.bylazar.field.FieldManager;
import com.bylazar.field.FieldPresetParams;
import com.bylazar.field.PanelsField;
import com.bylazar.field.Style;
import com.pedropathing.math.Pose;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Debug {

    public static Telemetry telemetry;
    public static FieldManager panelsField;


    public static void init(Telemetry telemetryRef) {
        telemetry = telemetryRef;

        panelsField = PanelsField.INSTANCE.getField();
        panelsField.setOffsets(new FieldPresetParams("UNIverse", -72, -72, CanvasRotation.DEG_90, false, true, false));
    }
    public static void updateRobot(Pose robotPose, double targetAngle) {

        panelsField.moveCursor(robotPose.x() - 5, robotPose.y() - 5);
        panelsField.setStyle(new Style("red", "red", 1.0));

        panelsField.rect(10,10);
        panelsField.setStyle(new Style("red", "blue", 2.0));
        panelsField.moveCursor(robotPose.x(), robotPose.y());
        panelsField.line(robotPose.x() + 20 * Math.cos(robotPose.heading()), robotPose.y() + 20 * Math.sin(robotPose.heading()));
        panelsField.setStyle(new Style("red", "green", 2.0));
        panelsField.line(robotPose.x() + 20 * Math.cos(targetAngle), robotPose.y() + 20 * Math.sin(targetAngle));

    }

    public  static void updateActiveHive(Pose hivePosition) {
        panelsField.moveCursor(hivePosition.x(), hivePosition.y());
        panelsField.rect(10, 20);
    }
    public  static void update() {
        panelsField.update();
        telemetry.update();
    }
}
