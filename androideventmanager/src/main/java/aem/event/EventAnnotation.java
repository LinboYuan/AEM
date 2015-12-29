package aem.event;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventAnnotation {
    /**
     * View id.
     *
     * @return the view id that the event binds to.
     */
    int value();

    /**
     * Event type. If not specified, onClick will be set by default.
     *
     * @return the event type of the method binds to.
     */
    EventType eventType() default EventType.OnClick;
}