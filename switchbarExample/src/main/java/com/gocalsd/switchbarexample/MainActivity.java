package com.gocalsd.switchbarexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gocalsd.switchbar.SwitchBar;
import com.gocalsd.switchbar.ToggleSwitch;

public class MainActivity extends AppCompatActivity {

    SwitchBar switchBar;
    String posMessage, negMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        switchBar = new SwitchBar(this);

        posMessage = getString(R.string.switch_on_string);
        negMessage = getString(R.string.switch_off_string);

        switchBar = findViewById(R.id.switchBar);

        //Set the on and off Background Colors
        //If you want to theme the Switch, override the style
        switchBar.setSwitchbarOnBackground(R.color.colorPrimary);
        switchBar.setSwitchbarOffBackground(R.color.colorAccent);

        //Set the defaults
        switchBar.setOnMessage(posMessage);
        switchBar.setOffMessage(negMessage);

        switchBar.setChecked(true);
        switchBar.setTextViewLabel(true);

        //Set the addOnSwitchChangeListener
        switchBar.addOnSwitchChangeListener(new SwitchBar.OnSwitchChangeListener() {
            @Override
            public void onSwitchChanged(ToggleSwitch switchView, boolean isChecked) {

            }
        });
    }

}
