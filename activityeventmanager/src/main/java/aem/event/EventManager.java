package aem.event;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * A abstract class to encapsulate most of the view event listeners. When bind an event to a view, in derived class,
 * you just need to declare a method that same with the event's signature, and annotate with {@link EventAnnotation}.
 * Note: the return value match is not required but recommended. For method that requires return boolean type,
 * {@code true} will be returned by default if the method return type is not.
 */
public abstract class EventManager
        implements
        View.OnKeyListener,
        View.OnTouchListener,
        View.OnHoverListener,
        View.OnGenericMotionListener,
        View.OnLongClickListener,
        View.OnDragListener,
        View.OnFocusChangeListener,
        View.OnClickListener,
        View.OnCreateContextMenuListener {

    private final Map<Integer, Map<EventType, Method>> eventMap;
    protected final Activity activity;

    protected EventManager(Activity activity) {
        this.activity = activity;
        eventMap = new HashMap<>();
        registerEvents();
    }

    private void setListener(View view, EventType eventType) {
        switch (eventType) {
            case OnKey:
                view.setOnKeyListener(this);
                break;
            case OnTouch:
                view.setOnTouchListener(this);
                break;
            case OnHover:
                view.setOnHoverListener(this);
                break;
            case OnGenericMotion:
                view.setOnGenericMotionListener(this);
                break;
            case OnLongClick:
                view.setOnLongClickListener(this);
                break;
            case OnDrag:
                view.setOnDragListener(this);
                break;
            case OnFocusChange:
                view.setOnFocusChangeListener(this);
                break;
            case OnClick:
                view.setOnClickListener(this);
                break;
            case OnCreateContextMenu:
                view.setOnCreateContextMenuListener(this);
                break;
        }
    }

    private void registerEvents() {
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventAnnotation.class)) {
                EventAnnotation annotation = method.getAnnotation(EventAnnotation.class);
                int viewId = annotation.value();
                View view = activity.findViewById(viewId);
                if (view != null) {
                    method.setAccessible(true);
                    EventType eventType = annotation.eventType();
                    setListener(view, eventType);
                    Map<EventType, Method> actionMap;
                    if (eventMap.containsKey(viewId)) {
                        actionMap = eventMap.get(viewId);
                        actionMap.put(eventType, method);
                    } else {
                        actionMap = new HashMap<>();
                        actionMap.put(eventType, method);
                        eventMap.put(viewId, actionMap);
                    }
                }
            }
        }
    }

    private Object invokeAction(EventType eventType, Object... args) {
        View view = null;
        for (Object obj : args) {
            if (obj instanceof View) {
                view = (View) obj;
                break;
            }
        }

        if (view == null) {
            return null;
        }

        Method action = eventMap.get(view.getId()).get(eventType);
        Object result = null;
        if (action != null) {
            try {
                result = action.invoke(this, args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private boolean invokeActionReturnStatus(EventType eventType, Object... args) {
        Object result = invokeAction(eventType, args);
        return result instanceof Boolean || (boolean) result;
    }

    @Override
    public void onClick(View v) {
        invokeAction(EventType.OnClick, v);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        invokeAction(EventType.OnCreateContextMenu, menu, v, menuInfo);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return invokeActionReturnStatus(EventType.OnDrag, v, event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        invokeAction(EventType.OnFocusChange, v, hasFocus);
    }

    @Override
    public boolean onGenericMotion(View v, MotionEvent event) {
        return invokeActionReturnStatus(EventType.OnGenericMotion, v, event);
    }

    @Override
    public boolean onHover(View v, MotionEvent event) {
        return invokeActionReturnStatus(EventType.OnHover, v, event);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return invokeActionReturnStatus(EventType.OnKey, v, keyCode, event);
    }

    @Override
    public boolean onLongClick(View v) {
        return invokeActionReturnStatus(EventType.OnLongClick, v);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return invokeActionReturnStatus(EventType.OnTouch, v, event);
    }
}