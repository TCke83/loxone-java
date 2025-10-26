package cz.smarteon.loxone.app.state;

import cz.smarteon.loxone.Loxone;
import cz.smarteon.loxone.app.SwitchControl;
import cz.smarteon.loxone.message.ValueEvent;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static cz.smarteon.loxone.message.ControlCommand.genericControlCommand;

/**
 * State class for keeping and managing state of a <code>SwitchControl</code>.
 */
public class SwitchControlState extends LockableControlState<SwitchControl> {

    /**
     * Current state of the SwitchControl.
     */
    @Getter
    @Nullable
    private Boolean state;

    public SwitchControlState(Loxone loxone, SwitchControl control) {
        super(loxone, control);
    }

    /**
     * Toggles state of SwitchControl. When current state is <code>SwitchState.UNINITIALIZED</code> it switches to On.
     */
    public void toggleState() {
        if (Boolean.TRUE.equals(state)) {
            stateOff();
        } else {
            stateOn();
        }
    }

    /**
     * Sets state of SwitchControl to On.
     */
    public void stateOn() {
        if (getLocked() != null && !Locked.NO.equals(getLocked())) {
            throw new LoxoneLockedException("SwitchControl is locked, so no state change is possible");
        }
        loxone.sendControlCommand(control, switchControl -> genericControlCommand(switchControl.getUuid().toString(),
                "On"));
    }

    /**
     * Sets state of SwitchControl to Off.
     */
    public void stateOff() {
        if (getLocked() != null && !Locked.NO.equals(getLocked())) {
            throw new LoxoneLockedException("SwitchControl is locked, so no state change is possible");
        }
        loxone.sendControlCommand(control, switchControl -> genericControlCommand(switchControl.getUuid().toString(),
                "Off"));
    }

    /**
     * Accepts ValueEvents that can contain active state updates for this control.
     * @param event value event received (should not be null)
     */
    @Override
    boolean acceptValueEvent(@NotNull ValueEvent event) {
        if (event.getUuid().equals(control.stateActive())) {
            return processActiveEvent(event);
        }
        return super.acceptValueEvent(event);
    }

    /**
     * Process the ValueEvent as an active state event message and update the state of the control accordingly.
     * @param event value event received
     */
    private boolean processActiveEvent(ValueEvent event) {
        final Boolean eventState = event.getValue() == 1;
        if (state == null || !state.equals(eventState)) {
            state = eventState;
            return true;
        }
        return false;
    }
}
