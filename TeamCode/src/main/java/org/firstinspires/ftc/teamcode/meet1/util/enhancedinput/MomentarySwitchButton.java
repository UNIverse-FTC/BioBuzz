package org.firstinspires.ftc.teamcode.meet1.util.enhancedinput;

import com.qualcomm.robotcore.hardware.Gamepad;

public class MomentarySwitchButton implements Button {

    Runnable trueBinding = null;
    Runnable falseBinding = null;
    private final Button button;
    private boolean previousButton = false;
    public MomentarySwitchButton(Gamepad gamepad, ButtonType button) {
        this.button = new ContinuousButton(gamepad, button);
    }
    public MomentarySwitchButton(Gamepad gamepad, ButtonType button, Runnable trueBinding) {
        this.button = new ContinuousButton(gamepad, button);
        this.trueBinding = trueBinding;
    }
    public MomentarySwitchButton(Gamepad gamepad, ButtonType button, Runnable trueBinding, Runnable falseBinding) {
        this.button = new ContinuousButton(gamepad, button);
        this.trueBinding = trueBinding;
        this.falseBinding = falseBinding;
    }


    boolean returnVal = false;


    @Override
    public boolean get() {
        boolean currentButton = button.get();
        returnVal = currentButton && !previousButton;
        previousButton = button.get();
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
