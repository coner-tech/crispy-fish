package org.coner.crispy_fish.filetype.st1;

import org.coner.crispy_fish.datatype.underscore_pairs.*;

import static org.coner.crispy_fish.filetype.st1.St1Keys.BESTTIME;
import static org.coner.crispy_fish.filetype.st1.St1Keys.CAR;
import static org.coner.crispy_fish.filetype.st1.St1Keys.CC;
import static org.coner.crispy_fish.filetype.st1.St1Keys.CLASS;
import static org.coner.crispy_fish.filetype.st1.St1Keys.DRIVER;
import static org.coner.crispy_fish.filetype.st1.St1Keys.NUMBER;
import static org.coner.crispy_fish.filetype.st1.St1Keys.PAXED;
import static org.coner.crispy_fish.filetype.st1.St1Keys.PENALTY;
import static org.coner.crispy_fish.filetype.st1.St1Keys.POS;
import static org.coner.crispy_fish.filetype.st1.St1Keys.RUN;
import static org.coner.crispy_fish.filetype.st1.St1Keys.TM;
import static org.coner.crispy_fish.filetype.st1.St1Keys.TOD;

public abstract class BaseSt1LineReader<L> implements St1LineReader<L> {

    protected final UnderscorePairReader<L> underscorePairReader;

    public BaseSt1LineReader(UnderscorePairReader<L> underscorePairReader) {
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
