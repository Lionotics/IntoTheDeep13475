package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.EndEffector;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.helpers.GamepadEx;

import java.util.List;

@Config
@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {

    Robot robot = Robot.getInstance();
    GamepadEx gp1 = new GamepadEx(), gp2 = new GamepadEx();
    //private BrickAngleDetector bad;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        robot.init(hardwareMap);

        while (opModeIsActive()) {
            gp1.update(gamepad1);
            gp2.update(gamepad2);

            if (gp1.rightBumper.isNewlyPressed()) {
                robot.transfer.next();
            } else if (gp1.leftBumper.isNewlyPressed()) {
                robot.transfer.previous();
            }

            if (gp1.a.isNewlyPressed()) {
                robot.transfer.ee.setClaw(EndEffector.Consts.CLAW_OPEN);
            }

            if (gp1.dpad_up.isCurrentlyPressed() || gp2.dpad_up.isCurrentlyPressed()) {
                robot.vSlides.manualUp();
            } else if (gp1.dpad_down.isCurrentlyPressed() || gp2.dpad_down.isCurrentlyPressed()) {
                robot.vSlides.manualDown();
            } else {
                robot.vSlides.hold();
            }

            if (gp1.dpad_right.isCurrentlyPressed() || gp2.dpad_right.isCurrentlyPressed()) {
                robot.hSlides.slideExtend();
            } else if (gp1.dpad_left.isCurrentlyPressed() || gp2.dpad_left.isCurrentlyPressed()) {
                robot.hSlides.slideRetract();
            } else {
                robot.hSlides.hold();
            }

            robot.driveTrain.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            robot.vSlides.loop();

            telemetry.addData("State", robot.transfer.currentState.name());
            telemetry.update();
        }
    }

}
