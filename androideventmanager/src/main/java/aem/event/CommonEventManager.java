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
 * An abstract class just encapsulates most of the common event listeners({declared in @link View class directly}).
 * When bind an event to a view, in derived class, you just need to declare a method that has the corresponding signature
 * (e.g. drag event with an argument of type DragEventEventArgs), and annotate it with {@link EventAnnotation}.
 * Note: the return value match is not required but recommended. For method that requires return boolean type,
 * {@code true} will be returned by default if the method return type is not.
 */
public abstract class CommonEventManager
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

    protected CommonEventManager(Activity activity) {
        this.activity = activity;
        eventMap = new HashMap<>();
        registerEvents();
    }

    void setListener(View view, EventType eventType) {
        switch (eventType) {
            case OnKey:
                view.setOnKeyListener(this);
                return;
            case OnTouch:
                view.setOnTouchListener(this);
                return;
            case OnHover:
                view.setOnHoverListener(this);
                return;
            case OnGenericMotion:
                view.setOnGenericMotionListener(this);
                return;
            case OnLongClick:
                view.setOnLongClickListener(this);
                return;
            case OnDrag:
                view.setOnDragListener(this);
                return;
            case OnFocusChange:
                view.setOnFocusChangeListener(this);
                return;
            case OnClick:
                view.setOnClickListener(this);
                return;
            case OnCreateContextMenu:
                view.setOnCreateContextMenuListener(this);
                return;
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

    Object invokeAction(EventType eventType, EventArgs args) {
        Object result = null;
        if (args.sender != null) {
            Method action = eventMap.get(args.sender.getId()).get(eventType);
            if (action != null) {
                try {
                    result = action.invoke(this, args);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    boolean invokeActionReturnStatus(EventType eventType, EventArgs args) {
        Object result = invokeAction(eventType, args);
        return result instanceof Boolean || (boolean) result;
    }

    protected class EventArgs {
        public View sender;

        public EventArgs(View sender) {
            this.sender = sender;
        }
    }

    protected class ContextMenuEventEventArgs extends EventArgs {
        public ContextMenu menu;
        public ContextMenu.ContextMenuInfo menuInfo;

        public ContextMenuEventEventArgs(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
            super(view);
            this.menu = menu;
            this.menuInfo = menuInfo;
        }
    }

    protected class DragEventEventArgs extends EventArgs {
        public DragEvent event;

        public DragEventEventArgs(View view, DragEvent event) {
            super(view);
            this.event = event;
        }
    }

    protected class FocusChangeEventEventArgs extends EventArgs {
        public boolean hasFocus;

        public FocusChangeEventEventArgs(View view, boolean hasFocus) {
            super(view);
            this.hasFocus = hasFocus;
        }
    }

    protected class GenericMotionEventEventArgs extends EventArgs {
        public MotionEvent event;

        public GenericMotionEventEventArgs(View view, MotionEvent event) {
            super(view);
            this.event = event;
        }
    }

    protected class KeyEventEventArgs extends EventArgs {
        public int keyCode;
        public KeyEvent event;

        public KeyEventEventArgs(View view, int keyCode, KeyEvent event) {
            super(view);
            this.keyCode = keyCode;
            this.event = event;
        }
    }

    @Override
    public void onClick(View v) {
        invokeAction(EventType.OnClick, new EventArgs(v));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        invokeAction(EventType.OnCreateContextMenu, new ContextMenuEventEventArgs(menu, v, menuInfo));
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return invokeActionReturnStatus(EventType.OnDrag, new DragEventEventArgs(v, event));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        invokeAction(EventType.OnFocusChange, new FocusChangeEventEventArgs(v, hasFocus));
    }

    @Override
    public boolean onGenericMotion(View v, MotionEvent event) {
        return invokeActionReturnStatus(EventType.OnGenericMotion, new GenericMotionEventEventArgs(v, event));
    }

    @Override
    public boolean onHover(View v, MotionEvent event) {
        return invokeActionReturnStatus(EventType.OnHover, new GenericMotionEventEventArgs(v, event));
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return invokeActionReturnStatus(EventType.OnKey, new KeyEventEventArgs(v, keyCode, event));
    }

    @Override
    public boolean onLongClick(View v) {
        return invokeActionReturnStatus(EventType.OnLongClick, new EventArgs(v));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return invokeActionReturnStatus(EventType.OnTouch, new GenericMotionEventEventArgs(v, event));
    }
}