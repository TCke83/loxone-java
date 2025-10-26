package cz.smarteon.loxone.app.state;

import cz.smarteon.loxone.Loxone;
import cz.smarteon.loxone.app.Control;
import cz.smarteon.loxone.message.TextEvent;
import cz.smarteon.loxone.message.ValueEvent;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Base class for all the controlStates in loxone application.
 * <p>
 * This class keeps track of the state of the control based on the events of the miniserver.
 * </p>
 * @param <T> The type of control this class keeps track of.
 */
@RequiredArgsConstructor
public abstract class ControlState<T extends Control> {

    /**
     * The webSocket connection to communicate with the loxone miniserver.
     */
    protected final Loxone loxone;

    /**
     * The control that this state refers to.
     */
    protected final T control;

    /**
     * The set of listeners registered for this control state.
     */
    private final Set<ControlStateListener<T>> listeners = new CopyOnWriteArraySet<>();

    /**
     * Registers a listener to be notified of state changes.
     * @param listener the listener to register (should not be null).
     */
    public void registerListener(@NotNull ControlStateListener<T> listener) {
        listeners.add(listener);
    }

    /**
     * Unregisters a previously registered listener.
     * @param listener the listener to unregister (should not be null).
     */
    public void unregisterListener(@NotNull ControlStateListener<T> listener) {
        listeners.remove(listener);
    }

    /**
     * Method that accepts ValueEvent from the miniserver to update the internal state.
     * @param event value event received (should not be null).
     */
    public final void accept(@NotNull ValueEvent event) {
        if (acceptValueEvent(event)) {
            notifyStateChanged();
        }
    }

    /**
     * Method that accepts TextEvent from the miniserver to update the internal state.
     * @param event text event received (should not be null).
     */
    public final void accept(@NotNull TextEvent event) {
        if (acceptTextEvent(event)) {
            notifyStateChanged();
        }
    }

    /**
     * Accepts ValueEvents that can contain active state updates for this control.
     * @param event value event received (should not be null).
     * @return boolean indicating the state of the control has changed.
     */
    boolean acceptValueEvent(@NotNull ValueEvent event) {
        // default implementation
        return false;
    }

    /**
     * Accepts TextEvents that can contain active state updates for this control.
     * @param event text event received (should not be null).
     * @return boolean indicating the state of the control has changed.
     */
    boolean acceptTextEvent(@NotNull TextEvent event) {
        // default implementation
        return false;
    }

    /**
     * Notifies all registered listeners of a state change.
     */
    protected void notifyStateChanged() {
        for (ControlStateListener<T> listener : listeners) {
            listener.onStateChange(this);
        }
    }
}
