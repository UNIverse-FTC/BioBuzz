package org.firstinspires.ftc.teamcode.visiontesting;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.opencv.features2d.ORB;
public class SLAM extends LinearOpMode {

    WebcamName webcamName = hardwareMap.get(WebcamName.class, "NAME_OF_CAMERA_IN_CONFIG_FILE");
    OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName);

    ORB orb = ORB.create(5000);

    @Override
    public void runOpMode() throws InterruptedException {

    }
}
