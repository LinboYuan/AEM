##[Abstract](https://github.com/LinboYuan/AndroidEventManager/wiki)
Android event manager is an utility class to simplify android listeners. It has encapsulated most of the view event listeners. When bind an event to a view, you just need declare a method that has the corresponding event args signature, and annotate it with the **_EventAnnotation_**. It contains a **_CommonEventManager_** which just has implemented listeners that declared in **_View_** class directly, and a **_FullEventManager_** that has impemented more listeners.

We will get rid of the fussy listener's implementation by this utility. It is easy to use and uncouples activity with the views' event. Meanwhile, it has a good performance.

It is open source and free to use.

##[How to use?](https://github.com/LinboYuan/AndroidEventManager/wiki/How-to-use%3F)
**Step 1**:
Define a class that extends the **_CommonEventManager_** or **_FullEventManager_** class, then you can define the event as you need. What we just need is annotating the method with **_EventAnnotation_**, and ensure the method has the proper signature. Note: _the return value match is not required but recommended_. E.g.
    
    public class MainActivityEvent extends FullEventManager {
        public MainActivityEvent(Activity activity) {
            super(activity);
        }

        @EventAnnotation(R.id.redirectBtn) // or (value = R.id.redirectBtn, eventType = EventType.OnClick)
        private void redirect(EventArgs args) {
            // Do something.
        }

        @EventAnnotation(value = R.id.categoryList, eventType = EventType.AdapterView_OnItemClick)
        private void onCategoryItemClick(AdapterViewEventArgs args) {
            // Do something.
        }
    }

**Step 2**:
Just initialize the event class in the corresponding activity's **_onCreate_** method. This will finished the register automatically.E.g.

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
    
##[Document](https://github.com/LinboYuan/AndroidEventManager/wiki/Document)
1. **CommonEventManager Members**

   **Field**:

       * _protected final Activity activity_

       Des: Represent the activity that current event manager attached to.

   **Constructor**:

       * _protected EventManager(Activity activity)_

          Des: The only provided constructor with one parameter of Activity type.
    
   **Inner Types**:

       * _EventArgs_

          Des: Base class of all event args class. Argument type of: **_OnClick_**, **_OnLongClick_**.

       * _ContextMenuEventEventArgs_
       
          Des: Base class of all event args class. Argument type of: **_OnCreateContextMenu_**.

       * _DragEventEventArgs_
       
          Des: Base class of all event args class. Argument type of: **_OnDrag_**.

       * _FocusChangeEventEventArgs_
       
          Des: Base class of all event args class. Argument type of: **_OnFocusChange_**.

       * _GenericMotionEventEventArgs_
       
          Des: Base class of all event args class. Argument type of: **_OnGenericMotion_**, **_OnHover_**, **_OnTouch_**.

       * _KeyEventEventArgs_
        
          Des: Base class of all event args class. Argument type of: **_OnKey_**.

       * _ContextMenuEventEventArgs_
        
          Des: Base class of all event args class. Argument type of: **_OnCreateContextMenu_**.

2. **FullEventManager Members**

   **Description**:
   
     Inherits from **_CommonEventManager_**.
    
   **Inner Types**:

       *  _AdapterViewEventArgs_
       
          Des: Base class of all event args class. Argument type of: **_AdapterView_OnItemClick_**, **_AdapterView_OnItemLongClick_**, **_AdapterView_OnItemSelected_**, **_AdapterView_OnNothingSelected_**.

       *  _RadioGroupEventArgs_
       
          Des: Base class of all event args class. Argument type of: **_RadioGroup_OnCheckedChanged_**.

       *  _CompoundButtonEventArgs_
       
          Des: Base class of all event args class. Argument type of: **_CompoundButton_OnCheckedChanged_**.

       *  _TimePickerEventArgs_
       
          Des: Base class of all event args class. Argument type of: **_TimePicker_OnTimeChanged_**.

       * _SeekBarEventArgs_
       
          Des: Base class of all event args class. Argument type of: **_SeekBar_OnProgressChanged_**, **_SeekBar_OnStartTrackingTouch_**, **_SeekBar_OnStopTrackingTouch_**.

       * _RatingBarEventArgs_
       
          Des: Base class of all event args class. Argument type of: **_RatingBar_OnRatingChanged_**.
    
3. **EventAnnotation Members**

       * _@IdRes int value_

          Des: Default annotation member, view id of the view that register the handler to. Required.
       
       * _EventType eventType_

          Des: Event type. Optional. Default value is EventType.OnClick.

4. **EventType Members**

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
       
       * _SeekBar_OnProgressChanged_
       
       * _SeekBar_OnStartTrackingTouch_
       
       * _SeekBar_OnStopTrackingTouch_
       
       * _RatingBar_OnRatingChanged_
