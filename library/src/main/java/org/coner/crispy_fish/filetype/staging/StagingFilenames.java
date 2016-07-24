package org.coner.crispy_fish.filetype.staging;

import java.util.regex.Pattern;

public class StagingFilenames {

    static final Pattern ORIGINAL_FILE_PATTERN = Pattern.compile(".*.st1$");
    static final Pattern REACCEPTED_FILE_PATTERN = Pattern.compile(".*.st1.\\d{3}$");

    static String getReacceptedFileBaseName(String originalFileBaseName) {
        return originalFileBaseName + "_st1";
    }
}
