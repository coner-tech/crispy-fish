package org.coner.crispy_fish.filetype.staging;

public interface StagingLineReader<L> {

    String getRegisteredClass(L line);

    String getRegisteredNumber(L line);

    String getRegisteredDriverName(L line);

    String getRegisteredDriverCar(L line);

    String getRegisteredDriverCarColor(L line);

    String getRunNumber(L line);

    String getRunRawTime(L line);

    String getRunPaxTime(L line);

    String getRunPenalty(L line);

    String getRunTimestamp(L line);

    String getDriverPositionInClassForDay(L line);

    String getDriverBestTimeOfDay(L line);
}
