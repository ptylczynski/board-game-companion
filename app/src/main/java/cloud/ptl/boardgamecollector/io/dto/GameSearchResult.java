package cloud.ptl.boardgamecollector.io.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GameSearchResult extends AbstractDTO{
    private List<String> gameNames;
    private List<Integer> ids;
}
