package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Config
public class Transfer {

    private static final Logger log = LoggerFactory.getLogger(Transfer.class);
    public EndEffector ee = new EndEffector();
    public Intake intake = new Intake();

    public void init(HardwareMap hwMap) {
        intake.init(hwMap);
        ee.init(hwMap);
    }

    public enum TransitionStates {
        START(-1), INTO_BARRIER(0), HOVER(1), GRAB(2),
        EXIT_BARRIER(3), TRANSFER(4), SCORE(5);

        public final int order;

        TransitionStates(int order) {
           this.order = order;
        }

        private static TransitionStates next(TransitionStates prev) {
           if (prev == SCORE) {
                return TransitionStates.INTO_BARRIER;
           }
           int order = prev.order;
           return TransitionStates.values()[order + 1];
        }

        private static TransitionStates prev(TransitionStates current) {
            if (current == INTO_BARRIER) {
                return TransitionStates.SCORE;
            }
            int order = current.order;
            return TransitionStates.values()[order - 1];
        }
    }

    public TransitionStates currentState = TransitionStates.START;

    private void transition(TransitionStates state) {
        switch (state) {
            case INTO_BARRIER:
                intake.setPivot(Intake.Consts.PIVOT_BARRIER);
                intake.setClaw(Intake.Consts.CLAW_OPEN);
                intake.setAdjuster(Intake.Consts.ADJUSTER_UP);
                ee.setPivot(EndEffector.Consts.PIVOT_REST);
                ee.setClaw(EndEffector.Consts.CLAW_CLOSE);
                break;
            case HOVER:
                intake.setPivot(Intake.Consts.PIVOT_GRAB);
                intake.setClaw(Intake.Consts.CLAW_OPEN);
                intake.setAdjuster(Intake.Consts.ADJUSTER_UP);
                ee.setPivot(EndEffector.Consts.PIVOT_REST);
                ee.setClaw(EndEffector.Consts.CLAW_OPEN);
                break;
            case GRAB:
                intake.setPivot(Intake.Consts.PIVOT_GRAB);
                intake.setClaw(Intake.Consts.CLAW_CLOSE);
                intake.setAdjuster(Intake.Consts.ADJUSTER_UP);
                ee.setPivot(EndEffector.Consts.PIVOT_REST);
                ee.setClaw(EndEffector.Consts.CLAW_OPEN);
                break;
            case EXIT_BARRIER:
                intake.setPivot(Intake.Consts.PIVOT_BARRIER);
                intake.setClaw(Intake.Consts.CLAW_CLOSE);
                intake.setAdjuster(Intake.Consts.ADJUSTER_UP);
                ee.setPivot(EndEffector.Consts.PIVOT_REST);
                ee.setClaw(EndEffector.Consts.CLAW_OPEN);
                break;
            case TRANSFER:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            intake.setPivot(Intake.Consts.PIVOT_TRANSFER);
                            Thread.sleep(500);
                            intake.setClaw(Intake.Consts.CLAW_OPEN);
                            intake.setAdjuster(Intake.Consts.ADJUST_PIECE);
                            Thread.sleep(750);
                            intake.setAdjuster(Intake.Consts.ADJUSTER_UP);
                            ee.setPivot(EndEffector.Consts.PIVOT_TRANSFER);
                            Thread.sleep(500);
                            ee.setClaw(EndEffector.Consts.CLAW_CLOSE);
                        } catch (InterruptedException e) {
                            log.error("Thread interrupted", e);
                        }
                    }
                }.run();
                break;
            case SCORE:
                intake.setPivot(Intake.Consts.PIVOT_TRANSFER);
                intake.setClaw(Intake.Consts.CLAW_OPEN);
                intake.setAdjuster(Intake.Consts.ADJUSTER_UP);
                ee.setPivot(EndEffector.Consts.PIVOT_SCORE);
                ee.setClaw(EndEffector.Consts.CLAW_CLOSE);
        }
    }

     public void next() {
        TransitionStates state = TransitionStates.next(currentState);
        transition(state);
        currentState = state;
     }

     public void previous() {
        if (currentState == TransitionStates.START || currentState == TransitionStates.TRANSFER
                || currentState == TransitionStates.SCORE) {
            return;
        }
        TransitionStates state = TransitionStates.prev(currentState);
        transition(state);
        currentState = state;
     }
}
