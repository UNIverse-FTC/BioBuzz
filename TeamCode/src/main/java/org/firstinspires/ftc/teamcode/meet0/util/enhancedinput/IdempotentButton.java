package org.firstinspires.ftc.teamcode.meet0.util.enhancedinput;

import com.qualcomm.robotcore.hardware.Gamepad;

public class IdempotentButton implements Button {

    private final Button button;
    private boolean previousButton = false;
    public IdempotentButton(Gamepad gamepad, ButtonType button) {
        this.button = new ContinuousButton(gamepad, button);
    }
    boolean returnVal = false;


    @Override
    public boolean get() {
        boolean currentButton = button.get();
        returnVal = currentButton && !previousButton;
        previousButton = button.get();
        return returnVal;
    }

}
