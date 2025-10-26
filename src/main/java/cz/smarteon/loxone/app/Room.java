package cz.smarteon.loxone.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.smarteon.loxone.LoxoneUuid;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.jetbrains.annotations.NotNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents Room.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Jacksonized
@Builder
@Value
@SuppressWarnings("checkstyle:visibilitymodifier")
@SuppressFBWarnings(value = "RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE", justification = "Fields are annotated with @NotNull and used by Lombok; SpotBugs false-positive on constructor null-checks")
public class Room {

    /**
     * UUID of this category, should be unique.
     */
    @JsonProperty(value = "uuid", required = true)
    @NotNull
    LoxoneUuid uuid;

    /**
     * Category name - usually localized, non unique.
     */
    @JsonProperty(value = "name", required = true)
    @NotNull
    String name;
}
