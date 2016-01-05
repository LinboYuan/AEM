package aem.event;

/**
 * Event type.
 */
public enum EventType {
    /**
     * signature: (KeyEventEventArgs args)
     * return: boolean.
     */
    OnKey(0),
    /**
     * signature: (GenericMotionEventEventArgs args)
     * return: boolean.
     */
    OnTouch(0),
    /**
     * signature: (GenericMotionEventEventArgs args)
     * return: boolean.
     */
    OnHover(0),
    /**
     * signature: (GenericMotionEventEventArgs args)
     * return: boolean.
     */
    OnGenericMotion(0),
    /**
     * signature: (EventArgs args)
     * return: boolean.
     */
    OnLongClick(0),
    /**
     * signature: (DragEventEventArgs args)
     * return: boolean.
     */
    OnDrag(0),
    /**
     * signature: (FocusChangeEventEventArgs args)
     * return: void.
     */
    OnFocusChange(0),
    /**
     * signature: (EventEventArgs args)
     * return: void.
     */
    OnClick(0),
    /**
     * signature: (ContextMenuEventEventArgs args)
     * return: void.
     */
    OnCreateContextMenu(0),

    // Adapter view event type weight: 1+2+4+8=15.
    /**
     * signature: (AdapterViewEventArgs args)
     * return: void.
     */
    AdapterView_OnItemClick(1),
    /**
     * signature: (AdapterViewEventArgs args)
     * return: boolean.
     */
    AdapterView_OnItemLongClick(2),
    /**
     * signature: (AdapterViewEventArgs args)
     * return: void.
     */
    AdapterView_OnItemSelected(4),
    /**
     * signature: (AdapterViewEventArgs args)
     * return: boolean.
     */
    AdapterView_OnNothingSelected(8),

    /**
     * signature: (RadioGroupEventArgs args)
     * return: void.
     */
    RadioGroup_OnCheckedChanged(16),
    /**
     * signature: (CompoundButtonEventArgs args)
     * return: void.
     */
    CompoundButton_OnCheckedChanged(32),
    /**
     * signature: (TimePickerEventArgs args)
     * return: void.
     */
    TimePicker_OnTimeChanged(64),

    //Seek bar event type weight: 128+256+512=896.
    /**
     * signature: (SeekBarEventArgs args)
     * return: void.
     */
    SeekBar_OnProgressChanged(128),
    /**
     * signature: (SeekBarEventArgs args)
     * return: void.
     */
    SeekBar_OnStartTrackingTouch(256),
    /**
     * signature: (SeekBarEventArgs args)
     * return: void.
     */
    SeekBar_OnStopTrackingTouch(512),

    /**
     * signature: (RatingBarEventArgs args)
     * return: void.
     */
    RatingBar_OnRatingChanged(1024);

    /**
     * Event key. 0 if the event inherits from View.
     */
    private final int key;

    EventType(int key) {
        this.key = key;
    }

    boolean isAdapterViewEvent() {
        return (key & 15) > 0; // Adapter view particular event type weight: 15.
    }

    boolean isRadioGroupEvent() {
        return (key & 16) > 0; // Radio group particular event type weight: 16.
    }

    boolean isCompoundButtonEvent() {
        return (key & 32) > 0; // Compound button particular event type weight: 32.
    }

    boolean isTimePickerEvent() {
        return (key & 64) > 0; // Time picker particular event type weight: 64.
    }

    boolean isSeekbarEvent() {
        return (key & 896) > 0; // Seek bar particular event type weight: 896.
    }

    boolean isRatingbarEvent() {
        return (key & 1024) > 0; // Rating bar particular event type weight: 1024.
    }
}