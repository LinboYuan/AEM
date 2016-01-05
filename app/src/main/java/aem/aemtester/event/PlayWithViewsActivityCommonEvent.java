package aem.aemtester.event;

import android.app.Activity;
import android.content.res.Resources;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;

import aem.aemtester.R;
import aem.event.EventAnnotation;
import aem.event.EventType;
import aem.event.FullEventManager;

public class PlayWithViewsActivityCommonEvent extends FullEventManager {
    public PlayWithViewsActivityCommonEvent(Activity activity) {
        super(activity);
    }

    @EventAnnotation(value = R.id.radioGroup, eventType = EventType.RadioGroup_OnCheckedChanged)
    private void onRadioCheckedChang(RadioGroupEventArgs args) {
        String msg = MessageFormat.format("{0} is checked.", ((RadioButton) args.group.findViewById(args.checkedId)).getText());
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    @EventAnnotation(value = R.id.checkbox, eventType = EventType.CompoundButton_OnCheckedChanged)
    private void onCheckBoxCheckedChanged(CompoundButtonEventArgs args) {
        Toast.makeText(activity, args.compoundButton.getText() + (args.isChecked ? " is checked." : " is unchecked."), Toast.LENGTH_SHORT).show();
    }

    @EventAnnotation(value = R.id.spinner, eventType = EventType.AdapterView_OnItemSelected)
    private void onSpinnerItemClick(AdapterViewEventArgs args) {
        String item = (String) args.parent.getAdapter().getItem(args.position);
        Toast.makeText(activity, item + " is selected.", Toast.LENGTH_SHORT).show();
    }

    @EventAnnotation(value = R.id.timePicker, eventType = EventType.TimePicker_OnTimeChanged)
    private void onTimePickTimeChanged(TimePickerEventArgs args) {
        Toast.makeText(activity, MessageFormat.format("Time changed to {0}:{1} ", args.hourOfDay, args.minute), Toast.LENGTH_SHORT).show();
    }

    @EventAnnotation(value = R.id.seekBar, eventType = EventType.SeekBar_OnProgressChanged)
    private void onSeekBarProgressChanged(SeekBarEventArgs args) {
        TextView textView = (TextView) activity.findViewById(R.id.seekBarTip);
        textView.setText("Current: " + args.progress);
    }

    @EventAnnotation(value = R.id.seekBar, eventType = EventType.SeekBar_OnStartTrackingTouch)
    private void onSeekBarStartChange(SeekBarEventArgs args) {
        args.seekBar.setBackgroundColor(Resources.getSystem().getColor(android.R.color.holo_green_light));
    }

    @EventAnnotation(value = R.id.seekBar, eventType = EventType.SeekBar_OnStopTrackingTouch)
    private void onSeekBarStopChange(SeekBarEventArgs args) {
        Toast.makeText(activity, "Adjustment stopped.", Toast.LENGTH_SHORT).show();
    }

    @EventAnnotation(value = R.id.ratingBar, eventType = EventType.RatingBar_OnRatingChanged)
    private void onratingChanged(RatingBarEventArgs args) {
        TextView textView = (TextView) activity.findViewById(R.id.ratingBarTip);
        textView.setText("Current: " + args.rating);
    }
}