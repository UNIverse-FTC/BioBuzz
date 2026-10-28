package org.firstinspires.ftc.teamcode.meet0.util.enhancedinput;

import static org.firstinspires.ftc.teamcode.meet0.util.enhancedinput.Button.*;

import com.qualcomm.robotcore.hardware.Gamepad;

public class EnhancedButton {

    private final Gamepad gamepad;
    private final Button button;
    private Type type;

    private boolean previousButton = false;
    public EnhancedButton(Gamepad gamepad, Button button, Type type) {
        this.gamepad = gamepad;
        this.button = button;
        this.type = type;
    }

    public boolean get() {
        boolean currentButton = updateButton();
        boolean returnVal = false;
        switch (type) {
            case ONCE:
                if (currentButton && !previousButton){
                    returnVal = true;
                }
                else {
                    returnVal = false;
                }
                previousButton = updateButton();
                return returnVal;

            case TRIGGER:
                if (currentButton && !previousButton) {
                    returnVal = !returnVal;
                }
                return returnVal;
            case CONTINUOUS:
                return updateButton();
        }
        return false;
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
