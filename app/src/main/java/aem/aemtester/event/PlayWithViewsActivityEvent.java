package aem.aemtester.event;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.MessageFormat;

import aem.aemtester.R;
import aem.event.EventAnnotation;
import aem.event.EventManager;
import aem.event.EventType;

public class PlayWithViewsActivityEvent extends EventManager {
    public PlayWithViewsActivityEvent(Activity activity) {
        super(activity);
    }

    @EventAnnotation(value = R.id.radioGroup, eventType = EventType.RadioGroup_OnCheckedChanged)
    private void onRadioCheckedChang(RadioGroup group, int checkedId) {
        String msg = MessageFormat.format("{0} is checked.", ((RadioButton) group.findViewById(checkedId)).getText());
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    @EventAnnotation(value = R.id.checkbox, eventType = EventType.CompoundButton_OnCheckedChanged)
    private void onCheckBoxCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(activity, buttonView.getText() + (isChecked ? " is checked." : " is unchecked."), Toast.LENGTH_SHORT).show();
    }

    @EventAnnotation(value = R.id.spinner, eventType = EventType.AdapterView_OnItemSelected)
    private void onSpinnerItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = (String) parent.getAdapter().getItem(position);
        Toast.makeText(activity, item + " is selected.", Toast.LENGTH_SHORT).show();
    }
}