package org.coner.crispy_fish.filetype.st1;

import org.coner.crispy_fish.filetype.ecf.EcfAssistant;

import java.io.*;
import java.nio.file.Path;
import org.apache.commons.io.FilenameUtils;

public class St1FilenameFilter implements FilenameFilter {

    private final String eventFileOriginalSt1BaseName;
    private final String eventFileReacceptedSt1BaseName;

    public St1FilenameFilter(EcfAssistant ecfAssistant, Path eventControlFile) {
        if (!ecfAssistant.isEcf(eventControlFile)) {
            throw new IllegalArgumentException("eventControlFile is not an ecf");
        }
        this.eventFileOriginalSt1BaseName = FilenameUtils.getBaseName(eventControlFile.toString());
        this.eventFileReacceptedSt1BaseName = St1Filenames.getReacceptedFileBaseName(eventFileOriginalSt1BaseName);
    }

    public boolean accept(File dir, String name) {
        String baseName = FilenameUtils.getBaseName(name);

        if (name == null || baseName == null) {
            return false;
        }

        if (St1Filenames.ORIGINAL_FILE_MATCHER.matcher(name).matches()) {
            if (eventFileOriginalSt1BaseName.equalsIgnoreCase(baseName)) {
                return true;
            }
            return false;
        }

        if (St1Filenames.REACCEPTED_FILE_MATCHER.matcher(name).matches()) {
            if (eventFileReacceptedSt1BaseName.equalsIgnoreCase(baseName)) {
                return true;
            }
            return false;
        }

        return false;
    }
}
