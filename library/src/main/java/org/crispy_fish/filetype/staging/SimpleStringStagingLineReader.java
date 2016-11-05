package org.crispy_fish.filetype.staging;

import org.crispy_fish.datatype.underscore_pairs.UnderscorePairReader;

import static org.crispy_fish.filetype.staging.StagingLineKeys.BESTTIME;
import static org.crispy_fish.filetype.staging.StagingLineKeys.CAR;
import static org.crispy_fish.filetype.staging.StagingLineKeys.CC;
import static org.crispy_fish.filetype.staging.StagingLineKeys.CLASS;
import static org.crispy_fish.filetype.staging.StagingLineKeys.DRIVER;
import static org.crispy_fish.filetype.staging.StagingLineKeys.NUMBER;
import static org.crispy_fish.filetype.staging.StagingLineKeys.PAXED;
import static org.crispy_fish.filetype.staging.StagingLineKeys.PENALTY;
import static org.crispy_fish.filetype.staging.StagingLineKeys.POS;
import static org.crispy_fish.filetype.staging.StagingLineKeys.RUN;
import static org.crispy_fish.filetype.staging.StagingLineKeys.TM;
import static org.crispy_fish.filetype.staging.StagingLineKeys.TOD;

public class SimpleStringStagingLineReader implements StagingLineReader<String> {

    protected final UnderscorePairReader<String> underscorePairReader;

    public SimpleStringStagingLineReader(UnderscorePairReader<String> underscorePairReader) {
        this.underscorePairReader = underscorePairReader;
    }

    public String getRunNumber(String line) {
        return underscorePairReader.get(line, RUN);
    }

    public String getRegisteredDriverClass(String line) {
        return underscorePairReader.get(line, CLASS);
    }

    public String getRegisteredDriverNumber(String line) {
        return underscorePairReader.get(line, NUMBER);
    }

    public String getRunRawTime(String line) {
        return underscorePairReader.get(line, TM);
    }

    public String getRunPenalty(String line) {
        return underscorePairReader.get(line, PENALTY);
    }

    public String getRegisteredDriverName(String line) {
        return underscorePairReader.get(line, DRIVER);
    }

    public String getRegisteredDriverCar(String line) {
        return underscorePairReader.get(line, CAR);
    }

    public String getRegisteredDriverCarColor(String line) {
        return underscorePairReader.get(line, CC);
    }

    public String getDriverPositionInClassForDay(String line) {
        return underscorePairReader.get(line, POS);
    }

    public String getDriverBestTimeOfDay(String line) {
        return underscorePairReader.get(line, BESTTIME);
    }

    public String getRunPaxTime(String line) {
        return underscorePairReader.get(line, PAXED);
    }

    public String getRunTimestamp(String line) {
        return underscorePairReader.get(line, TOD);
    }
}
