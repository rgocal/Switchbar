package com.gocalsd.switchbarexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.gocalsd.switchbar.SwitchBar;

public class MainActivity extends AppCompatActivity {

    SwitchBar switchBar;
    String posMessage, negMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        posMessage = "Switchbar is Enabled!";
        negMessage = "Switchbar Disabled...";

        switchBar = findViewById(R.id.switchBar);
        //Show the Switchbar! It's hidden by default!
        switchBar.show();

        //Set the on and off Messages
        switchBar.setOnMessage(posMessage);
        switchBar.setOffMessage(negMessage);

        //Set the on and off Background Colors
        //If you want to theme the Switch, override the style
        switchBar.setSwitchbarOnBackground(R.color.colorPrimary);
        switchBar.setSwitchbarOffBackground(R.color.colorPrimaryDark);

        //Set the defaults
        switchBar.setChecked(true);
        switchBar.setTextViewLabel(true);

        //Set the addOnSwitchChangeListener
        switchBar.addOnSwitchChangeListener(new SwitchBar.OnSwitchChangeListener() {
            @Override
            public void onSwitchChanged(Switch switchView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this, "It's on!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "It's off!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
