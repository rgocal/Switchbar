package com.gocalsd.switchbarexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.gocalsd.switchbar.SwitchBar;
import com.gocalsd.switchbar.ToggleSwitch;

public class MainActivity extends AppCompatActivity {

    String posMessage, negMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        final AppCompatImageView imageView = findViewById(R.id.cast_background);
        SwitchBar switchBar = findViewById(R.id.switchBar);

        setSupportActionBar(toolbar);

        posMessage = getString(R.string.switch_on_string);
        negMessage = getString(R.string.switch_off_string);

        imageView.setBackgroundResource(R.drawable.cast_connected);

        //Set the on and off Background Colors using Context
        //If you want to theme the Switch, override the style
        switchBar.setSwitchbarOnBackground(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary));
        switchBar.setSwitchbarOffBackground(ContextCompat.getColor(getBaseContext(), R.color.colorAccent));

        //Set the defaults
        switchBar.setOnMessage(posMessage);
        switchBar.setOffMessage(negMessage);

        switchBar.setChecked(true);
        switchBar.setTextViewLabel(true);

        //Set the add the Listener
        switchBar.getSwitch().setOnBeforeCheckedChangeListener(new ToggleSwitch.OnBeforeCheckedChangeListener() {
            @Override
            public boolean onBeforeCheckedChanged(ToggleSwitch toggleSwitch, boolean checked) {
                if(checked) {
                    imageView.setBackgroundResource(R.drawable.cast_connected);
                }else{
                    imageView.setBackgroundResource(R.drawable.cast_off);
                }
                return false;
            }
        });

    }

}
