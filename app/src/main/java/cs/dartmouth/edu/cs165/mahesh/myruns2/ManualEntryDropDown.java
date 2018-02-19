package cs.dartmouth.edu.cs165.mahesh.myruns2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class ManualEntryDropDown extends ListActivity {
    static final String[] dropdown = new String[]{"Date", "Time", "Duration", "Distance", "Calories", "Heart Rate", "Comment"};
    Calendar calendar = Calendar.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manualentry_layout);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dropdown);
        setListAdapter(adapter);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String get_data = ((TextView) view).getText().toString();
                switch (get_data) {
                    case "Date":
                        onDateClicked();
                        break;
                    case "Time":
                        onTimeClicked();
                        break;
                    default:
                        getDialogs(get_data);
                }
            }
        };

        ListView listview = getListView();
        listview.setOnItemClickListener(listener);
    }


    /*
    Date button functions
    Reference take from
    https://developer.android.com/reference/android/app/DatePickerDialog.OnDateSetListener.html
     */
    private void onDateClicked() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
            }
        };

        new DatePickerDialog(ManualEntryDropDown.this, listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    /*
        Time button functions
        Reference taken from
        https://developer.android.com/reference/android/app/TimePickerDialog.OnTimeSetListener.html
     */
    private void onTimeClicked() {

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
            }
        };

        new TimePickerDialog(ManualEntryDropDown.this, listener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true).show();
    }

    /**
        Dialog to display the infromation required when clicked.
     */
    private void getDialogs(String str) {
        MyAlertDialogFragment dialog = new MyAlertDialogFragment();
        DialogFragment fragment = dialog.newInstance(str);
        fragment.show(getFragmentManager(), "dialog");
    }

    public void onOk() {
    }

    public void onCancel() {
    }

    //Method to save data but not implemented as of now.
    public void setSave(View v) {
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        finish();
    }

    //Cancel method to cancel the about to saved data.
    public void setCancel(View v) {
        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();

        finish();
    }

    public static class MyAlertDialogFragment extends DialogFragment {
        public static MyAlertDialogFragment newInstance(String str) {
            MyAlertDialogFragment fragment = new MyAlertDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", str);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) throws NullPointerException {
            String str = getArguments().getString("title");
            EditText edittext = new EditText(getActivity());
            if (str.equals("Comment")) {
                edittext.setHint("Enter your comments.");
                edittext.setHeight(400);
            } else
                edittext.setInputType(2);
            return new AlertDialog.Builder(getActivity()).setTitle(str).setView(edittext).setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int selection) {
                            ((ManualEntryDropDown) getActivity())
                                    .onOk();
                        }
                    })
                    .setNegativeButton("CANCEL",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int selection) {
                                    ((ManualEntryDropDown) getActivity())
                                            .onCancel();
                                }
                            }).create();
        }
    }
}
