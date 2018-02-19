package cs.dartmouth.edu.cs165.mahesh.myruns2;

import android.os.Bundle;
import android.preference.PreferenceFragment;


public class SettingsTab extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}

