package com.gocalsd.switchbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.shape.MaterialShapeDrawable;

import java.util.ArrayList;

/**
 * Google's Switchbar layout widget modified for customization and personal use
 * Modified by Ryan Gocal
 * Refactored with AndroidX and Material Components
 */

public class SwitchBar extends LinearLayoutCompat implements CompoundButton.OnCheckedChangeListener,
        View.OnClickListener {

    private ToggleSwitch mSwitch;
    private TextView mTextView;
    private String switchOn, switchOff;
    private int mBackgroundSwitchColor;
    private MaterialShapeDrawable backgroundDrawableOn, backgroundDrawableOff;
    private int onColor, offColor;

    private ArrayList<OnSwitchChangeListener> mSwitchChangeListeners =
            new ArrayList<>();

    public interface OnSwitchChangeListener {
        /**
         * Called when the checked state of the Switch has changed.
         *  @param switchView The Switch view whose state has changed.
         * @param isChecked  The new checked state of switchView.
         */
        void onSwitchChanged(ToggleSwitch switchView, boolean isChecked);
    }

    public void setSwitchbarOnBackground(int backgroundColor){
        this.mBackgroundSwitchColor = backgroundColor;
        backgroundDrawableOn.setTint(backgroundColor);
        backgroundDrawableOn.setElevation(8);
        backgroundDrawableOn.setCornerSize(0);
    }

    public void setSwitchbarOffBackground(int backgroundColor){
        this.offColor = backgroundColor;
        this.mBackgroundSwitchColor = backgroundColor;
        backgroundDrawableOff.setTint(backgroundColor);
        backgroundDrawableOff.setElevation(8);
        backgroundDrawableOff.setCornerSize(0);
    }

    public SwitchBar(Context context) {
        this(context, null);
    }

    public SwitchBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.switchStyle);
    }

    public SwitchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SwitchBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.switchbar_layout, this);
        canAnimate();
        mTextView = (TextView) getChildAt(0);
        mSwitch = (ToggleSwitch) getChildAt(1);

        backgroundDrawableOn = new MaterialShapeDrawable();
        backgroundDrawableOff = new MaterialShapeDrawable();

        setBackground(backgroundDrawableOn);
        mSwitch.setBackgroundTintList(ColorStateList.valueOf(mBackgroundSwitchColor));

        mSwitch.setThumbTintList(ColorUtil.colorToStateList(ColorUtil.getDarkerShadeColor(onColor),
                ColorUtil.getLighterShadeColor(onColor)));
        mSwitch.setTrackTintList(ColorUtil.colorToStateList(ColorUtil.getDarkerShadeColor(offColor),
                ColorUtil.getLighterShadeColor(offColor)));

        // Prevent onSaveInstanceState() to be called as we are managing the state of the Switch
        // on our own
        mSwitch.setSaveEnabled(false);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setTextViewLabel(isChecked);
            }
        });

        addOnSwitchChangeListener(new OnSwitchChangeListener() {
            @Override
            public void onSwitchChanged(ToggleSwitch switchView, boolean isChecked) {
                setTextViewLabel(isChecked);
            }
        });
        setOnClickListener(this);
    }

    public void setOnMessage (String onMessage){
        this.switchOn = String.valueOf(onMessage);
    }

    public void setOffMessage (String offMessage){
        this.switchOff = String.valueOf(offMessage);
    }

    //Update the text and background by state
    public void setTextViewLabel(boolean isChecked) {
        if(ColorUtil.isDark(offColor)){
            mTextView.setTextColor(Color.WHITE);
        }else{
            mTextView.setTextColor(Color.BLACK);
        }
        mTextView.setText(isChecked ? switchOn : switchOff);
        setBackground(isChecked ? backgroundDrawableOn : backgroundDrawableOff);

        mSwitch.setThumbTintList(isChecked ? ColorUtil.colorToStateList(ColorUtil.getDarkerShadeColor(onColor),
                ColorUtil.getLighterShadeColor(onColor)) : ColorUtil.colorToStateList(ColorUtil.getDarkerShadeColor(offColor),
                ColorUtil.getDarkerShadeColor(offColor)));

        mSwitch.setTrackTintList(isChecked ? ColorUtil.colorToStateList(ColorUtil.getLighterShadeColor(onColor),
                ColorUtil.getLighterShadeColor(onColor)) : ColorUtil.colorToStateList(ColorUtil.getDarkerShadeColor(offColor),
                ColorUtil.getDarkerShadeColor(offColor)));

    }

    public void setChecked(boolean checked) {
        setTextViewLabel(checked);
        mSwitch.setChecked(checked);
    }

    public void setCheckedInternal(boolean checked) {
        setTextViewLabel(checked);
        mSwitch.setCheckedInternal(checked);
    }

    public boolean isChecked() {
        return mSwitch.isChecked();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mTextView.setEnabled(enabled);
        mSwitch.setEnabled(false);
    }

    public final ToggleSwitch getSwitch() {
        return mSwitch;
    }

    public void show() {
        if (!isShowing()) {
            setVisibility(View.VISIBLE);
            mSwitch.setOnCheckedChangeListener(this);
        }
    }

    public void hide() {
        if (isShowing()) {
            setVisibility(View.GONE);
            mSwitch.setOnCheckedChangeListener(null);
        }
    }

    public boolean isShowing() {
        return (getVisibility() == View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        final boolean isChecked = !mSwitch.isChecked();
        setChecked(isChecked);
    }

    public void propagateChecked(boolean isChecked) {
        final int count = mSwitchChangeListeners.size();
        for (int n = 0; n < count; n++) {
            mSwitchChangeListeners.get(n).onSwitchChanged(mSwitch, isChecked);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        propagateChecked(isChecked);
    }

    public void addOnSwitchChangeListener(OnSwitchChangeListener listener) {
        if (mSwitchChangeListeners.contains(listener)) {
            throw new IllegalStateException("Cannot add twice the same OnSwitchChangeListener");
        }
        mSwitchChangeListeners.add(listener);
    }

    public void removeOnSwitchChangeListener(OnSwitchChangeListener listener) {
        if (!mSwitchChangeListeners.contains(listener)) {
            throw new IllegalStateException("Cannot remove OnSwitchChangeListener");
        }
        mSwitchChangeListeners.remove(listener);
    }

    static class SavedState extends BaseSavedState {
        boolean checked;
        boolean visible;
        SavedState(Parcelable superState) {
            super(superState);
        }

        /**
         * Constructor called from {@link #CREATOR}
         */
        private SavedState(Parcel in) {
            super(in);
            checked = (Boolean)in.readValue(null);
            visible = (Boolean)in.readValue(null);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeValue(checked);
            out.writeValue(visible);
        }

        @Override
        public String toString() {
            return "SwitchBar.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " checked=" + checked
                    + " visible=" + visible + "}";
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.checked = mSwitch.isChecked();
        ss.visible = isShowing();
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        mSwitch.setCheckedInternal(ss.checked);
        setTextViewLabel(ss.checked);
        setVisibility(ss.visible ? View.VISIBLE : View.GONE);
        mSwitch.setOnCheckedChangeListener(ss.visible ? this : null);
        requestLayout();
    }
}