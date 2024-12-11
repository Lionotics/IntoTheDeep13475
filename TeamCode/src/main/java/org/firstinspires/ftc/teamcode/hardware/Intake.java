package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Intake {

    Servo shoulderRight, shoulderLeft;
    CRServo wheelRight, wheelLeft;
    EndEffector ee;


    public static double SHOULDER_UP = 0.41, SHOULDER_DOWN = 1;
    public boolean shoulderUp;


    public enum IntakeState {
        SPIN, LOCK, TRANSFER, EJECT
    }

    public void init(HardwareMap hwMap) {
        shoulderRight = hwMap.get(Servo.class, "shoulderRight");
        shoulderLeft = hwMap.get(Servo.class, "shoulderLeft");
        wheelRight = hwMap.get(CRServo.class, "wheelRight");
        wheelLeft = hwMap.get(CRServo.class, "wheelLeft");

        ee = Robot.getInstance().ee;
        setShoulders(SHOULDER_UP);
        shoulderUp = true;
    }

    public double getShoulderRightPosition() {return shoulderRight.getPosition();}

    public double getShoulderLeftPosition() {return shoulderRight.getPosition();}

    public void setShoulders(double pos) { //TODO: PRIVATISE!
        shoulderRight.setPosition(pos);
        shoulderLeft.setPosition(pos);
    }

    public void spinWheelRight(double power) {wheelRight.setPower(power);} //TODO: Set to private

    public void spinWheelLeft(double power) {wheelLeft.setPower(power);} //TODO: Set to private

    public void spinWheels(double power) {
        spinWheelRight(power);
        spinWheelLeft(-power);
    }

    public void toggleShoulder() {
        setShoulders((shoulderUp) ? SHOULDER_DOWN : SHOULDER_UP);
        shoulderUp = !shoulderUp;
    }

//    public void spinToLock() {
//        enableWheel = false;
//    }
//
//    public void lockToTransfer() {
//        ee.rotateDown();
//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            ee.close();
//        }).start();
//    }
//
//    public void transferToEject() {
//        new Thread(() -> {
//            try {
//                enableWheel = true;
//                spinWheels(-.6);
//                Thread.sleep(1000);
//                ee.rotateUp();
//                spinWheels(0);
//                enableWheel = false;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
//
//    public void ejectToSpin() {
//        enableWheel = true;
//    }
//
//    public void ejectToTransfer() {
//        new Thread(() -> {
//            try {
//                ee.rotateDown();
//                enableWheel = true;
//                Thread.sleep(1250);
//                spinWheels(.6);
//                Thread.sleep(1000);
//                spinWheels(0);
//                enableWheel = false;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
//
//    public void transferToLock() {
//        ee.open();
//    }
//
//    public void lockToSpin() {
//        enableWheel = true;
//    }
//
//    public IntakeState currentState = IntakeState.SPIN;
//
//    public void incrementState() {
//        switch (currentState) {
//            case SPIN:
//                spinToLock();
//                currentState = IntakeState.LOCK;
//                break;
//            case LOCK:
//                lockToTransfer();
//                currentState = IntakeState.TRANSFER;
//                break;
//            case TRANSFER:
//                transferToEject();
//                currentState = IntakeState.EJECT;
//                break;
//            case EJECT:
//                ejectToSpin();
//                currentState = IntakeState.SPIN;
//                break;
//        }
//    }
//
//    public void decrementState() {
//        switch (currentState) {
//            case LOCK:
//                lockToSpin();
//                currentState = IntakeState.SPIN;
//                break;
//            case TRANSFER:
//                transferToLock();
//                currentState = IntakeState.LOCK;
//                break;
//            case EJECT:
//                ejectToTransfer();
//                currentState = IntakeState.TRANSFER;
//                break;
//            default:
//                break;
//
//        }
//    }
}
