package org.coner.crispy_fish.filetype.st1;

import org.coner.crispy_fish.datatype.underscore_pairs.UnderscorePairReader;

import static org.coner.crispy_fish.filetype.st1.St1Keys.*;

public class St1LineReader {

    private final UnderscorePairReader underscorePairReader;

    public St1LineReader(UnderscorePairReader underscorePairReader) {
        this.underscorePairReader = underscorePairReader;
    }

    public void setSt1Line(String st1Line) {
        underscorePairReader.setPairs(st1Line);
    }

    public String getRun() {
        return underscorePairReader.get(RUN);
    }

    public String getClazz() {
        return underscorePairReader.get(CLASS);
    }

    public String getNumber() {
        return underscorePairReader.get(NUMBER);
    }

    public String getTm() {
        return underscorePairReader.get(TM);
    }

    public String getPenalty() {
        return underscorePairReader.get(PENALTY);
    }

    public String getDriver() {
        return underscorePairReader.get(DRIVER);
    }

    public String getCar() {
        return underscorePairReader.get(CAR);
    }

    public String getCc() {
        return underscorePairReader.get(CC);
    }

    public String getPos() {
        return underscorePairReader.get(POS);
    }

    public String getBestTime() {
        return underscorePairReader.get(BESTTIME);
    }

    public String getPaxed() {
        return underscorePairReader.get(PAXED);
    }

    public String getTod() {
        return underscorePairReader.get(TOD);
    }
}
