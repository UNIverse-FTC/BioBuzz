package org.firstinspires.ftc.teamcode.meet0.util.enhancedinput;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ContinuousButton implements Button {

    private final Gamepad gamepad;
    private final ButtonType button;
    private boolean previousButton = false;
    public ContinuousButton(Gamepad gamepad, ButtonType button) {
        this.gamepad = gamepad;
        this.button = button;
    }
    boolean returnVal = false;


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
}
