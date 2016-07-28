package org.coner.crispy_fish.filetype.staging;

import org.coner.crispy_fish.filetype.ecf.EventControlFile;

public class StagingFileAssistant {
    public StagingFilenameFilter buildStagingFilenameFilter(EventControlFile eventControlFile) {
        return new StagingFilenameFilter(eventControlFile, this);
    }

    String getReacceptedFileBaseName(String originalFileBaseName) {
        return originalFileBaseName + "_st1";
    }
}
