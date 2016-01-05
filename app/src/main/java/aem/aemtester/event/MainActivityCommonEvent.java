package aem.aemtester.event;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import aem.aemtester.R;
import aem.aemtester.activity.EditActivity;
import aem.aemtester.activity.PlayWithViewsActivity;
import aem.event.CommonEventManager;
import aem.event.EventAnnotation;
import aem.event.EventType;
import aem.event.FullEventManager;

public class MainActivityCommonEvent extends CommonEventManager {
    public MainActivityCommonEvent(Activity activity) {
        super(activity);
    }

    @EventAnnotation(R.id.redirectBtn)
    //or    @EventAnnotation(value = R.id.redirectBtn, eventType = EventType.OnClick)
    private void redirect(EventArgs args) {
        Intent intent = new Intent(activity, EditActivity.class);
        activity.startActivity(intent);
    }

    @EventAnnotation(value = R.id.redirectBtn, eventType = EventType.OnLongClick)
    private boolean redirectLongClick(EventArgs args) {
        Toast.makeText(activity, "Redirect button is long clicked.", Toast.LENGTH_LONG).show();
        return true;
    }

//    @EventAnnotation(value = R.id.mainList, eventType = EventType.AdapterView_OnItemClick)
//    private void onMainListItemClick(AdapterViewEventArgs args) {
//        Toast.makeText(activity, "Main list item is clicked.", Toast.LENGTH_LONG).show();
//    }

    @EventAnnotation(R.id.playViews)
    private void onPlayViewsClick(EventArgs args) {
        Intent intent = new Intent(activity, PlayWithViewsActivity.class);
        activity.startActivity(intent);
    }
}