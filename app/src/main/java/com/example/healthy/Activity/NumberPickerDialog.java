package com.example.healthy.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.NumberPicker;
import androidx.fragment.app.DialogFragment;
import com.example.healthy.logic.AppLogic;

public class NumberPickerDialog extends DialogFragment {
    private NumberPicker.OnValueChangeListener valueChangeListener;
    AppLogic logic = AppLogic.getInstance();


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final NumberPicker numberPicker = new NumberPicker(getActivity());

        numberPicker.setMinValue(10);
        numberPicker.setMaxValue(60);

        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Indtast træning");
        builder.setMessage("Vælg antal minutter");

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker, numberPicker.getValue(), numberPicker.getValue());
                logic.addToHighIntensity(numberPicker.getValue());
                logic.computePoints();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,  numberPicker.getValue(),  numberPicker.getValue());
            }
        });

        builder.setView(numberPicker);
        return builder.create();
    }

    public NumberPicker.OnValueChangeListener getValueChangeListener() {
        return valueChangeListener;
    }

    public void  setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }
}