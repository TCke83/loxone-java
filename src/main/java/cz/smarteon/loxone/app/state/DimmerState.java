package cz.smarteon.loxone.app.state;

import cz.smarteon.loxone.app.state.model.Mood;
import lombok.*;

import java.util.List;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode
@NoArgsConstructor
public class DimmerState {

    private Double position ;

    private Double min;

    private Double max;

    private Double step;

}
