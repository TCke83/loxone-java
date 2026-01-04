package cz.smarteon.loxone.app.state;

import cz.smarteon.loxone.Loxone;
import cz.smarteon.loxone.app.DimmerControl;
import cz.smarteon.loxone.app.SwitchControl;
import cz.smarteon.loxone.message.TextEvent;
import cz.smarteon.loxone.message.ValueEvent;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static cz.smarteon.loxone.message.ControlCommand.genericControlCommand;

/**
 * State class for keeping and managing state of a <code>SwitchControl</code>.
 */
@Slf4j
public class DimmerControlState extends LockableControlState<DimmerState, DimmerControl> {

    public DimmerControlState(Loxone loxone, DimmerControl control) {
        super(loxone, control);
    }

    /**
     * Accepts ValueEvents that can contain active state updates for this control.
     * @param event value event received (should not be null)
     */
    @Override
    void accept(@NotNull ValueEvent event) {
        if (event.getUuid().equals(getControl().statePosition())) {
            log.info("Received active state update for {}: {}", getControl().getUuid(), event.getValue());
        } else if (event.getUuid().equals(getControl().stateMin())) {
            log.info("Received active state update for {}: {}", getControl().getUuid(), event.getValue());
        } else if (event.getUuid().equals(getControl().stateMax())) {
            log.info("Received active state update for {}: {}", getControl().getUuid(), event.getValue());
        } else if (event.getUuid().equals(getControl().stateStep())) {
            log.info("Received active state update for {}: {}", getControl().getUuid(), event.getValue());
        }
    }

    /**
     * Accepts TextEvents that can contain active state updates for this control.
     * @param event value event received (should not be null)
     */
    @Override
    void accept(@NotNull TextEvent event) {
        if (event.getUuid().equals(getControl().statePosition())) {
            log.info("Received active state update for {}: {}", getControl().getUuid(), event.getText());
        } else if (event.getUuid().equals(getControl().stateMin())) {
            log.info("Received active state update for {}: {}", getControl().getUuid(), event.getText());
        } else if (event.getUuid().equals(getControl().stateMax())) {
            log.info("Received active state update for {}: {}", getControl().getUuid(), event.getText());
        } else if (event.getUuid().equals(getControl().stateStep())) {
            log.info("Received active state update for {}: {}", getControl().getUuid(), event.getText());
        }
    }
}
