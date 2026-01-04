package cz.smarteon.loxone.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.smarteon.loxone.LoxoneUuid;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Represents LightControllerV2 block.
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LightControllerV2Control extends Control {

    public static final String NAME = "LightControllerV2";

    @JsonProperty("details")
    private Details details;

    @JsonProperty("subControls")
    private Map<String, Control> subControls;

    @NotNull
    public LoxoneUuid stateActiveMoods() {
        return getCompulsoryState("activeMoods").only();
    }

    @NotNull
    public LoxoneUuid stateMoodList() {
        return getCompulsoryState("moodList").only();
    }

    @NotNull
    public LoxoneUuid stateActiveMoodsNum() {
        return getCompulsoryState("activeMoodsNum").only();
    }

    @NotNull
    public LoxoneUuid stateFavoriteMoods() {
        return getCompulsoryState("favoriteMoods").only();
    }

    @NotNull
    public LoxoneUuid stateAdditionalMoods() {
        return getCompulsoryState("additionalMoods").only();
    }

    @NotNull
    public LoxoneUuid stateCircuitNames() {
        return getCompulsoryState("circuitNames").only();
    }

    @NotNull
    public LoxoneUuid stateDaylightConfig() {
        return getCompulsoryState("daylightConfig").only();
    }

    @NotNull
    public LoxoneUuid statePresence() {
        return getCompulsoryState("presence").only();
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Details {
        @JsonProperty("jLockable")
        private boolean lockable;

        @JsonProperty("controlHistory")
        private boolean controlHistory;

        @JsonProperty("hasHistory")
        private int hasHistory;

        @JsonProperty("movementScene")
        private int movementScene;

        @JsonProperty("masterValue")
        private String masterValue;
    }
}
