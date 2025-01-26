package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Intake {

    public enum Consts {
        CLAW_CLOSE(0.1), CLAW_OPEN(.6),
        PIVOT_BARRIER(0.65), PIVOT_GRAB(0.95), PIVOT_TRANSFER(0),
        ADJUSTER_UP(0.31), ADJUST_PIECE(0);

        public final double pos;

        Consts(double pos) {
            this.pos = pos;
        }
    }

    public Servo claw, pivot, adjuster;

    public void init(HardwareMap hwMap) {
        claw = hwMap.get(Servo.class, "intakeClaw"); // TODO: Get names
        pivot = hwMap.get(Servo.class, "intakePivot");
        adjuster = hwMap.get(Servo.class, "intakeAdjuster");
    }

    public void setClaw(Consts pos) {
        claw.setPosition(pos.pos);
    }

    public void setPivot(Consts pos) {
        pivot.setPosition(pos.pos);
    }

    public void setAdjuster(Consts pos) {
        adjuster.setPosition(pos.pos);
    }


   /* public Action openClaw() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                setClaw(CLAW_OPEN);
                packet.put("Claw State", "Open");
                return false;
            }
        };
    }

    public Action closeClaw() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                setClaw(CLAW_CLOSE);
                packet.put("Claw State", "Open");
                return false;
            }
        };
    }

    public Action transferWrist() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                setWrist(WRIST_UP);
                packet.put("Wrist State", "Transfer");
                return false;
            }
        };
    }

    public Action sideWrist() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                setWrist(WRIST_DOWN);
                packet.put("Wrist State", "Sideways");
                return false;
            }
        };
    }
    public Action transferPivot() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                setPivot(PIVOT_TRANSFER);
                packet.put("Wrist State", "Transfer");
                return false;
            }
        };
    }

    public Action grabPivot() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                setPivot(PIVOT_TRANSFER);
                packet.put("Wrist State", "Down");
                return false;
            }
        };
    }

    public Action barrierPivot() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                setPivot(PIVOT_BARRIER);
                packet.put("Wrist State", "Down");
                return false;
            }
        };
    }*/

    public double clawPos() {
        return claw.getPosition();
    }

    public double adjusterPos() {
        return adjuster.getPosition();
    }

    public double pivotPos() {
        return pivot.getPosition();
    }
}
