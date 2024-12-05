package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.helpers.GamepadEx;

@TeleOp(name="Intake Testing", group="Testing")
public class IntakeTesting extends LinearOpMode {
    Robot robot = Robot.getInstance();
    GamepadEx gp1 = new GamepadEx();
    @Override
    public void runOpMode(){
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.right_trigger > 0.3) {
                robot.intake.spinWheels(gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > 0.3) {
                robot.intake.spinWheels(-gamepad1.left_trigger);
            } else {
                robot.intake.spinWheels(0);
            }

            if (gp1.rightBumper.isNewlyPressed()) {
                robot.intake.incrementState();
            } else if (gp1.leftBumper.isNewlyPressed()) {
                robot.intake.decrementState();
            }

            if (gamepad1.a) {
                robot.intake.setShoulders(Intake.SHOULDER_UP);
            } else if (gamepad1.b) {
                robot.intake.setShoulders(Intake.SHOULDER_DOWN);
            }

            telemetry.addData("Right Shoulder Position", robot.intake.getShoulderRightPosition());
            telemetry.addData("Left Shoulder Position", robot.intake.getShoulderLeftPosition());
            telemetry.addData("Current State", robot.intake.currentState.name());
            telemetry.addData("Wheels Speed:", (gamepad1.right_trigger > 0.3) ?
                    gamepad1.right_trigger : ( (gamepad1.left_trigger > 0.3) ?
                    -gamepad1.left_trigger : 0));
            telemetry.update();

            gp1.update(gamepad1);
        }
    }
}
