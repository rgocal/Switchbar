# Switchbar
Google's Switchbar layout widget recreated for personal use! Want a preview? Open up your android settings, navigate to wireless settings, open up your wifi settings, and woolah! That's a switchbar!

# Migration
Switchbar has been refactored  to AndroidX and MaterialComponents. If you don't use these in your project implementations, I suggest you do so before using this library.

# Let's get setup
Add these correctly to your gradle setup files.

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

    	dependencies {
	        implementation 'com.github.rgocal:Switchbar:2.10'
	}
	
	
	
![Preview Image](./preview/switchbar_on.jpg?raw=true)
![Preview Image](./preview/switchbar_off.jpg?raw=true)

In your layout you plan to add a Switchbar, its recommended to use it as your actionbar in a fragment or below your toolbar. 56dp is the standard default size of a toolbar.

    <com.gocalsd.switchbar.SwitchBar
            android:id="@+id/switchBar"
            android:layout_width="match_parent"
            android:layout_height="56dp"/>
            
In your Activity, Add the switch, and set the following attributes

    	//Set the on and off Background Colors using Context
        //If you want to theme the Switch, override the style
        switchBar.setSwitchbarOnBackground(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary));
        switchBar.setSwitchbarOffBackground(ContextCompat.getColor(getBaseContext(), R.color.colorAccent));

        //Set the defaults
        switchBar.setOnMessage("on String");
        switchBar.setOffMessage("off String");

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
