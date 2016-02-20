package org.coner.crispy_fish.filetype.st1;

public interface St1LineReader<L> {

    void setSt1Line(L st1Line);

    String getRun();

    String getClazz();

    String getNumber();

    String getTm();

    String getPenalty();

    String getDriver();

    String getCar();

    String getCc();

    String getPos();

    String getBestTime();

    String getPaxed();

    String getTod();
}
