package aem.aemtester.event;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import aem.aemtester.R;
import aem.aemtester.activity.EditActivity;
import aem.event.EventAnnotation;
import aem.event.EventManager;
import aem.event.EventType;

public class MainActivityEvent extends EventManager {
    public MainActivityEvent(Activity activity) {
        super(activity);
    }

    @EventAnnotation(R.id.redirectBtn)
    //or    @EventAnnotation(value = R.id.redirectBtn, eventType = EventType.OnClick)
    private void redirect(View view) {
        Intent intent = new Intent(activity, EditActivity.class);
        activity.startActivity(intent);
    }

    @EventAnnotation(value = R.id.redirectBtn, eventType = EventType.OnLongClick)
    private boolean redirectLongClick(View view) {
        Toast.makeText(activity, "Redirect button is long clicked.", Toast.LENGTH_LONG).show();
        return true;
    }

    @EventAnnotation(value = R.id.mainList, eventType = EventType.OnItemClick)
    private void onMainListItemClick(AdapterView<?> parent, View view, int position, long id){
        Toast.makeText(activity, "Main list item is clicked.", Toast.LENGTH_LONG).show();
    }
}