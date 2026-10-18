package org.firstinspires.ftc.teamcode.meet0.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.meet0.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.meet0.paths.PathExecutor;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Intake;
import org.firstinspires.ftc.teamcode.meet0.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.meet0.subsystems.enums.HoodState;
import org.firstinspires.ftc.teamcode.meet0.util.Constants;

@TeleOp(name="Meet0 Auto")
public class Auto extends LinearOpMode {


    public void runOpMode()
    {

        RobotHardware robotHardware = new RobotHardware(hardwareMap, telemetry);
        Drivetrain drivetrain = new Drivetrain(robotHardware);
        Launcher launcher = new Launcher(robotHardware);
        Intake intake = new Intake(robotHardware);
        PathExecutor pathExecutor = new PathExecutor(robotHardware);
        waitForStart();

        pathExecutor.init();
        while (opModeIsActive()) {
            pathExecutor.update();
            launcher.update();

        }
    }



}
