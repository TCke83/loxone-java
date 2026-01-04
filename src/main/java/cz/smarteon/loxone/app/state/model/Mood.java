package cz.smarteon.loxone.app.state.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Mood {

    String name;

    Integer id;
}
