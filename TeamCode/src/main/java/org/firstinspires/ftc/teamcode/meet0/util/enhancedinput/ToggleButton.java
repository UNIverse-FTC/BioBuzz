package org.firstinspires.ftc.teamcode.meet0.util.enhancedinput;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ToggleButton implements Button {

    private final Button button;
    public ToggleButton(Gamepad gamepad, ButtonType button) {
        this.button = new IdempotentButton(gamepad, button);
    }
    boolean returnVal = false;


    @Override
    public boolean get() {
        if (button.get()) {
            returnVal = !returnVal;
        }
        return returnVal;
    }

}
