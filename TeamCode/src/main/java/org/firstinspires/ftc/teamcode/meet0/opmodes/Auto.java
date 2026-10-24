package org.firstinspires.ftc.teamcode.meet0.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.paths.PathExecutor;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Intake;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet0.subsystems.enums.HoodState;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;
import org.firstinspires.ftc.teamcode.meet0.util.Debug;

@Autonomous(name = "\uD83E\uDD16 Autonomous")
public class Auto extends LinearOpMode {


    public void runOpMode()
    {

        RobotHardware robotHardware = new RobotHardware(hardwareMap);
        Launcher launcher = new Launcher(robotHardware);
        Intake intake = new Intake(robotHardware);
        PathExecutor pathExecutor = new PathExecutor(robotHardware);
        pathExecutor.init(launcher, intake,this);
        Debug.init(telemetry);

        waitForStart();


        while (opModeIsActive()) {
            pathExecutor.update();
            Debug.update();
intake.update();
launcher.update();
        }

        OpModeStorage.autonomousEndPose = pathExecutor.follower.pose();

    }





}
