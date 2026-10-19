package org.firstinspires.ftc.teamcode.meet0.util;

import com.bylazar.field.FieldManager;
import com.bylazar.field.PanelsField;
import com.pedropathing.math.Pose;

public class Debug {


    public static void updateRobot(Pose robotPose) {
        FieldManager panelsField = PanelsField.INSTANCE.getField();

        panelsField.moveCursor(robotPose.x(), robotPose.y());
        panelsField.circle(10);
    }
}
