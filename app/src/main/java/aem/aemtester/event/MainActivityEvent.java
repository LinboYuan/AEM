package aem.aemtester.event;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;


import aem.activity.event.EventAnnotation;
import aem.activity.event.EventManager;
import aem.activity.event.EventType;
import aem.aemtester.R;
import aem.aemtester.activity.EditActivity;

public class MainActivityEvent extends EventManager {
    public MainActivityEvent(Activity activity) {
        super(activity);
    }

    @EventAnnotation(R.id.redirectBtn)     //or    @EventAnnotation(value = R.id.redirectBtn, eventType = EventType.OnClick)
    private void redirect(View view) {
        Intent intent = new Intent(activity, EditActivity.class);
        activity.startActivity(intent);
    }

    @EventAnnotation(value = R.id.redirectBtn, eventType = EventType.OnLongClick)
    private boolean redirectLongClick(View view) {
        Toast.makeText(activity, "Redirect button is long clicked.", Toast.LENGTH_LONG).show();
        return true;
    }
}