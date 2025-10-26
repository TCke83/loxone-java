package cz.smarteon.loxone.app.state;

import cz.smarteon.loxone.Loxone;
import cz.smarteon.loxone.app.AnalogInfoControl;
import cz.smarteon.loxone.message.ValueEvent;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * State class for keeping state of a <code>AnalogInfoControl</code>.
 */
public class AnalogInfoControlState extends ControlState<AnalogInfoControl> {

    /**
     * Current value of the AnalogInfoControl.
     */
    @Getter
    @Nullable
    private Double value;

    public AnalogInfoControlState(Loxone loxone, AnalogInfoControl control) {
        super(loxone, control);
    }

    /**
     * Accepts ValueEvents that can contain active state updates for this control.
     * @param event value event received (should not be null)
     */
    @Override
    boolean acceptValueEvent(@NotNull ValueEvent event) {
        if (event.getUuid().equals(control.stateValue())) {
            return processValueEvent(event);
        }
        return super.acceptValueEvent(event);
    }

    /**
     * Process the ValueEvent as a value event message and update the state of the control accordingly.
     * @param event value event received
     */
    private boolean processValueEvent(ValueEvent event) {
        final Double eventValue = event.getValue();
        if (value == null || !value.equals(eventValue)) {
            value = eventValue;
            return true;
        }
        return false;
    }
}
