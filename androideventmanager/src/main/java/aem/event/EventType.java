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

    // Adapter view event type weight: 3840.
    /**
     * signature: (AdapterView<?> parent, View view, int position, long id)
     * return: void.
     */
    OnItemClick(256),
    /**
     * signature: (AdapterView<?> parent, View view, int position, long id)
     * return: boolean.
     */
    OnItemLongClick(512),
    /**
     * signature: (AdapterView<?> parent, View view, int position, long id)
     * return: void.
     */
    OnItemSelected(1024),
    /**
     * signature: (AdapterView<?> parent)
     * return: boolean.
     */
    OnNothingSelected(2048);

    private final int key;

    EventType(int key) {
        this.key = key;
    }

    public boolean isAdapterViewEvent() {
        return (key & 3840) > 0; // Adapter view event type weight: 3840.
    }
}