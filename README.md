# Switchbar
Google's Swtichbar layout widget recreated for personal use! Want a preview? Open up your android settings, navigate to wireless settings, open up your wifi settings, and woolah! That's a switchbar!

# Let's get setup
Add these correctly to your gradle setup files.

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

    	dependencies {
	        implementation 'com.github.rgocal:Switchbar:1.00'
	}

In your layout you plan to add a Switchbar, its recommended to use it as your actionbar in a fragment or below your toolbar.

    <com.gocalsd.switchbar.SwitchBar
            android:id="@+id/switchBar"
            android:layout_gravity="top"
            android:layout_marginTop="56dp"
            android:layout_width="match_parent"
            android:layout_height="56dp"/>
            
In your Activity, Add the following! posMessage and negMessage are just strings. Theres really nothing to it! Treat it like a switch and your good to go!

    switchBar = findViewById(R.id.switchBar);
        //Show the Switchbar! It's hidden by default!
        switchBar.show();

        //Set the on and off Messages
        switchBar.setOnMessage(posMessage);
        switchBar.setOffMessage(negMessage);
        //Set the on and off Background Colors
        //It's suggested to use the Primary and DarkPrimary colors of your application
        //as the switch uses the Accent Color
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
