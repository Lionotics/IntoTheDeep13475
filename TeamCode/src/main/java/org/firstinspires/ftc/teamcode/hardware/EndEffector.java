package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class EndEffector {

    public enum Consts {
        CLAW_CLOSE(0), CLAW_OPEN(.2),
        PIVOT_TRANSFER(.1), PIVOT_SCORE(1), PIVOT_REST(.5);

        public double pos;

        Consts(double pos) {
            this.pos = pos;
        }
    }

    public Servo claw, pivot;

    public void init(HardwareMap hwMap) {
        claw = hwMap.get(Servo.class, "outtakeClaw"); // TODO: Get names
        pivot = hwMap.get(Servo.class, "eePivot");
    }

    public void setClaw(Consts pos) {
        claw.setPosition(pos.pos);
    }

    public void setPivot(Consts pos) {
        pivot.setPosition(pos.pos);
    }

    //TODO: FIX ACTIONS
    /*public Action openClaw() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                open();
                packet.put("Claw State", "Open");
                return false;
            }
        };
    }

    public Action closeClaw() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                close();
                packet.put("Claw State", "Open");
                return false;
            }
        };
    }

    public Action rotateUpBig() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                bigUp();
                packet.put("Wrist State", "Up");
                return false;
            }
        };
    }

    public Action rotateDownBig() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                bigDown();
                packet.put("Wrist State", "Down");
                return false;
            }
        };
    }
    public Action rotateUpLittle() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                littleUp();
                packet.put("Wrist State", "Up");
                return false;
            }
        };
    }

    public Action rotateDownLittle() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                littleDown();
                packet.put("Wrist State", "Down");
                return false;
            }
        };
    }*/

    public double clawPos() {
        return claw.getPosition();
    }

    public double pivotPos() {
        return pivot.getPosition();
    }
}
