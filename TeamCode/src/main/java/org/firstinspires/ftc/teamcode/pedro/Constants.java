package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("frontLeft");
        c.frontRightName.set("frontRight");
        c.backLeftName.set("backLeft");
        c.backRightName.set("backRight");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
    });
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("odom");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(4.472345667561208);
        c.yPodOffset.set(1.3930050406869001);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.2444463296318517);
                Controller secondaryTranslationalForward = Controller.proportional(0.09031638707274492);
                Controller primaryTranslationalLateral = Controller.proportional(0.41777165451323667);
                Controller secondaryTranslationalLateral = Controller.proportional(0.15435546327843927);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.012212591703541885));
                c.brake.set(Controller.proportionalFeedforward(0.010380702948010601));

                c.headingFeedback.set(Controller.proportional(3.4099604169575026));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04905882637107545, 0.007497007228326181));

                c.linearBrakeCoefficients.set(Matrix.diag(0.07222420803720153, 0.060572750637497744));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0020597839622976937, 0.0020635635864313107));

                c.maxAchievableForwardVelocity.set(69.07525508605923);
                c.maxAchievableStrafeVelocity.set(56.37177273224063);
                c.naturalForwardDeceleration.set(30.93085287271853);
                c.naturalStrafeDeceleration.set(67.95137986902762);
            }
    );

    public static Follower create(HardwareMap h) {

        return new Follower(new PinpointLocalizer(h, localizerConfig), new Mecanum(h, drivetrainConfig), new Foresight(foresightConfig));
    }
}