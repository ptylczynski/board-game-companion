package cloud.ptl.boardgamecollector.io.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserGamesDTO extends AbstractDTO{
    List<Long> gamesID;
    List<String> names;
}
