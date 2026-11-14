package org.firstinspires.ftc.teamcode.meet1.util.enhancedinput;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.meet1.util.Debug;

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
    boolean buttonState = false;


    @Override
    public boolean get() {
        if (button.get()) {
            buttonState = !buttonState;
        }
        return buttonState;
    }

//    boolean previousState = false;
//    public Toggle getMomentary() {
//        Toggle toggle = new Toggle();
//        boolean currentState = get();
//        toggle.on = currentState && !previousState;
//        toggle.off = !currentState && previousState;
//
//        previousState = currentState;
//        return toggle;
//    }

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
