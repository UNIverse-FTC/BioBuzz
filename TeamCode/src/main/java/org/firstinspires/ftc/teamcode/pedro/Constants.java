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
                Controller primaryTranslationalForward = Controller.proportional(0.2681616869231215);
                Controller secondaryTranslationalForward = Controller.proportional(0.09907857790585152);
                Controller primaryTranslationalLateral = Controller.proportional(0.41660075493097004);
                Controller secondaryTranslationalLateral = Controller.proportional(0.15392284716980478);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.014014160808223432));
                c.brake.set(Controller.proportionalFeedforward(0.011912036686989917));

                c.headingFeedback.set(Controller.proportional(2.6073529327543437));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04054659489666408, 0.009577310474047583));

                c.linearBrakeCoefficients.set(Matrix.diag(0.07118167297446595, 0.1823810945896308));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.002140371924700055, -3.8791414171414866E-4));

                c.maxAchievableForwardVelocity.set(47.44951527628261);
                c.maxAchievableStrafeVelocity.set(34.614863127366846);
                c.naturalForwardDeceleration.set(40.399578515596566);
                c.naturalStrafeDeceleration.set(54.27800304739682);
            }
    );

    public static Follower create(HardwareMap h) {

        return new Follower(new PinpointLocalizer(h, localizerConfig), new Mecanum(h, drivetrainConfig), new Foresight(foresightConfig));
    }
}