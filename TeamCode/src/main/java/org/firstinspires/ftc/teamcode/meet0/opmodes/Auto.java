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
        Debug.init(telemetry);

        boolean previous_a = false;
        boolean previous_b = false;

        int selected_alliance = 0;
        while (opModeInInit()){

            String alliance = "none??";

            switch (selected_alliance) {
                case (0):
                    alliance = "A alliance";
                    break;
                case (1):
                    alliance = "B alliance";
                    break;
            }
            Debug.telemetry.addLine("A for red, B for blue");
            Debug.telemetry.addLine("Selected " + alliance);

            if (gamepad1.a && !previous_a) {
                if (selected_alliance == 1) {
                    selected_alliance = 0;
                }
            }
            if (gamepad1.b && !previous_b) {
                if (selected_alliance == 0) {
                    selected_alliance = 1;
                }
            }
            previous_a = gamepad1.a;
            previous_b = gamepad1.b;
            Debug.update();
        }

        pathExecutor.init(launcher, intake,this, selected_alliance);

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
