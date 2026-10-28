package org.firstinspires.ftc.teamcode.meet0.util.enhancedinput;

import com.qualcomm.robotcore.hardware.Gamepad;

public class FlipFlopButton implements Button {

    private final Button button1;
    private final Button button2;


    private boolean previousButton = false;
    public FlipFlopButton(Gamepad gamepad, ButtonType button1, ButtonType button2) {
        this.button1 = new IdempotentButton(gamepad, button1);
        this.button2 = new IdempotentButton(gamepad, button2);

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

}
