package org.firstinspires.ftc.teamcode.meet0.vision;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraCompatibilityManager;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import  java.util.List;
public class AprilTagLocalizer {


    public List<AprilTagDetection> detections;

        private  AprilTagProcessor aprilTag;
        private  VisionPortal visionPortal;

    public  void init() {
        aprilTag = new AprilTagProcessor.Builder()
                //setting go here
                .build();


        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.addProcessor(aprilTag);
        //setting go here
        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        visionPortal = builder.build();
    }

    public void getDetections() {
        detections = aprilTag.getDetections();

        detections.removeIf(tag -> Math.abs(tag.ftcPose.roll) > 90);


    }

}


