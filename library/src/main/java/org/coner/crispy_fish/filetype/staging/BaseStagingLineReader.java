package org.coner.crispy_fish.filetype.staging;

import org.coner.crispy_fish.datatype.underscore_pairs.*;

import static org.coner.crispy_fish.filetype.staging.StagingLineKeys.BESTTIME;
import static org.coner.crispy_fish.filetype.staging.StagingLineKeys.CAR;
import static org.coner.crispy_fish.filetype.staging.StagingLineKeys.CC;
import static org.coner.crispy_fish.filetype.staging.StagingLineKeys.CLASS;
import static org.coner.crispy_fish.filetype.staging.StagingLineKeys.DRIVER;
import static org.coner.crispy_fish.filetype.staging.StagingLineKeys.NUMBER;
import static org.coner.crispy_fish.filetype.staging.StagingLineKeys.PAXED;
import static org.coner.crispy_fish.filetype.staging.StagingLineKeys.PENALTY;
import static org.coner.crispy_fish.filetype.staging.StagingLineKeys.POS;
import static org.coner.crispy_fish.filetype.staging.StagingLineKeys.RUN;
import static org.coner.crispy_fish.filetype.staging.StagingLineKeys.TM;
import static org.coner.crispy_fish.filetype.staging.StagingLineKeys.TOD;

public abstract class BaseStagingLineReader<L> implements StagingLineReader<L> {

    protected final UnderscorePairReader<L> underscorePairReader;

    public BaseStagingLineReader(UnderscorePairReader<L> underscorePairReader) {
        this.underscorePairReader = underscorePairReader;
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
