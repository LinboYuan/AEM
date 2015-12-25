package aem.event;

/**
 * Event type.
 */
public enum EventType {
    /**
     * signature: (View v, int keyCode, KeyEvent event)
     * return: boolean.
     */
    OnKey,
    /**
     * signature: (View v, MotionEvent event)
     * return: boolean.
     */
    OnTouch,
    /**
     * signature: (View v, MotionEvent event)
     * return: boolean.
     */
    OnHover,
    /**
     * signature: (View v, MotionEvent event)
     * return: boolean.
     */
    OnGenericMotion,
    /**
     * signature: (View v)
     * return: boolean.
     */
    OnLongClick,
    /**
     * signature: (View v, DragEvent event)
     * return: boolean.
     */
    OnDrag,
    /**
     * signature: (View v, boolean hasFocus)
     * return: void.
     */
    OnFocusChange,
    /**
     * signature: (View v)
     * return: void.
     */
    OnClick,
    /**
     * signature: (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
     * return: void.
     */
    OnCreateContextMenu,

    //TODO: Not supported yet for api version issues or other special reasons.
//    /**
//     * signature: (View v)
//     */
//    OnViewAttachedToWindow,
//    /**
//     * signature: (View v)
//     */
//    OnViewDetachedFromWindow,
//    /**
//     * signature: (View v)
//     */
//    OnContextClick,
//    /**
//     * signature: (int visibility)
//     */
//    OnSystemUiVisibilityChange
}