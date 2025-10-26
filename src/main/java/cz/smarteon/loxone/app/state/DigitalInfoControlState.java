package cz.smarteon.loxone.app.state;

import cz.smarteon.loxone.Loxone;
import cz.smarteon.loxone.app.DigitalInfoControl;
import cz.smarteon.loxone.message.ValueEvent;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * State class for keeping state of a <code>DigitalInfoControl</code>.
 */
public class DigitalInfoControlState extends ControlState<DigitalInfoControl> {

    /**
     * Current value of the DigitalInfoControl.
     */
    @Getter
    @Nullable
    private Boolean state;


    public DigitalInfoControlState(Loxone loxone, DigitalInfoControl control) {
        super(loxone, control);
    }

    /**
     * Accepts ValueEvents that can contain active state updates for this control.
     * @param event value event received (should not be null)
     */
    @Override
    boolean acceptValueEvent(@NotNull ValueEvent event) {
        if (event.getUuid().equals(control.stateActive())) {
            return processValueEvent(event);
        }
        return super.acceptValueEvent(event);
    }

    /**
     * Process the ValueEvent as an active state event message and update the state of the control accordingly.
     * @param event value event received
     */
    private boolean processValueEvent(ValueEvent event) {
        final Boolean eventState = event.getValue() == 1;
        if (state == null || !state.equals(eventState)) {
            state = eventState;
            return true;
        }
        return false;
    }
}
