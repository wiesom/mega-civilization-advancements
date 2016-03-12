package android.p1p.se.megacivilization;


import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

public class AdvancementBonusDialog implements NumberPicker.OnValueChangeListener {
    public static final String TAG = "AdvancementBonusDialog";
    AlertDialog alertDialog;
    NumberPicker bluePicker;
    NumberPicker greenPicker;
    NumberPicker orangePicker;
    NumberPicker redPicker;
    NumberPicker yellowPicker;
    int bonus = 0;
    boolean wasSuccess = false;

    public AdvancementBonusDialog(final MainActivity activity, int bonus) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity.getSupportActionBar().getThemedContext(), R.style.MyAlertDialogStyle);
        this.bonus = bonus;
        alertDialogBuilder.setTitle("Select bonus (" + bonus + ")");
        View pickerLayout = activity.getLayoutInflater().inflate(R.layout.bonus_picker, null);
        bluePicker = ViewHelper.setPickeValues(pickerLayout, R.id.number_picker_blue);
        greenPicker = ViewHelper.setPickeValues(pickerLayout, R.id.number_picker_green);
        orangePicker = ViewHelper.setPickeValues(pickerLayout, R.id.number_picker_orange);
        redPicker = ViewHelper.setPickeValues(pickerLayout, R.id.number_picker_red);
        yellowPicker = ViewHelper.setPickeValues(pickerLayout, R.id.number_picker_yellow);

        bluePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // Save the value in the number picker
                Log.v(TAG, "onValueChange:" + newVal * 5);

            }
        });

        alertDialogBuilder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Log.v(TAG, "Blue:" + bluePicker.getValue() * 5);
                Log.v(TAG, "Green:" + greenPicker.getValue() * 5);
                activity.blue += bluePicker.getValue() * 5;
                activity.red += redPicker.getValue() * 5;
                Log.v(TAG, "Green:" + activity.green);
                activity.green += greenPicker.getValue() * 5;
                Log.v(TAG, "Green:" + activity.green);
                activity.yellow += yellowPicker.getValue() * 5;
                activity.orange += orangePicker.getValue() * 5;
                activity.upDateValues();
                activity.resetBuyMenu();
                wasSuccess = true;
            }
        });


        alertDialogBuilder.setView(pickerLayout);
        alertDialog = alertDialogBuilder.create();
    }

    public boolean show() {
        alertDialog.show();
        alertDialog.getButton(Dialog.BUTTON_POSITIVE).setEnabled(false);
        bluePicker.setOnValueChangedListener(this);
        greenPicker.setOnValueChangedListener(this);
        redPicker.setOnValueChangedListener(this);
        yellowPicker.setOnValueChangedListener(this);
        orangePicker.setOnValueChangedListener(this);
        Log.v(TAG, "Blue:" + bluePicker.getValue() * 5);
        return wasSuccess;
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        // Save the value in the number picker
        Log.v(TAG, "onValueChange:" + newVal * 5);


        if (ViewHelper.picketCorrectValues(bonus, bluePicker, greenPicker, redPicker, yellowPicker, orangePicker))
            alertDialog.getButton(Dialog.BUTTON_POSITIVE).setEnabled(true);
        else
            alertDialog.getButton(Dialog.BUTTON_POSITIVE).setEnabled(false);
    }

}
