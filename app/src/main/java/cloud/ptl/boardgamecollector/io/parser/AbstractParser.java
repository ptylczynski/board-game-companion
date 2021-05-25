package cloud.ptl.boardgamecollector.io.parser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import cloud.ptl.boardgamecollector.io.dto.AbstractDTO;

public abstract class AbstractParser <T extends AbstractDTO> {
    public abstract T parse(InputStream is) throws XmlPullParserException, IOException;
}
