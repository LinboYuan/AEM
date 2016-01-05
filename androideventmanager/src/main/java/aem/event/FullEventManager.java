package aem.event;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TimePicker;

/**
 * An abstract class that implements all the supported event listeners.
 * {@inheritDoc}.
 */
public abstract class FullEventManager extends CommonEventManager implements
        AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener,
        AdapterView.OnItemSelectedListener,
        RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener,
        TimePicker.OnTimeChangedListener,
        SeekBar.OnSeekBarChangeListener,
        RatingBar.OnRatingBarChangeListener {
    protected FullEventManager(Activity activity) {
        super(activity);
    }

    /**
     * @param view
     * @param eventType
     * @return a boolean value to indicate whether specified event type is particular.
     */
    private boolean setParticularListener(View view, EventType eventType) {
        if (eventType.isAdapterViewEvent()) {
            if (view instanceof AdapterView) {
                AdapterView adapterView = (AdapterView) view;
                switch (eventType) {
                    case AdapterView_OnItemClick:
                        adapterView.setOnItemClickListener(this);
                        break;
                    case AdapterView_OnItemLongClick:
                        adapterView.setOnItemLongClickListener(this);
                        break;
                    case AdapterView_OnItemSelected:
                    case AdapterView_OnNothingSelected:
                        adapterView.setOnItemSelectedListener(this);
                        break;
                }
            }

            return true;
        }

        if (eventType.isSeekbarEvent()) {
            if (view instanceof SeekBar) {
                switch (eventType) {
                    case SeekBar_OnProgressChanged:
                    case SeekBar_OnStartTrackingTouch:
                    case SeekBar_OnStopTrackingTouch:
                        ((SeekBar) view).setOnSeekBarChangeListener(this);
                        break;
                }
            }

            return true;
        }

        if (eventType.isRadioGroupEvent() && view instanceof RadioGroup) {
            RadioGroup radioGroup = (RadioGroup) view;
            radioGroup.setOnCheckedChangeListener(this);
            return true;
        }

        if (eventType.isCompoundButtonEvent() && view instanceof CompoundButton) {
            CompoundButton compoundButton = (CompoundButton) view;
            compoundButton.setOnCheckedChangeListener(this);
            return true;
        }

        if (eventType.isTimePickerEvent() && view instanceof TimePicker) {
            TimePicker timePicker = (TimePicker) view;
            timePicker.setOnTimeChangedListener(this);
            return true;
        }

        if (eventType.isRatingbarEvent() && view instanceof RatingBar) {
            RatingBar ratingBar = (RatingBar) view;
            ratingBar.setOnRatingBarChangeListener(this);
            return true;
        }

        return false;
    }

    @Override
    void setListener(View view, EventType eventType) {
        if (!setParticularListener(view, eventType)) {
            super.setListener(view, eventType);
        }
    }

    protected class AdapterViewEventArgs extends EventArgs {
        public AdapterView<?> parent;
        /**
         * The view within the AdapterView that was clicked (this
         * will be a view provided by the adapter)
         */
        public View itemView;
        public int position;
        public long id;

        public AdapterViewEventArgs(AdapterView<?> parent, View view, int position, long id) {
            super(parent);
            this.parent = parent;
            this.itemView = view;
            this.position = position;
            this.id = id;
        }
    }

    protected class RadioGroupEventArgs extends EventArgs {
        public RadioGroup group;
        public int checkedId;

        public RadioGroupEventArgs(RadioGroup group, int checkedId) {
            super(group);
            this.group = group;
            this.checkedId = checkedId;
        }
    }

    protected class CompoundButtonEventArgs extends EventArgs {
        public CompoundButton compoundButton;
        public boolean isChecked;

        public CompoundButtonEventArgs(CompoundButton compoundButton, boolean isChecked) {
            super((compoundButton));
            this.compoundButton = compoundButton;
            this.isChecked = isChecked;
        }
    }

    protected class TimePickerEventArgs extends EventArgs {
        public TimePicker timePicker;
        public int hourOfDay;
        public int minute;

        public TimePickerEventArgs(TimePicker timePicker, int hourOfDay, int minute) {
            super(timePicker);
            this.timePicker = timePicker;
            this.hourOfDay = hourOfDay;
            this.minute = minute;
        }
    }

    protected class SeekBarEventArgs extends EventArgs {
        public SeekBar seekBar;
        public int progress;
        public boolean fromUser;

        public SeekBarEventArgs(SeekBar seekBar, int progress, boolean fromUser) {
            super(seekBar);
            this.seekBar = seekBar;
            this.progress = progress;
            this.fromUser = fromUser;
        }
    }

    protected class RatingBarEventArgs extends EventArgs {
        public RatingBar ratingBar;
        public float rating;
        public boolean fromUser;

        public RatingBarEventArgs(RatingBar ratingBar, float rating, boolean fromUser) {
            super(ratingBar);
            this.ratingBar = ratingBar;
            this.rating = rating;
            this.fromUser = fromUser;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        invokeAction(EventType.AdapterView_OnItemClick, new AdapterViewEventArgs(parent, view, position, id));
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return invokeActionReturnStatus(EventType.AdapterView_OnItemLongClick, new AdapterViewEventArgs(parent, view, position, id));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        invokeAction(EventType.AdapterView_OnItemSelected, new AdapterViewEventArgs(parent, view, position, id));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        invokeAction(EventType.AdapterView_OnNothingSelected, new AdapterViewEventArgs(parent, null, -1, -1));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        invokeAction(EventType.RadioGroup_OnCheckedChanged, new RadioGroupEventArgs(group, checkedId));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        invokeAction(EventType.CompoundButton_OnCheckedChanged, new CompoundButtonEventArgs(buttonView, isChecked));
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        invokeAction(EventType.TimePicker_OnTimeChanged, new TimePickerEventArgs(view, hourOfDay, minute));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        invokeAction(EventType.SeekBar_OnProgressChanged, new SeekBarEventArgs(seekBar, progress, fromUser));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        invokeAction(EventType.SeekBar_OnStartTrackingTouch, new SeekBarEventArgs(seekBar, 0, false));
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        invokeAction(EventType.SeekBar_OnStopTrackingTouch, new SeekBarEventArgs(seekBar, 0, false));
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        invokeAction(EventType.RatingBar_OnRatingChanged, new RatingBarEventArgs(ratingBar, rating, fromUser));
    }
}
