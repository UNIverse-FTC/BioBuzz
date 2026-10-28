package org.firstinspires.ftc.teamcode.meet0.subsystems.InputClasses;


import  com.qualcomm.robotcore.hardware.Gamepad;

public class Input {

    private  Gamepad gamepad1;
    private  Gamepad gamepad2;
public  button B;


    public  void UpdateButtons()
    {
        B.updateButton();
    }


    void Init(Gamepad inputGamepad1, Gamepad inputGamepad2)
    {
        this.gamepad1 = inputGamepad1;
        this.gamepad2 = inputGamepad2;
       button B_Button = new button(() -> this.gamepad1.b);
    }


}

