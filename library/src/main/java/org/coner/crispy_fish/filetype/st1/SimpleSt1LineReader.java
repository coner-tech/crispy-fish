package org.coner.crispy_fish.filetype.st1;

import org.coner.crispy_fish.datatype.underscore_pairs.SimpleUnderscorePairReader;

public class SimpleSt1LineReader extends BaseSt1LineReader<String> {

    public SimpleSt1LineReader(SimpleUnderscorePairReader underscorePairReader) {
        super(underscorePairReader);
    }

    public void setSt1Line(String st1Line) {
        underscorePairReader.setPairs(st1Line);
    }
}
