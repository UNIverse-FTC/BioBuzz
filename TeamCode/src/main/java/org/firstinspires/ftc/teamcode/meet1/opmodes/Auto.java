package org.firstinspires.ftc.teamcode.meet1.opmodes;

import static org.firstinspires.ftc.teamcode.meet1.subsystems.enums.DrivetrainState.AUTO;
import static org.firstinspires.ftc.teamcode.meet1.util.enhancedinput.ButtonType.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.meet1.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet1.paths.PathExecutor;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Intake;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet1.subsystems.Subsystems;
import org.firstinspires.ftc.teamcode.meet1.util.Debug;
import org.firstinspires.ftc.teamcode.meet1.util.enhancedinput.Button;
import org.firstinspires.ftc.teamcode.meet1.util.enhancedinput.FlipFlopButton;
import org.firstinspires.ftc.teamcode.meet1.util.enhancedinput.ToggleButton;

@Autonomous(name = "\uD83E\uDD16 Autonomous")
public class Auto extends LinearOpMode {


    public void runOpMode()
    {

        RobotHardware robotHardware = new RobotHardware(hardwareMap);
        Launcher launcher = new Launcher(robotHardware);
        Intake intake = new Intake(robotHardware);
        Drivetrain drivetrain = new Drivetrain(robotHardware);

        Subsystems subsystems = new Subsystems(intake, launcher, drivetrain);

        PathExecutor pathExecutor = new PathExecutor(robotHardware);

        Debug.init(telemetry);


        Button autoSelector = new ToggleButton(gamepad1, A);

        while (opModeInInit()){

            String alliance = "none??";

            if (autoSelector.get()) {
                alliance = "Near & Park";
            }
            else {
                alliance= "Far & Park";

            }

            Debug.telemetry.addLine("A for near, B for far");
            Debug.telemetry.addLine("Selected: " + alliance);
            Debug.update();
        }


        waitForStart();
        pathExecutor.init(subsystems, autoSelector);


        while (opModeIsActive()) {
            pathExecutor.update();
            Debug.update();
        }

        OpModeStorage.autonomousEndPose = pathExecutor.follower.pose();

    }





}
