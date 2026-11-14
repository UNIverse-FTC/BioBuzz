package org.firstinspires.ftc.teamcode.meet1.util;

import com.pedropathing.controllers.Controller;
import com.pedropathing.utils.Angle;

public class AimController {
    private final Controller controller = Controller.proportional(
            Constants.Drivetrain.KP
    );

    public double calculate(double target, double current) {

        double error = Angle.normalizeSigned(target - current);
        Debug.panelsTelemetry.addData("Error", error);
        Debug.panelsTelemetry.addData("Error2", target - current);
        controller.calculate(2, 2);
        double output = controller.calculate(target, error, Constants.Drivetrain.KD);

        return Math.max(-0.6, Math.min(0.6, output));
    }
}
