package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.internal.ui.ThemedActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Config
public class Intake {

    Servo shoulderRight, shoulderLeft;
    CRServo wheelRight, wheelLeft;
    EndEffector ee;

    public static double SHOULDER_RIGHT_UP = 0.5, SHOULDER_RIGHT_DOWN = 0.5;
    public static double SHOULDER_LEFT_UP = 0.5, SHOULDER_LEFT_DOWN = 0.5;
    public boolean enableWheel = true;

    private enum IntakeState {
        SPIN, LOCK, RAISE, TRANSFER, EJECT
    }

    public void init(HardwareMap hwMap) {
        hwMap.get(Servo.class, "shoulderRight");
        hwMap.get(Servo.class, "shoulderLeft");
        hwMap.get(CRServo.class, "wheelRight");
        hwMap.get(CRServo.class, "wheelLeft");

        ee.init(hwMap);

        setShoulderRight(SHOULDER_RIGHT_DOWN);
        setShoulderLeft(SHOULDER_LEFT_DOWN);
    }

    private void setShoulderRight(double pos) {shoulderRight.setPosition(pos);}

    private void setShoulderLeft(double pos) {shoulderLeft.setPosition(pos);}

    private void setWheelRight(double power) {wheelRight.setPower(power);}

    private void setWheelLeft(double power) {wheelLeft.setPower(power);}

    public void spinToLock() {
        enableWheel = false;
    }

    public void lockToRaise() {
        setShoulderRight(SHOULDER_RIGHT_UP);
        setShoulderLeft(SHOULDER_LEFT_UP);
    }

    public void raiseToTransfer() {
        setShoulderRight(SHOULDER_RIGHT_DOWN);
        setShoulderLeft(SHOULDER_LEFT_DOWN);
        ee.rotateDown();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ee.close();
        }).start();
    }



    IntakeState intakeState = IntakeState.SPIN;

    public void incrementState() {
        switch (intakeState) {
            case SPIN:
                intakeState = IntakeState.LOCK;
                break;
            case LOCK:
                intakeState = IntakeState.RAISE;
                break;
            case RAISE:
                intakeState = IntakeState.TRANSFER;
                break;
            case TRANSFER:
                intakeState = IntakeState.EJECT;
                break;
            case EJECT:
                intakeState = IntakeState.SPIN;
                break;
        }
    }

    public void decrementState() {
        switch (intakeState) {
            case SPIN:
                intakeState = IntakeState.EJECT;
                break;
            case LOCK:
                intakeState = IntakeState.SPIN;
                break;
            case RAISE:
                intakeState = IntakeState.LOCK;
                break;
            case TRANSFER:
                intakeState = IntakeState.RAISE;
                break;
            case EJECT:
                intakeState = IntakeState.TRANSFER;
                break;
        }
    }
}
