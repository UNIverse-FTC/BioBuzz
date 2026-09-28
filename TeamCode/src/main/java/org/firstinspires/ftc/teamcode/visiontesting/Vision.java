package org.firstinspires.ftc.teamcode.visiontesting;

import android.graphics.Color;
import android.util.Size;

import com.bylazar.camerastream.PanelsCameraStream;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.field.FieldImage;
import com.bylazar.field.FieldManager;
import com.bylazar.field.PanelsField;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.Circle;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ColorSpace;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.opencv.core.Scalar;


import java.util.List;
@Configurable
@TeleOp(name = "Vision Test", group = "Tests")
public class Vision extends LinearOpMode {
    public static Scalar minHSV = new Scalar(20, 100, 130);
    public static Scalar maxHSV = new Scalar(35, 255, 255);

    private double focalLengthInPx = 369.64;
    @Override
    public void runOpMode() {
        ColorBlobLocatorProcessor colorLocator = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(new ColorRange(
                        ColorSpace.HSV,
                        minHSV,
                        maxHSV
                        )
                )
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                .setRoi(ImageRegion.asUnityCenterCoordinates(-1, 1, 1, -1))
                .setDrawContours(true)
                .setBoxFitColor(0)
                .setCircleFitColor(Color.rgb(255, 255, 0))
                .setBlurSize(10)
                .setDilateSize(15)
                .setErodeSize(15)
                .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)
                .build();

        VisionPortal portal = new VisionPortal.Builder()
                .addProcessor(colorLocator)
                .setCameraResolution(new Size(640, 360 ))
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .build();

        telemetry.setMsTransmissionInterval(100);
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);

        FieldManager field = PanelsField.INSTANCE.getField();

        while (opModeIsActive() || opModeInInit()) {
            telemetry.addData("preview on/off", "... Camera Stream\n");

            List<ColorBlobLocatorProcessor.Blob> blobs = colorLocator.getBlobs();

            ColorBlobLocatorProcessor.Util.filterByCriteria(
                    ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA,
                    150, 20000, blobs);

            ColorBlobLocatorProcessor.Util.filterByCriteria(
                    ColorBlobLocatorProcessor.BlobCriteria.BY_CIRCULARITY,
                    0.6, 1, blobs);

            telemetry.addLine("Circularity Radius Center");

            for (ColorBlobLocatorProcessor.Blob b : blobs) {
                Circle circleFit = b.getCircle();

                double distance = (focalLengthInPx * 2.8) / circleFit.getRadius();
                double horizontalRatio = Math.abs(circleFit.getX()) / 320;
                telemetry.addLine(String.format("RATIO: %5.3f math 1: %5.3f", horizontalRatio, (Math.abs(circleFit.getX()) / 160)));
                double horizontalAngle = 24.4 - (horizontalRatio * 24.4);
                double horizontalOffset = distance * Math.tan(Math.toRadians(horizontalAngle));

                field.moveCursor(-1 * horizontalOffset, distance);
                field.circle(2.8);
                field.moveCursor(0,-5);
                field.circle(10);
                telemetry.addLine(String.format("%5.3f      %5.3f     (%3d,%3d) %5.3f, %5.3f",
                        b.getCircularity(), circleFit.getRadius(), (int) circleFit.getX() - 160, (int) circleFit.getY(), distance, horizontalOffset));
            }
            field.update();

            telemetry.update();
            sleep(100);
        }
    }
}
