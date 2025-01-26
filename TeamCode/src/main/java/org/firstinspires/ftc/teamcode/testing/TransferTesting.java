package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.helpers.GamepadEx;

import java.util.List;

@Config
@TeleOp(name = "Transfer Testing", group = "Testing")
public class TransferTesting extends LinearOpMode {
    Robot robot = Robot.getInstance();
    GamepadEx gp1 = new GamepadEx(), gp2 = new GamepadEx();

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            gp1.update(gamepad1);

            if (gp1.rightBumper.isNewlyPressed()) {
                robot.transfer.next();
            } else if (gp1.leftBumper.isNewlyPressed()) {
                robot.transfer.previous();
            }

            telemetry.addData("State", robot.transfer.currentState.name());
            telemetry.update();
        }
    }
}
