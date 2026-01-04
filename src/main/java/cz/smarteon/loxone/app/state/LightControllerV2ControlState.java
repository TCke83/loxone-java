package cz.smarteon.loxone.app.state;

import cz.smarteon.loxone.Codec;
import cz.smarteon.loxone.Loxone;
import cz.smarteon.loxone.app.LightControllerV2Control;
import cz.smarteon.loxone.app.state.events.MoodEvent;
import cz.smarteon.loxone.app.state.model.Mood;
import cz.smarteon.loxone.message.TextEvent;
import cz.smarteon.loxone.message.ValueEvent;
import cz.smarteon.loxone.support.ThrowingConsumer;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.DoubleConsumer;
import java.util.stream.Collectors;

/**
 * State class for keeping and managing state of a <code>LightControllerV2Control</code>.
 */
@Slf4j
public class LightControllerV2ControlState extends LockableControlState<LightControllerV2State, LightControllerV2Control> {

    private List<Integer> initialMoodIds = Collections.emptyList();

    public LightControllerV2ControlState(Loxone loxone, LightControllerV2Control control) {
        super(loxone, control);
        setState(new LightControllerV2State());
    }

    /**
     * Accepts TextEvents that can contain active state updates for this control.
     * @param event text event received (should not be null)
     */
    @Override
    void accept(@NotNull TextEvent event) {
        if (event.getUuid().equals(getControl().stateActiveMoods())) {
            processEvent("activeMoods", event, eventText -> {
                final List<Integer> moodIds = Codec.readList(eventText, Integer.class);
                if (getState() == null || getState().getMoodList() == null) {
                    initialMoodIds = moodIds;
                } else {
                    setMoods(moodIds);
                }
            });
        } else if (event.getUuid().equals(getControl().stateMoodList())) {
            processEvent("moodList", event, eventText -> {
                final List<MoodEvent> moodEvents = Codec.readList(event.getText(), MoodEvent.class);
                getState().setMoodList(moodEvents.stream()
                        .map(me -> Mood.builder().name(me.getName()).id(me.getId()).build())
                        .collect(Collectors.toList()));
                if (initialMoodIds != null) {
                    setMoods(initialMoodIds);
                    initialMoodIds = null;
                }
            });
        }
    }

    /**
     * Accepts ValueEvents that can contain active state updates for this control.
     * @param event text event received (should not be null)
     */
    @Override
    void accept(@NotNull ValueEvent event) {
        if (event.getUuid().equals(getControl().stateActiveMoodsNum())) {
            processEvent("activeMoodsNum", event, eventValue -> {
                final int activeMoodsNum = (int) eventValue;
                getState().setActiveMoodNum(activeMoodsNum);
            });
        }
    }

    private void processEvent(String eventType, TextEvent event, ThrowingConsumer<String> eventHandler) {
        try {
            eventHandler.accept(event.getText());
        } catch (Exception e) {
            log.info("Unable to parse " + eventType + " event!", e);
        }
    }

    private void processEvent(String eventType, ValueEvent event, DoubleConsumer eventHandler) {
        try {
            eventHandler.accept(event.getValue());
        } catch (Exception e) {
            log.info("Unable to parse " + eventType + " event!", e);
        }
    }

    private void setMoods(List<Integer> initialMoodIds) {
        LightControllerV2State state = getState();
        state.setActiveMoods(initialMoodIds.stream()
                .map(i -> getState().getMoodList().stream().filter(m -> m.getId().equals(i)).findFirst().orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        setState(state);
    }
}
