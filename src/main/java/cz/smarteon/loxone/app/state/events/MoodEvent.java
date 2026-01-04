package cz.smarteon.loxone.app.state.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Java representation of Loxone locked event.
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoodEvent {

    String name;

    String shortName;

    Integer id;

    boolean t5;

    @JsonProperty("static")
    boolean readOnly;

    Integer used;
}
