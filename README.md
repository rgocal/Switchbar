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
	        implementation 'com.github.rgocal:Switchbar:2.00'
	}

In your layout you plan to add a Switchbar, its recommended to use it as your actionbar in a fragment or below your toolbar. 56dp is the standard default size of a toolbar.

    <com.gocalsd.switchbar.SwitchBar
            android:id="@+id/switchBar"
            android:layout_width="match_parent"
            android:layout_height="56dp"/>
            
In your Activity, Add the following! posMessage and negMessage are just strings. Theres really nothing to it! Treat it like a switch and your good to go!

    switchBar = findViewById(R.id.switchBar);

        //Set the on and off Messages
        switchBar.setOnMessage("Switchbar on!");
        switchBar.setOffMessage("Switchbar off!");
	
        //Set the on and off Background Colors
        //Override the switchbar style to change the switch tint
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
                    //do something when checked on
                }else{
                    //do something when checked off
                }
		
            }
        });
