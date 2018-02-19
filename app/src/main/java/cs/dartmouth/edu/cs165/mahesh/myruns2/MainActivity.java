package cs.dartmouth.edu.cs165.mahesh.myruns2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public ArrayList<Fragment> fragment;
    public Tabs tabs;
    public SlidingTabLayout slidingtablayout;
    public ViewPager viewpager;


	@Override
    //Initial fragment screen to save and sync
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        slidingtablayout = (SlidingTabLayout) findViewById(R.id.startmaintab);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        fragment = new ArrayList<>();
        fragment.add(new MainFragment());
        fragment.add(new HistoryTab());
        fragment.add(new SettingsTab());
        this.tabs =new Tabs(getFragmentManager(), fragment);
        viewpager.setAdapter(tabs);
        slidingtablayout.setDistributeEvenly(true);
        slidingtablayout.setViewPager(viewpager);
	}

    //Method to save the data on clicked with save button.
    public void setStart(View v) {
        Spinner spinner = (Spinner)findViewById(R.id.spinnerInputType);
        String selection = spinner.getSelectedItem().toString();
        Intent intent;
        switch(selection) {
            case "Manual Entry":
                intent = new Intent(MainActivity.this,ManualEntryDropDown.class);
                startActivity(intent);
                break;
            case "GPS":
                intent = new Intent(MainActivity.this, MapDropDown.class);
                startActivity(intent);
                break;
            case "Automatic":
                intent = new Intent(MainActivity.this, MapDropDown.class);
                startActivity(intent);
                break;
        }

    }

    //Method to cancel the input when clicked on cancel button.
    public void setSync(View v) {
        Toast.makeText(getApplicationContext(), "Sync", Toast.LENGTH_SHORT).show();
    }
}