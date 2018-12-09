package org.crispy_fish.util;

import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.crispy_fish.filetype.ecf.EventControlFile;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public enum TestEvent {

    THSCC_2016_POINTS_1(
            "2016-03-05 points autox 1 danville.st1",
            false
    ),
    THSCC_2016_POINTS_2(
            "2016-03-18 points autox 2 cary.st1",
            false
    ),
    THSCC_2016_POINTS_3(
            "2016 Points Event 3 Danville.st1",
            false
    ),
    THSCC_2016_POINTS_9(
            "2016-09-17 points autox 9 cary.st1",
            false
    );

    private static final String RESOURCES_PREFIX = (TestEvent.class.getCanonicalName() + "/").replace(".", "/");

    private final String stagingFileName;
    private final int conePenalty = 2;
    private final boolean twoDayEvent;


    TestEvent(String stagingFileName, boolean twoDayEvent) {
        this.stagingFileName = stagingFileName;
        this.twoDayEvent = twoDayEvent;
    }

    public List<String> getStagingFileLines() {
        URL url = Resources.getResource(RESOURCES_PREFIX + stagingFileName);
        try {
            return FileUtils.readLines(new File(url.toURI()));
        } catch (Exception e) {
            throw new RuntimeException("Unable to get staging file line for " + this.toString(), e);
        }
    }

    public EventControlFile buildEventControlFileMock() {
        EventControlFile eventControlFile = mock(EventControlFile.class);
        when(eventControlFile.getConePenalty()).thenReturn(conePenalty);
        when(eventControlFile.isTwoDayEvent()).thenReturn(twoDayEvent);
        when(eventControlFile.getPath()).thenThrow(UnsupportedOperationException.class);
        return eventControlFile;
    }

}
