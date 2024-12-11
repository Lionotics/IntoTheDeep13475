package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.EndEffector;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Slides;
import org.firstinspires.ftc.teamcode.helpers.GamepadEx;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {

    Robot robot = Robot.getInstance();
    GamepadEx gp1 = new GamepadEx(), gp2 = new GamepadEx();


    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        waitForStart();
        robot.init(hardwareMap);
        while (opModeIsActive()) {

//            if (gp1.rightBumper.isNewlyPressed()) {
//                robot.intake.incrementState();
//            } else if (gp1.leftBumper.isNewlyPressed()) {
//                robot.intake.decrementState();
//            }

            if (gamepad2.right_trigger > 0.3) {
                robot.intake.spinWheels(gamepad2.right_trigger);
            } else if (gamepad2.left_trigger > 0.3) {
                robot.intake.spinWheels(-gamepad2.left_trigger);
            } else {
                robot.intake.spinWheels(0);
            }

            if (gp2.a.isNewlyPressed()) {
                robot.ee.toggleClaw();
            }

            if (gp2.x.isNewlyPressed()) {
                robot.ee.toggleWrist();
            }

            if (gp2.b.isNewlyPressed()) {
               robot.intake.toggleShoulder();
            }

            if (gp1.dpad_up.isCurrentlyPressed()) {
                robot.slides.horizontalSlide(-.5);
            } else if (gp1.dpad_down.isCurrentlyPressed()) {
                robot.slides.horizontalSlide(.5);
            }

            if (gp2.dpad_up.isCurrentlyPressed()) {
                robot.slides.manualUp();
            } else if (gp2.dpad_down.isCurrentlyPressed() ) {
                telemetry.clear();
                robot.slides.manualDown();
            }

//            if (gp2.y.isNewlyPressed()) {
//                robot.slides.moveToPosition(Slides.LiftPositions.TOP_BUCKET);
//            } else if (gp2.x.isNewlyPressed()) {
//                robot.slides.moveToPosition(Slides.LiftPositions.BOTTOM_BUCKET);
//            } else if (gp2.b.isNewlyPressed()) {
//                robot.slides.moveToPosition(Slides.LiftPositions.TOP_BAR);
//            } else if (gp2.a.isNewlyPressed()) {
//                robot.slides.moveToPosition(Slides.LiftPositions.BOTTOM);
//            } // else if (gp2.back.isNewlyPressed()) {
//            // robot.slides.moveToPosition(Slides.LiftPositions.TOP_HANG);
//            // }

            robot.slides.loop();


            telemetry.addData("Slide Mode", robot.slides.getLiftState().name());
            telemetry.addData("Horizontal Pos", robot.slides.getHorizontalPos());
            telemetry.addData("Vertical Pos", robot.slides.getVerticalPos());
            telemetry.addData("Vertical Target", robot.slides.getTargetPos());
            telemetry.addData("Vertical Velocity", robot.slides.getVerticalVelo());
            telemetry.addData("PID Output", robot.slides.getPidPower());
            telemetry.addData("Wheels Speed:", (gamepad1.right_trigger > 0.3) ?
                    gamepad1.right_trigger : ( (gamepad1.left_trigger > 0.3) ?
                    -gamepad1.left_trigger : 0));
            telemetry.update();

            robot.driveTrain.driveRobotCentric(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            gp1.update(gamepad1);
            gp2.update(gamepad2);

            telemetry.addData("left_stick_y", gamepad1.left_stick_y);
            telemetry.addData("left_stick_x", gamepad1.left_stick_x);
            telemetry.addData("right_stick_x", gamepad1.right_stick_x);
//            telemetry.addData("Index: ", robot.intake.currentState.name());
            telemetry.addData("Claw status:", EndEffector.clawOpen);
            telemetry.addData("Horizontal: ", robot.slides.getHorizontalPos());
            telemetry.addData("Vertical: ", robot.slides.getVerticalPos());
            telemetry.update();
        }
    }

}
