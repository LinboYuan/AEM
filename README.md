##Abstract
Android event manager is an utility class to simplify android listeners. It has encapsulated most of the view event listeners. When bind an event to a view, you just need declare a method that has the same signature with the event, and annotate it with the EventAnnotation.

We will get rid of the fussy listener's implementation by this utility. It is easy to use and uncouples activity with the views' event. Meanwhile, it has a good performance.

It is open source and free to use.

##How to use?
**Step 1**:
Define a class that extends the **EventManager** class(this is an abstract class that encapsulated/implemented all supported listeners), then you can define the event that you want as you wish. What we just need is annotating the method with **EventAnnotation**, and ensure the method has the same signature with the event that you want to bind to. Note: the return value match is not required but recommended. E.g.
    
    public class MainActivityEvent extends EventManager {
        public MainActivityEvent(Activity activity) {
            super(activity);
        }

        @EventAnnotation(R.id.redirectBtn) // or (value = R.id.redirectBtn, eventType = EventType.OnClick)
        private void redirect(View view) {
            // Do something.
        }

        @EventAnnotation(value = R.id.categoryList, eventType = EventType.AdapterView_OnItemClick)
        private void onCategoryItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Do something.
        }
    }

**Step 2**:
Just initialize the event class in the corresponding activity's **onCreate** method. This will finished the register automatically.E.g.

    public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Initialize/Register the events.
            new MainActivityEvent(this);
        }

        ...
    }
    
##Document
1. **EventManager Members**

   **Field**:

    * _protected final Activity activity_

       Des: Represent the activity that current event manager attached to.

   **Constructor**:

       * _protected EventManager(Activity activity)_

          Des: The only provided constructor with one parameter of Activity type.

2. **EventAnnotation Members**

       * _@IdRes int value_

          Des: Default annotation member, view id of the view that register the handler to. Required.
       
       * _EventType eventType_

          Des: Event type. Optional. Default value is EventType.OnClick.

3. **EventType Members**

       * _AdapterView_OnItemClick_

       * _AdapterView_OnItemLongClick_

       * _AdapterView_OnItemSelected_

       * _AdapterView_OnNothingSelected_

       * _CompoundButton_OnCheckedChanged_

       * _OnClick_

       * _OnCreateContextMenu_

       * _OnDrag_

       * _OnFocusChange_

       * _OnGenericMotion_

       * _OnHover_

       * _OnKey_

       * _OnLongClick_

       * _OnTouch_

       * _RadioGroup_OnCheckedChanged_
       
       * _CompoundButton_OnCheckedChanged_
       
       * _TimePicker_OnTimeChanged_
