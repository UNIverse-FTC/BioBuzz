package org.firstinspires.ftc.teamcode.meet1.util;

import androidx.annotation.NonNull;

import com.bylazar.field.CanvasRotation;
import com.bylazar.field.FieldManager;
import com.bylazar.field.FieldPresetParams;
import com.bylazar.field.PanelsField;
import com.bylazar.field.Style;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.math.Pose;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Debug {

    public static Telemetry telemetry;
    public static FieldManager panelsField;

    public static TelemetryManager panelsTelemetry;


    public static void init(Telemetry telemetryRef) {
        telemetry = telemetryRef;

        panelsField = PanelsField.INSTANCE.getField();
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        panelsField.setOffsets(new FieldPresetParams("UNIverse", -72, -72, CanvasRotation.DEG_90, false, true, false));
    }

    public static void debugPID(double rightTarget, double rightCurrent, double leftTarget, double leftCurrent) {
        panelsTelemetry.addData("Right Target", rightTarget);
        panelsTelemetry.addData("Right Current", rightCurrent);
        panelsTelemetry.addData("Left Target", leftTarget);
        panelsTelemetry.addData("Left Current", leftCurrent);
    }
    public static void updateRobot(Pose robotPose, double targetAngle) {

        panelsField.moveCursor(robotPose.x() - 5, robotPose.y() - 5);
        panelsField.setStyle(new Style("red", "red", 1.0));

        panelsField.rect(10, 10);
        panelsField.setStyle(new Style("red", "blue", 2.0));
        panelsField.moveCursor(robotPose.x(), robotPose.y());
        panelsField.line(robotPose.x() + 20 * Math.cos(robotPose.heading()), robotPose.y() + 20 * Math.sin(robotPose.heading()));
        panelsField.setStyle(new Style("red", "green", 2.0));
        panelsField.line(robotPose.x() + 20 * Math.cos(targetAngle), robotPose.y() + 20 * Math.sin(targetAngle));
//
//        String robotIcon = " ";
//
//        double adjustedHeading = Math.toDegrees(robotPose.heading());
//
//        int octant = (int) Math.round(adjustedHeading / 8);
//
//        switch (octant) {
//            case (0):
//                robotIcon = " \u2192";
//                break;
//            case (1):
//                robotIcon = " \u2197";
//                break;
//            case (2):
//                robotIcon = " \u2191";
//                break;
//            case (3):
//                robotIcon = " \u2196";
//                break;
//            case (4):
//                robotIcon = " \u2190";
//                break;
//            case (5):
//                robotIcon = " \u2199";
//                break;
//            case (6):
//                robotIcon = " \u2193";
//                break;
//            case (7):
//                robotIcon = " \u2198";
//                break;
//            case (8):
//                robotIcon = " \u2192";
//        }
//
//        int adjustedX = (int) Math.round(robotPose.x() / Constants.GRID_RES);
//        int adjustedY = (int) Math.round(robotPose.y() / Constants.GRID_RES);
//        for (int y = 144 / Constants.GRID_RES; y > 0; y--) {
//            StringBuilder line = new StringBuilder();
//            for (int x = 0; x < 144 / Constants.GRID_RES; x++){
//
//                    if (adjustedX == x && adjustedY == y) {
//                        line.append(robotIcon);
//                    }
//                    else {
//                        line.append(" .");
//                    }
//            }
//            telemetry.addLine(line.toString());
//        }

    }



    public  static void updateActiveHive(@NonNull Pose hivePosition) {
        panelsField.moveCursor(hivePosition.x(), hivePosition.y());
        panelsField.rect(10, 20);
    }
    public  static void update() {
        panelsField.update();
        telemetry.update();
        panelsTelemetry.update();
    }
}
