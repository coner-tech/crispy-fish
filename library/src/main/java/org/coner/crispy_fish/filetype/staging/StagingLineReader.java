package org.coner.crispy_fish.filetype.staging;

public interface StagingLineReader<L> {

    void setStagingLine(L stagingLine);

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
