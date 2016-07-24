package org.coner.crispy_fish.filetype.staging;

import org.coner.crispy_fish.datatype.underscore_pairs.SimpleUnderscorePairReader;

public class SimpleStagingLineReader extends BaseStagingLineReader<String> {

    public SimpleStagingLineReader(SimpleUnderscorePairReader underscorePairReader) {
        super(underscorePairReader);
    }

    public void setStagingLine(String stagingLine) {
        underscorePairReader.setPairs(stagingLine);
    }
}
