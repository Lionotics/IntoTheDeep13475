package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.helpers.GamepadEx;

@TeleOp(name="Intake Testing", group = "Testing")
public class IntakeTesting extends LinearOpMode {
    Robot robot = Robot.getInstance();
    GamepadEx gp1 = new GamepadEx();

    public static double pivotPos = 0;
    public static double clawPos = 0;
    public static double adjustPos = 0;


    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            if (gp1.a.isCurrentlyPressed()) {
                robot.transfer.intake.pivot.setPosition(pivotPos);
            }
            if (gp1.b.isCurrentlyPressed()) {
                robot.transfer.intake.claw.setPosition(clawPos);
            }
            if (gp1.x.isCurrentlyPressed()) {
                robot.transfer.intake.adjuster.setPosition(adjustPos);
            }

            telemetry.addData("claw", robot.transfer.intake.clawPos());
            telemetry.addData("pivot", robot.transfer.intake.pivotPos());
            telemetry.addData("adjuster", robot.transfer.intake.adjusterPos());
            telemetry.update();
            gp1.update(gamepad1);
        }

    }
}
