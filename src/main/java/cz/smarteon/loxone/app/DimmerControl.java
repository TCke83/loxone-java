package cz.smarteon.loxone.app;

import cz.smarteon.loxone.LoxoneUuid;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a dimmer block or o subcontrol of a LightControllerV2Control.
 */
public class DimmerControl extends Control {

    public static final String NAME = "Dimmer";

    @NotNull
    public LoxoneUuid statePosition() {
        return getCompulsoryState("position").only();
    }

    @NotNull
    public LoxoneUuid stateMin() {
        return getCompulsoryState("position").only();
    }

    @NotNull
    public LoxoneUuid stateMax() {
        return getCompulsoryState("position").only();
    }

    @NotNull
    public LoxoneUuid stateStep() {
        return getCompulsoryState("position").only();
    }
}
