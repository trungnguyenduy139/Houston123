package com.trungnguyen.android.houston123.bus;

import android.support.annotation.Nullable;

import com.trungnguyen.android.houston123.binding.BindingAction;
import com.trungnguyen.android.houston123.binding.BindingConsumer;

import java.lang.ref.WeakReference;


/**
 * About : kelin的WeakBindingAction
 */
public class WeakAction<T> {
    @Nullable
    private BindingAction action;
    @Nullable
    private BindingConsumer<T> consumer;
    private boolean isLive;
    private Object target;
    @Nullable
    private WeakReference reference;

    public WeakAction(Object target, BindingAction action) {
        reference = new WeakReference(target);
        this.action = action;

    }

    public WeakAction(Object target, BindingConsumer<T> consumer) {
        reference = new WeakReference(target);
        this.consumer = consumer;
    }

    public void execute() {
        if (action != null && isLive()) {
            action.call();
        }
    }

    public void execute(T parameter) {
        if (consumer != null
                && isLive()) {
            consumer.call(parameter);
        }
    }

    public void markForDeletion() {
        reference.clear();
        reference = null;
        action = null;
        consumer = null;
    }

    @Nullable
    public BindingAction getBindingAction() {
        return action;
    }

    @Nullable
    public BindingConsumer getBindingConsumer() {
        return consumer;
    }

    public boolean isLive() {
        if (reference == null) {
            return false;
        }
        if (reference.get() == null) {
            return false;
        }
        return true;
    }


    @Nullable
    public Object getTarget() {
        if (reference != null) {
            return reference.get();
        }
        return null;
    }
}
