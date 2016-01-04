package aem.event;

/**
 * Event type.
 */
public enum EventType {
    /**
     * signature: (View v, int keyCode, KeyEvent event)
     * return: boolean.
     */
    OnKey(0),
    /**
     * signature: (View v, MotionEvent event)
     * return: boolean.
     */
    OnTouch(1),
    /**
     * signature: (View v, MotionEvent event)
     * return: boolean.
     */
    OnHover(2),
    /**
     * signature: (View v, MotionEvent event)
     * return: boolean.
     */
    OnGenericMotion(4),
    /**
     * signature: (View v)
     * return: boolean.
     */
    OnLongClick(8),
    /**
     * signature: (View v, DragEvent event)
     * return: boolean.
     */
    OnDrag(16),
    /**
     * signature: (View v, boolean hasFocus)
     * return: void.
     */
    OnFocusChange(32),
    /**
     * signature: (View v)
     * return: void.
     */
    OnClick(64),
    /**
     * signature: (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
     * return: void.
     */
    OnCreateContextMenu(128),

    // Adapter view event type weight: 256+512+1024+2048=3840.
    /**
     * signature: (AdapterView<?> parent, View view, int position, long id)
     * return: void.
     */
    AdapterView_OnItemClick(256),
    /**
     * signature: (AdapterView<?> parent, View view, int position, long id)
     * return: boolean.
     */
    AdapterView_OnItemLongClick(512),
    /**
     * signature: (AdapterView<?> parent, View view, int position, long id)
     * return: void.
     */
    AdapterView_OnItemSelected(1024),
    /**
     * signature: (AdapterView<?> parent)
     * return: boolean.
     */
    AdapterView_OnNothingSelected(2048),

    /**
     * signature: (RadioGroup group, int checkedId)
     * return: void.
     */
    RadioGroup_OnCheckedChanged(4096),
    /**
     * signature: (CompoundButton buttonView, boolean isChecked)
     * return: void.
     */
    CompoundButton_OnCheckedChanged(8192),
    /**
     * signature: onTimeChanged(TimePicker view, int hourOfDay, int minute)
     * return: void.
     */
    TimePicker_OnTimeChanged(16384);

    private final int key;

    EventType(int key) {
        this.key = key;
    }

    boolean isAdapterViewEvent() {
        return (key & 3840) > 0; // Adapter view particular event type weight: 3840.
    }

    boolean isRadioGroupEvent() {
        return (key & 4096) > 0; // Radio group particular event type weight: 4096.
    }

    boolean isCompoundButtonEvent() {
        return (key & 8192) > 0; // Compound button particular event type weight: 8192.
    }

    boolean isTimePickerEvent() {
        return (key & 16384) > 0; // Compound button particular event type weight: 16384.
    }
}