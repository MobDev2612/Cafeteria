package com.persistent.cafeteria.Utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;
import android.widget.TimePicker;

public class CustomTimePicker {
    private static int hour, minute;

    private static TextView dateEdit;
    private static Context mContext;

    /**
     * Default Constructor
     */
    private CustomTimePicker() {
    }

    /**
     * Opens date picker in dialog window
     *
     * @param fragmentManager fragment manager object
     * @param date            qtyText in which date to be populate
     */

    public static void openDatePicker(FragmentManager fragmentManager, final TextView date,Context mcontext) {
        dateEdit = date;
        mContext = mcontext;
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(fragmentManager, "Time");
    }

    public static class DatePickerFragment extends DialogFragment {
        DatePickerDialog.OnDateSetListener onDateSet;


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            return new TimePickerDialog(mContext,
                    timePickerListener, hour, minute,false);
        }

        private TimePickerDialog.OnTimeSetListener timePickerListener =
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int selectedHour,
                                          int selectedMinute) {
                        hour = selectedHour;
                        minute = selectedMinute;
                        dateEdit.setText(new StringBuilder().append(pad(hour))
                                .append(":").append(pad(minute)));

                    }
                };

        private static String pad(int c) {
            if (c >= 10)
                return String.valueOf(c);
            else
                return "0" + String.valueOf(c);
        }

    }

}
