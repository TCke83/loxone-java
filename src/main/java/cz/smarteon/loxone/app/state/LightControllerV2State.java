package cz.smarteon.loxone.app.state;

import cz.smarteon.loxone.app.state.model.Mood;
import lombok.*;

import java.util.List;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode
@NoArgsConstructor
public class LightControllerV2State {

    private List<Mood> activeMoods;

    private List<Mood> moodList;

    private Integer activeMoodNum;

    private List<Mood> favoriteMoods;

    private List<Mood> additionalMoods;

    private List<String> circuitNames;

    private Object daylightConfig;

    private Object presence;
}
