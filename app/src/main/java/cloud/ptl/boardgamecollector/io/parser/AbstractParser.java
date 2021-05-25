package cloud.ptl.boardgamecollector.io.parser;

import cloud.ptl.boardgamecollector.io.dto.AbstractDTO;

public abstract class AbstractParser {
    public abstract AbstractDTO parse(String dto);
}
