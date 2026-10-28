package org.firstinspires.ftc.teamcode.meet0.subsystems.InputClasses;

import java.util.function.BooleanSupplier;

public class button
{

    private  final  BooleanSupplier binding;
    private  boolean lastInput;
    public button(BooleanSupplier binding)
    {this.binding =  binding;}

    boolean wasPressedThisUpdate()
    {
        return (!lastInput && binding.getAsBoolean());
    }
    boolean getValue() {
        return binding.getAsBoolean();
    }
    void updateButton()
    {
        lastInput = binding.getAsBoolean();

    }
}
