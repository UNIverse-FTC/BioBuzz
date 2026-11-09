package org.firstinspires.ftc.teamcode.meet0.util.enhancedinput;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ToggleButton implements Button {

    private final Button button;

    Runnable trueBinding = null;
    Runnable falseBinding = null;

    public ToggleButton(Gamepad gamepad, ButtonType button) {
        this.button = new MomentarySwitchButton(gamepad, button);
    }

    public ToggleButton(Gamepad gamepad, ButtonType button, Runnable trueBinding, Runnable falseBinding) {
        this.button = new MomentarySwitchButton(gamepad, button);
        this.trueBinding = trueBinding;
        this.falseBinding = falseBinding;
    }
    boolean returnVal = false;


    @Override
    public boolean get() {
        if (button.get()) {
            returnVal = !returnVal;
        }
        return returnVal;
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
