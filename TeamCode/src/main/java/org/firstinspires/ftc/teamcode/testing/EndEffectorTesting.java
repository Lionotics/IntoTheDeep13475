package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.helpers.GamepadEx;

@TeleOp(name="End Effector Testing", group = "Testing")
public class EndEffectorTesting extends LinearOpMode {
    Robot robot = Robot.getInstance();
    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                robot.ee.rotateUp();
            } else if (gamepad1.b) {
                robot.ee.rotateDown();
            } else if (gamepad1.x) {
                robot.ee.openClaw();
            } else if (gamepad1.y) {
                robot.ee.closeClaw();
            }
            telemetry.addData("EE Claw Position", robot.ee.getClawPosition());
            telemetry.addData("EE Wrist Position", robot.ee.getWristPosition());
            telemetry.update();
        }
    }
}
