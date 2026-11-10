package org.firstinspires.ftc.teamcode.meet1.util.enhancedinput;

import com.qualcomm.robotcore.hardware.Gamepad;

public class FlipFlopButton implements Button {

    private final Button button1;
    private final Button button2;

    Runnable trueBinding = null;
    Runnable falseBinding = null;

    private boolean previousButton = false;
    public FlipFlopButton(Gamepad gamepad, ButtonType button1, ButtonType button2) {
        this.button1 = new MomentarySwitchButton(gamepad, button1);
        this.button2 = new MomentarySwitchButton(gamepad, button2);

    }

    public FlipFlopButton(Gamepad gamepad, ButtonType button1, ButtonType button2, Runnable trueBinding, Runnable falseBinding) {
        this.button1 = new MomentarySwitchButton(gamepad, button1);
        this.button2 = new MomentarySwitchButton(gamepad, button2);
        this.trueBinding = trueBinding;
        this.falseBinding = falseBinding;
    }
    boolean returnVal = false;


    @Override
    public boolean get() {
        if (button1.get()) {
            if (!returnVal) {
                return returnVal = true;
            }
        }
        if (button2.get()) {
            if (returnVal) {
                return returnVal = false;
            }
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
