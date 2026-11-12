package org.firstinspires.ftc.teamcode.meet1.util.enhancedinput;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ContinuousButton implements Button {

    Runnable trueBinding = null;
    Runnable falseBinding = null;
    private final Gamepad gamepad;
    private final ButtonType button;
    public ContinuousButton(Gamepad gamepad, ButtonType button) {
        this.gamepad = gamepad;
        this.button = button;
    }

    public ContinuousButton(Gamepad gamepad, ButtonType button, Runnable trueBinding, Runnable falseBinding) {
        this.button = button;
        this.gamepad = gamepad;
        this.trueBinding = trueBinding;
        this.falseBinding = falseBinding;
    }

    @Override
    public boolean get() {
        return updateButton();
    }

    private boolean updateButton() {
        switch (button) {
            case A:
                return gamepad.a;
            case B:
                return gamepad.b;
            case X:
                return gamepad.x;
            case Y:
                return gamepad.y;
            case DPAD_UP:
                return gamepad.dpad_up;
            case DPAD_DOWN:
                return gamepad.dpad_down;
            case DPAD_LEFT:
                return gamepad.dpad_left;
            case DPAD_RIGHT:
                return gamepad.dpad_right;
            case LEFT_BUMPER:
                return gamepad.left_bumper;
            case LEFT_TRIGGER:
                return gamepad.left_trigger_pressed;
            case RIGHT_BUMPER:
                return gamepad.right_bumper;
            case RIGHT_TRIGGER:
                return gamepad.right_trigger_pressed;
        }
        return false;
    }

    @Override
    public void update() {
        if (trueBinding != null && get()) {
            trueBinding.run();
        }
        else if (falseBinding != null && !get()) {
            falseBinding.run();
        }
    }
}
