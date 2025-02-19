package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.helpers.GamepadEx;

@Config
@TeleOp(name="End Effector Testing", group = "Testing")
public class EndEffectorTesting extends LinearOpMode {
    Robot robot = Robot.getInstance();
    GamepadEx gp1 = new GamepadEx();

    public static double pivotPos = 0;
    public static double clawPos = 0;


    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            if (gp1.a.isCurrentlyPressed()) {
                robot.transfer.ee.pivot.setPosition(pivotPos);
            }
            if (gp1.b.isCurrentlyPressed()) {
                robot.transfer.ee.claw.setPosition(clawPos);
            }

            telemetry.addData("claw",robot.transfer.ee.clawPos());
            telemetry.addData("pivot",robot.transfer.ee.pivotPos());
            telemetry.update();
            gp1.update(gamepad1);
        }

    }
}
